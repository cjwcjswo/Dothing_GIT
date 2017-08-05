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
 * 클라이언트로부터 웹소켓 메세지가 왔을때 처리하는 Handler
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	/**
	 * 웹에서 1:1 채팅에서 상대방의 세션정보를 저장하는 Map
	 * Map String 채팅방 번호, List 웹소켓세션 리스트
	 */
	public static Map<String, List<WebSocketSession>> sessionMap = new HashMap<>();
	
	/**
	 * 웹에서 특정 사용자에게 알림을 보낼때 상대방의 세션정보를 저장하는 Map
	 * Map String 상대방 id, WebSocketSession 해당하는 웹소켓세션
	 */
	public static Map<String, WebSocketSession> idMap = new HashMap<>();

	@Autowired
	private ChatService chatService;
	private String errandsNum; // 심부름 번호(채팅방 번호)

	/**
	 * 해당하는 웹소켓세션이 현재 웹사이트에서 로그인 되었는가 처리
	 * @param session 클라이언트의 웹소켓 세션
	 * @return true 로그인 됨, false 로그인 안됨
	 */
	private boolean isAuthenticated(WebSocketSession session) {
		if (session.getPrincipal() == null) return false; // 로그인이 안됬을 경우
		return true;
	}
	
	/**
	 * Principal로 부터 id값을 return하는 메소드
	 * @param principal 해당하는 유저의 principal 값
	 * @return 유저의 아이디
	 */
	private String getUserId(Principal principal) {
		String[] idArr = principal.getName().split("=");
		String[] resultIdArr = idArr[1].split(",");
		return resultIdArr[0];
	}
	
	/**
	 * 클라이언트가 웹서버로 웹소켓을 통해 접속했을 경우 처리
	 * @param session 클라이언트의 웹소켓 세션
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		System.out.println("CONNECTED: " + session.getId());
		
		// 해당하는 클라이언트가 로그입 되었을 시 idMap에 클라이언트 세션 정보를 저장한다.
		if (isAuthenticated(session)) idMap.put(getUserId(session.getPrincipal()), session);
	}

	/**
	 * 클라이언트가 소켓연결을 끊었을경우(페이지 이동..)
	 * @param session 클라이언트의 웹소켓 세션
	 * @param status 종료 상태 정보
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
	 * 안드로이드 채팅 서버로부터 웹소켓으로 채팅메세지를 보내기 위한 메소드
	 * @param msg 소켓의 메세지 값
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
	 * 안드로이드 채팅서버로부터 웹소켓으로 채팅이 왔다는 알림메세지를 보내기 위한 메소드
	 * @param msg 소켓의 메세지 값
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

	/**
	 * 웹 클라이언트로부터 웹소켓 메시지가 왔을때 처리하는 메소드
	 * @param session 메세지를 보낸 클라이언트 세션
	 * @param message 해당하는 메세지
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload(); // 메세지에 담긴 payload 불러오기
		System.out.println("msg: " + msg);

		String replyArr[] = msg.split(":"); // 메세지를 ":"구분자로 분리함
		//replyArr[0] = 심부름 종류
		
		// 심부름 알림메세지일 경우
		if (replyArr[0].equals("심부름")) { 
			Iterator<String> iter = idMap.keySet().iterator();
			// idMap에 저장된 모든 세션에게 알림메세지를 보낸다
			while (iter.hasNext()) {
				WebSocketSession wb = idMap.get(iter.next());
				if ((wb != null) && !wb.getId().equals(session.getId())) {
					wb.sendMessage(new TextMessage("심부름:" + replyArr[1] + ":" + replyArr[2] + ":" + replyArr[3]));
				}
			}
		} 
		// 심부름 매칭 알림 메세지일 경우
		else if (replyArr[0].equals("선택")) { 
			//replyArr[1] = 매칭한 사람
			//replyArr[2] = 매칭된 상태방
			if(idMap.get(replyArr[2]) != null){
			idMap.get(replyArr[2]).sendMessage(new TextMessage("선택:" + replyArr[1]));
			}
		}
		// 댓글 알림 메세지일 경우
		else if (replyArr.length == 3 && replyArr[0].equals("댓글")) {
			// replyArr[1] = 글쓴이
			// replyArr[2] = 댓글 단 사람
			if(idMap.get(replyArr[1]) != null){
			idMap.get(replyArr[1]).sendMessage(new TextMessage("댓글:" + replyArr[2]));
			}
		}
		// 채팅 알림메세지일 경우
		else if (replyArr[0].equals("알림")) {
			//replyArr[1] = 채팅 받는사람
			if(idMap.get(replyArr[1]) != null){
			idMap.get(replyArr[1]).sendMessage(new TextMessage("알림:" + replyArr[2] + ":" + replyArr[3]
					+ ":" + replyArr[4] + ":" + replyArr[5]));
			}
		}
		// 상대방과의 1:1 채팅 내용일 경우
		else {
			String msgArr[] = msg.split("#/separator/#");
			// msgArr[0] = errandsNum(심부름 번호);
			// msgArr[1] = sender(보내는 사람);
			// msgArr[2] = msg(메세지 내용);
			// msgArr[3] = writeday(보낸 시간);
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
