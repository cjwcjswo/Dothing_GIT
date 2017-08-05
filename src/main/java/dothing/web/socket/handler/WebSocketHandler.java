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

/**
 * Ŭ���̾�Ʈ�κ��� ������ �޼����� ������ ó���ϴ� Handler
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	/**
	 * ������ 1:1 ä�ÿ��� ������ ���������� �����ϴ� Map
	 * Map String ä�ù� ��ȣ, List �����ϼ��� ����Ʈ
	 */
	public static Map<String, List<WebSocketSession>> sessionMap = new HashMap<>();
	
	/**
	 * ������ Ư�� ����ڿ��� �˸��� ������ ������ ���������� �����ϴ� Map
	 * Map String ���� id, WebSocketSession �ش��ϴ� �����ϼ���
	 */
	public static Map<String, WebSocketSession> idMap = new HashMap<>();

	@Autowired
	private ChatService chatService;
	private String errandsNum; // �ɺθ� ��ȣ(ä�ù� ��ȣ)

	/**
	 * �ش��ϴ� �����ϼ����� ���� ������Ʈ���� �α��� �Ǿ��°� ó��
	 * @param session Ŭ���̾�Ʈ�� ������ ����
	 * @return true �α��� ��, false �α��� �ȵ�
	 */
	private boolean isAuthenticated(WebSocketSession session) {
		if (session.getPrincipal() == null) return false; // �α����� �ȉ��� ���
		return true;
	}
	
	/**
	 * Principal�� ���� id���� return�ϴ� �޼ҵ�
	 * @param principal �ش��ϴ� ������ principal ��
	 * @return ������ ���̵�
	 */
	private String getUserId(Principal principal) {
		String[] idArr = principal.getName().split("=");
		String[] resultIdArr = idArr[1].split(",");
		return resultIdArr[0];
	}
	
	/**
	 * Ŭ���̾�Ʈ�� �������� �������� ���� �������� ��� ó��
	 * @param session Ŭ���̾�Ʈ�� ������ ����
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		System.out.println("CONNECTED: " + session.getId());
		
		// �ش��ϴ� Ŭ���̾�Ʈ�� �α��� �Ǿ��� �� idMap�� Ŭ���̾�Ʈ ���� ������ �����Ѵ�.
		if (isAuthenticated(session)) idMap.put(getUserId(session.getPrincipal()), session);
	}

	/**
	 * Ŭ���̾�Ʈ�� ���Ͽ����� ���������(������ �̵�..)
	 * @param session Ŭ���̾�Ʈ�� ������ ����
	 * @param status ���� ���� ����
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("CLOSED: " + session.getId());
		
		// �α��� �Ǿ����� �� idMap���� ����
		if (isAuthenticated(session)) { 
			idMap.remove(getUserId(session.getPrincipal()));
		}
		
		// ä�ù��� ������� sessionMap���� ����
		if (sessionMap.get(errandsNum) != null) {
			sessionMap.get(errandsNum).remove(session);
			if (sessionMap.get(errandsNum).isEmpty()) sessionMap.remove(errandsNum);
		}
	}

	
	/**
	 * �ȵ���̵� ä�� �����κ��� ���������� ä�ø޼����� ������ ���� �޼ҵ�
	 * @param msg ������ �޼��� ��
	 */
	public static void sendWebsocketMessage(String msg) throws IOException {
		String msgArr[] = msg.split("#/separator/#");
		// msgArr[0] = errandsNum(ä�ù� ��ȣ);
		// msgArr[1] = sender(������ ���);
		// msgArr[2] = msg(ä�� ����);
		// msgArr[3] = writeday(������ �ð�);
		
		String errandsNum = msgArr[0];
		System.out.println("���� ���Ǹ�: " + sessionMap);
		
		// errandsNum�� �ش��ϴ� ������ ���Ǹ���Ʈ(ä�ù�)�� �ҷ��´�
		List<WebSocketSession> list = sessionMap.get(errandsNum); 
		System.out.println("[ä��]���� �� ����: " + list);
		
		// ä�ù��� �������(������ ��밡 ���� �� ���)
		if (list != null) {
			for (WebSocketSession sess : list) {
				if (msg.length() > 20) sess.sendMessage(new TextMessage(msg)); // �޼��� ������	
			}
		}
	}

	/**
	 * �ȵ���̵� ä�ü����κ��� ���������� ä���� �Դٴ� �˸��޼����� ������ ���� �޼ҵ�
	 * @param msg ������ �޼��� ��
	 */
	public static void sendAlert(String msg) throws IOException {
		String msgArr[] = msg.split(":");
		// msgArr[0] = errandsNum(ä�ù� ��ȣ);
		// msgArr[1] = �޴»��;
		// msgArr[2] = msg(ä�� ����);
		// msgArr[3] = writeday(������ �ð�);
		WebSocketSession session = idMap.get(msgArr[1]);
		System.out.println("[�˶�]���� �� ����: " + session);
		if (session != null) {
			session.sendMessage(new TextMessage("�˸�:" + msgArr[2] + ":" + msgArr[3]
					+ ":" + msgArr[4] + ":" + msgArr[5]));
		}
	}

	/**
	 * �� Ŭ���̾�Ʈ�κ��� ������ �޽����� ������ ó���ϴ� �޼ҵ�
	 * @param session �޼����� ���� Ŭ���̾�Ʈ ����
	 * @param message �ش��ϴ� �޼���
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload(); // �޼����� ��� payload �ҷ�����
		System.out.println("msg: " + msg);

		String replyArr[] = msg.split(":"); // �޼����� ":"�����ڷ� �и���
		//replyArr[0] = �ɺθ� ����
		
		// �ɺθ� �˸��޼����� ���
		if (replyArr[0].equals("�ɺθ�")) { 
			Iterator<String> iter = idMap.keySet().iterator();
			// idMap�� ����� ��� ���ǿ��� �˸��޼����� ������
			while (iter.hasNext()) {
				WebSocketSession wb = idMap.get(iter.next());
				if ((wb != null) && !wb.getId().equals(session.getId())) {
					wb.sendMessage(new TextMessage("�ɺθ�:" + replyArr[1] + ":" + replyArr[2] + ":" + replyArr[3]));
				}
			}
		} 
		// �ɺθ� ��Ī �˸� �޼����� ���
		else if (replyArr[0].equals("����")) { 
			//replyArr[1] = ��Ī�� ���
			//replyArr[2] = ��Ī�� ���¹�
			if(idMap.get(replyArr[2]) != null){
			idMap.get(replyArr[2]).sendMessage(new TextMessage("����:" + replyArr[1]));
			}
		}
		// ��� �˸� �޼����� ���
		else if (replyArr.length == 3 && replyArr[0].equals("���")) {
			// replyArr[1] = �۾���
			// replyArr[2] = ��� �� ���
			if(idMap.get(replyArr[1]) != null){
			idMap.get(replyArr[1]).sendMessage(new TextMessage("���:" + replyArr[2]));
			}
		}
		// ä�� �˸��޼����� ���
		else if (replyArr[0].equals("�˸�")) {
			//replyArr[1] = ä�� �޴»��
			if(idMap.get(replyArr[1]) != null){
			idMap.get(replyArr[1]).sendMessage(new TextMessage("�˸�:" + replyArr[2] + ":" + replyArr[3]
					+ ":" + replyArr[4] + ":" + replyArr[5]));
			}
		}
		// ������� 1:1 ä�� ������ ���
		else {
			String msgArr[] = msg.split("#/separator/#");
			// msgArr[0] = errandsNum(�ɺθ� ��ȣ);
			// msgArr[1] = sender(������ ���);
			// msgArr[2] = msg(�޼��� ����);
			// msgArr[3] = writeday(���� �ð�);
			errandsNum = msgArr[0];
			
			if (sessionMap.get(errandsNum) == null) { // ä�ù��� ��������� �ʾҴٸ�
				List<WebSocketSession> list = new ArrayList<>();
				list.add(session);
				sessionMap.put(errandsNum, list); // �ش��ϴ� �ɺθ� ��ȣ�� ä�ù��� �����
			} else {
				if (!sessionMap.get(errandsNum).contains(session)) {
					sessionMap.get(errandsNum).add(session);
				}
			}
			if (msg.length() > 20) { // ���ο��� ä���� �ϴ� ���
				chatService.write(msgArr); // ä�ñ���� ������ �����Ų��
				//�ȵ���̵�� web�������� ����� ���� �ȵ���̵� ä�ü����� �޼����� ������.
				List<ServerThread> androidList = AndroidChatServer.socketMap.get(errandsNum);
				if (androidList != null) {
					for (ServerThread th : androidList) {
						th.pw.println(msgArr[1] + ":" + msgArr[2] + ":" + msgArr[3]);
					}
				}
			}
			// ��Ŭ���̾�Ʈ���� �޼����� ������ 
			List<WebSocketSession> list = sessionMap.get(errandsNum);
			for (WebSocketSession sess : list) {
				if (msg.length() > 20) {
					sess.sendMessage(new TextMessage(msg));
				}
			}

		}

	}
}
