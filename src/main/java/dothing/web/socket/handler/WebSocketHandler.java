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
 * @brief: 클라이언트로부터 웹소켓 메세지가 왔을때 처리하는 Handler
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	/**
	 * 웹에서 1:1 채팅에서 상대방의 세션정보를 저장하는 Map
	 * Map<String, List<webSocketsession>: String 채팅방 번호, List<WebSocketSession> 웹소켓세션 리스트
	 */
	public static Map<String, List<WebSocketSession>> sessionMap = new HashMap<>();
	
	/**
	 * 웹에서 특정 사용자에게 알림을 보낼때 상대방의 세션정보를 저장하는 Map
	 * Map<String, WebSocketSession>: String 상대방 id, WebSocketSession 해당하는 웹소켓세션
	 */
	public static Map<String, WebSocketSession> idMap = new HashMap<>();

	@Autowired
	private ChatService chatService;
	private String errandsNum; // 심부름 번호(채팅방 번호)

	/**
	 * @brief: 해당하는 웹소켓세션이 현재 웹사이트에서 로그인 되었는가 처리
	 * @param WebSocketSession session: 클라이언트의 웹소켓 세션
	 * @return boolean: true 로그인 됨, false 로그인 안됨
	 */
	private boolean isAuthenticated(WebSocketSession session) {
		if (session.getPrincipal() == null) return false; // 로그인이 안됬을 경우
		return true;
	}
	
	/**
	 * @brief: Principal로 부터 id값을 return하는 메소드
	 * @param Principal principal: 해당하는 유저의 principal 값
	 * @return String: 유저의 아이디
	 */
	private String getUserId(Principal principal) {
		String[] idArr = principal.getName().split("=");
		String[] resultIdArr = idArr[1].split(",");
		return resultIdArr[0];
	}
	
	/**
	 * @brief: 클라이언트가 웹서버로 웹소켓을 통해 접속했을 경우 처리
	 * @param WebSocketSession session: 클라이언트의 웹소켓 세션
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		System.out.println("CONNECTED: " + session.getId());
		
		// 해당하는 클라이언트가 로그입 되었을 시 idMap에 클라이언트 세션 정보를 저장한다.
		if (isAuthenticated(session)) idMap.put(getUserId(session.getPrincipal()), session);
	}

	/**
	 * @brief: 클라이언트가 소켓연결을 끊었을경우(페이지 이동..)
	 * @param WebSocketSession session: 클라이언트의 웹소켓 세션
	 * @param status: 종료 상태 정보
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("CLOSED: " + session.getId());
		
		// 로그인 되어있을 시 idMap에서 제거
		if (isAuthenticated(session)) { 
			idMap.remove(getUserId(session.getPrincipal()));
		}
		
		// 채팅방이 있을경우 sessionMap에서 제거
		if (sessionMap.get(errandsNum) != null) {
			sessionMap.get(errandsNum).remove(session);
			if (sessionMap.get(errandsNum).isEmpty()) sessionMap.remove(errandsNum);
		}
	}

	
	/**
	 * @brief: 안드로이드 채팅 서버로부터 웹소켓으로 채팅메세지를 보내기 위한 메소드
	 * @param String msg: 소켓의 메세지 값
	 */
	public static void sendWebsocketMessage(String msg) throws IOException {
		String msgArr[] = msg.split("#/separator/#");
		// msgArr[0] = errandsNum(채팅방 번호);
		// msgArr[1] = sender(보내는 사람);
		// msgArr[2] = msg(채팅 내용);
		// msgArr[3] = writeday(보내는 시간);
		
		String errandsNum = msgArr[0];
		System.out.println("현재 세션맵: " + sessionMap);
		
		// errandsNum에 해당하는 웹소켓 세션리스트(채팅방)을 불러온다
		List<WebSocketSession> list = sessionMap.get(errandsNum); 
		System.out.println("[채팅]현재 웹 접속: " + list);
		
		// 채팅방이 있을경우(접속한 상대가 존재 할 경우)
		if (list != null) {
			for (WebSocketSession sess : list) {
				if (msg.length() > 20) sess.sendMessage(new TextMessage(msg)); // 메세지 보내기	
			}
		}
	}

	/**
	 * @brief: 안드로이드 채팅서버로부터 웹소켓으로 채팅이 왔다는 알림메세지를 보내기 위한 메소드
	 * @param String msg: 소켓의 메세지 값
	 */
	public static void sendAlert(String msg) throws IOException {
		String msgArr[] = msg.split(":");
		// msgArr[0] = errandsNum(채팅방 번호);
		// msgArr[1] = 받는사람;
		// msgArr[2] = msg(채팅 내용);
		// msgArr[3] = writeday(보내는 시간);
		WebSocketSession session = idMap.get(msgArr[1]);
		System.out.println("[알람]현재 웹 접속: " + session);
		if (session != null) {
			session.sendMessage(new TextMessage("알림:" + msgArr[2] + ":" + msgArr[3]
					+ ":" + msgArr[4] + ":" + msgArr[5]));
		}
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		System.out.println("msg: " + msg);

		String replyArr[] = msg.split(":"); // 
		if (replyArr[0].equals("심부름")) { // 심부름 알림메세지
			Iterator<String> iter = idMap.keySet().iterator();
			while (iter.hasNext()) {
				WebSocketSession wb = idMap.get(iter.next());
				if ((wb != null) && !wb.getId().equals(session.getId())) {
					wb.sendMessage(new TextMessage("심부름:" + replyArr[1] + ":" + replyArr[2] + ":" + replyArr[3]));
				}
			}
		} else if (replyArr[0].equals("선택")) { // 심부름 매칭 알림 메세지
			if(idMap.get(msg.split(":")[2]) != null){
			idMap.get(msg.split(":")[2]).sendMessage(new TextMessage("선택:" + msg.split(":")[1]));
			}
		} else if (replyArr.length == 3 && replyArr[0].equals("댓글")) { // 댓글 알림 메세지
			if(idMap.get(msg.split(":")[1]) != null){
			idMap.get(msg.split(":")[1]).sendMessage(new TextMessage("댓글:" + msg.split(":")[2]));
			}
		} else if (replyArr[0].equals("알림")) { // 채팅 알림메세지
			if(idMap.get(msg.split(":")[1]) != null){
			idMap.get(msg.split(":")[1]).sendMessage(new TextMessage("알림:" + msg.split(":")[2] + ":" + msg.split(":")[3]
					+ ":" + msg.split(":")[4] + ":" + msg.split(":")[5]));
			}
		} else {// 상대방과의 채팅
			String msgArr[] = msg.split("#/separator/#");
			// msgArr[0] = errandsNum;
			// msgArr[1] = sender;
			// msgArr[2] = msg;
			// msgArr[3] = writeday;
			errandsNum = msgArr[0];
			if (sessionMap.get(errandsNum) == null) { // 채팅방이 만들어지지 않았다면
				List<WebSocketSession> list = new ArrayList<>();
				list.add(session);
				sessionMap.put(errandsNum, list); // 해당하는 심부름 번호의 채팅방을 만든다
			} else {
				if (!sessionMap.get(errandsNum).contains(session)) {
					sessionMap.get(errandsNum).add(session);
				}
			}
			if (msg.length() > 20) { // 서로에게 채팅을 하는 경우
				chatService.write(msgArr); // 채팅기록을 서버에 저장시킨다
				//안드로이드와 web서버간의 통신을 위해 안드로이드 채팅서버에 메세지를 보낸다.
				List<ServerThread> androidList = AndroidChatServer.socketMap.get(errandsNum);
				if (androidList != null) {
					for (ServerThread th : androidList) {
						th.pw.println(msgArr[1] + ":" + msgArr[2] + ":" + msgArr[3]);
					}
				}
			}
			// 웹클라이언트에게 메세지를 보낸다 
			List<WebSocketSession> list = sessionMap.get(errandsNum);
			for (WebSocketSession sess : list) {
				if (msg.length() > 20) {
					sess.sendMessage(new TextMessage(msg));
				}
			}

		}

	}
}
