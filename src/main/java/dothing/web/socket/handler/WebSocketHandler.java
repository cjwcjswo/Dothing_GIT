package dothing.web.socket.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import dothing.web.service.ChatService;

@Component
public class WebSocketHandler extends TextWebSocketHandler{
	
	Map<String, WebSocketSession> sessionMap = new HashMap<>();
	
	@Autowired
	private ChatService chatService;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		sessionMap.remove(session.getId());
	}
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		
		String msg = message.getPayload();
		//msgArr[0] = errandsNum;
		//msgArr[1] = sender;
		//msgArr[2] = msg;
		String msgArr[] = msg.split("#/separator/#");
		
		chatService.write(msgArr);
		
		
		/*
		Iterator<String> iter= sessionMap.keySet().iterator();
		while(iter.hasNext()){
			sessionMap.get(iter.next()).sendMessage(new TextMessage(message.getPayload()));
		}*/
		
		//session.sendMessage(new TextMessage(message.getPayload()));
	}
}
