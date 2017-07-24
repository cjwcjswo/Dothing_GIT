package dothing.web.socket.handler;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import dothing.web.android.chat.AndroidChatServer;
import dothing.web.android.chat.ServerThread;
import dothing.web.service.ChatService;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
	Logger logger = Logger.getLogger(WebSocketHandler.class);
	public static Map<String, List<WebSocketSession>> sessionMap = new HashMap<>();
	public static Map<String, WebSocketSession> idMap = new HashMap<>();

	@Autowired
	private ChatService chatService;

	private String errandsNum;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		super.afterConnectionEstablished(session);

		System.out.println("CONNECTED: " + session.getId());
		if (isAuthenticated(session)) { // �α��� �Ǿ����� �� idMap�� ID / WEBSOCKET ��������
										// �߰�
			idMap.put(getUserId(session.getPrincipal()), session);
		}

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("CLOSED: " + session.getId());
		if (isAuthenticated(session)) { // �α��� �Ǿ����� �� idMap���� ����
			idMap.remove(getUserId(session.getPrincipal()));
		}
		if (sessionMap.get(errandsNum) != null) {
			sessionMap.get(errandsNum).remove(session);
			if (sessionMap.get(errandsNum).isEmpty()) {
				sessionMap.remove(errandsNum);
			}
		}

	}

	/**
	 * �α��� �Ǿ���?
	 */
	boolean isAuthenticated(WebSocketSession session) {
		if (session.getPrincipal() == null) {
			return false;
		}
		return true;
	}

	/**
	 * ������ ���̵� ��������
	 */
	String getUserId(Principal principal) {
		String[] idArr = principal.getName().split("=");
		String[] resultIdArr = idArr[1].split(",");
		return resultIdArr[0];
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		super.handleTransportError(session, exception);
		System.out.println("����: " + exception.getMessage());
	}
	public static void sendWebsocketMessage(String msg) throws IOException {
		String msgArr[] = msg.split("#/separator/#");
		// msgArr[0] = errandsNum;
		// msgArr[1] = sender;
		// msgArr[2] = msg;
		// msgArr[3] = writeday;
		String errandsNum = msgArr[0];
		System.out.println("���� ���Ǹ�: " + sessionMap);
		List<WebSocketSession> list = sessionMap.get(errandsNum);
		System.out.println("[ä��]���� �� ����: " + list);
		if (list != null) {
			for (WebSocketSession sess : list) {
				if (msg.length() > 20) {
					sess.sendMessage(new TextMessage(msg));
				}
			}
		}
	}

	public static void sendAlert(String msg) throws IOException {
		WebSocketSession session = idMap.get(msg.split(":")[1]);
		System.out.println("[�˶�]���� �� ����: " + session);
		if (session != null) {
			
			idMap.get(msg.split(":")[1]).sendMessage(new TextMessage("�˸�:" + msg.split(":")[2] + ":" + msg.split(":")[3]
					+ ":" + msg.split(":")[4] + ":" + msg.split(":")[5]));
		}
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		System.out.println("msg: " + msg);

		String replyArr[] = msg.split(":");
		System.out.println("replyArr : " + replyArr);
		System.out.println("replyArr[0] : " + replyArr[0]);
		if (replyArr[0].equals("�ɺθ�")) {
			Iterator<String> iter = idMap.keySet().iterator();
			while (iter.hasNext()) {
				WebSocketSession wb = idMap.get(iter.next());
				
				if ((wb != null) && !wb.getId().equals(session.getId())) {
					wb.sendMessage(new TextMessage("�ɺθ�:" + replyArr[1] + ":" + replyArr[2] + ":" + replyArr[3]));
				}

			}
		} else if (replyArr[0].equals("����")) {
			if(idMap.get(msg.split(":")[2]) != null){
			idMap.get(msg.split(":")[2]).sendMessage(new TextMessage("����:" + msg.split(":")[1]));
			}
		} else if (replyArr.length == 3 && replyArr[0].equals("���")) {
			if(idMap.get(msg.split(":")[1]) != null){
			idMap.get(msg.split(":")[1]).sendMessage(new TextMessage("���:" + msg.split(":")[2]));
			}
			
		} else if (replyArr[0].equals("�˸�")) {
			if(idMap.get(msg.split(":")[1]) != null){
			idMap.get(msg.split(":")[1]).sendMessage(new TextMessage("�˸�:" + msg.split(":")[2] + ":" + msg.split(":")[3]
					+ ":" + msg.split(":")[4] + ":" + msg.split(":")[5]));
			}
		} else {// replyArr == null
			// logger.error("come here");
			String msgArr[] = msg.split("#/separator/#");
			// msgArr[0] = errandsNum;
			// msgArr[1] = sender;
			// msgArr[2] = msg;
			// msgArr[3] = writeday;
			System.out.println("������ ������ ����?");
			errandsNum = msgArr[0];
			if (sessionMap.get(errandsNum) == null) {
				List<WebSocketSession> list = new ArrayList<>();
				System.out.println(errandsNum +"���� ���˸� �߰���!");
				list.add(session);
				sessionMap.put(errandsNum, list);
			} else {
				if (!sessionMap.get(errandsNum).contains(session)) {
					sessionMap.get(errandsNum).add(session);
				}
			}

			if (msg.length() > 20) {
				chatService.write(msgArr);
				List<ServerThread> androidList = AndroidChatServer.socketMap.get(errandsNum);
				if (androidList != null) {
					for (ServerThread th : androidList) {
						th.pw.println(msgArr[1] + ":" + msgArr[2] + ":" + msgArr[3]);
					}
				}
			}
			
			
			List<WebSocketSession> list = sessionMap.get(errandsNum);
			for (WebSocketSession sess : list) {
				System.out.println("���ľ�");
				if (msg.length() > 20) {
					System.out.println("���ı�");
					sess.sendMessage(new TextMessage(msg));
				}
			}

		}

	}
}
