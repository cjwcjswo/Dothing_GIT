package dothing.web.socket.handler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.sun.org.glassfish.external.statistics.impl.StatsImpl;

import dothing.web.service.ChatService;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	Map<String, List<WebSocketSession>> sessionMap = new HashMap<>();
	Map<String, WebSocketSession> idMap = new HashMap<>();

	@Autowired
	private ChatService chatService;

	private String errandsNum;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);

		System.out.println("CONNECTED: " + session.getId());
		if (isAuthenticated(session)) { // 로그인 되어있을 시 idMap에 ID / WEBSOCKET 형식으로
										// 추가
			idMap.put(getUserId(session.getPrincipal()), session);
		}

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("CLOSED: " + session.getId());
		if (isAuthenticated(session)) { // 로그인 되어있을 시 idMap에서 제거
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
	 * 로그인 되었나?
	 */
	boolean isAuthenticated(WebSocketSession session) {
		if (session.getPrincipal() == null) {
			return false;
		}
		return true;
	}

	/**
	 * 유저의 아이디 가져오기
	 */
	String getUserId(Principal principal) {
		String[] idArr = principal.getName().split("=");
		String[] resultIdArr = idArr[1].split(",");
		return resultIdArr[0];
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		String replyArr[] = msg.split(":");
		if (replyArr[0].equals("심부름")) {
			Iterator<String> iter = idMap.keySet().iterator();
			while (iter.hasNext()) {
				WebSocketSession wb = idMap.get(iter.next());
				if (!wb.getId().equals(session.getId())) {
					wb.sendMessage(new TextMessage("심부름:" + replyArr[1]));
				}
				
			}
		} else if (replyArr.length == 3 && replyArr[0].equals("댓글")) {
			idMap.get(msg.split(":")[1]).sendMessage(new TextMessage("댓글:" + msg.split(":")[2]));
		} else {
			String msgArr[] = msg.split("#/separator/#");
			// msgArr[0] = errandsNum;
			// msgArr[1] = sender;
			// msgArr[2] = msg;
			// msgArr[3] = writeday;
			errandsNum = msgArr[0];
			System.out.println("handler e no : " + errandsNum);

			System.out.println("receive msg : " + msg);

			if (sessionMap.get(errandsNum) == null) {
				List<WebSocketSession> list = new ArrayList<>();
				list.add(session);
				sessionMap.put(errandsNum, list);
			} else {
				if (!sessionMap.get(errandsNum).contains(session)) {
					sessionMap.get(errandsNum).add(session);
				}
			}

			if (msg.length() > 20) {
				chatService.write(msgArr);
			}

			List<WebSocketSession> list = sessionMap.get(errandsNum);
			for (WebSocketSession sess : list) {
				if (msg.length() > 20) {
					sess.sendMessage(new TextMessage(msg));
				}
			}

		}

	}
}
