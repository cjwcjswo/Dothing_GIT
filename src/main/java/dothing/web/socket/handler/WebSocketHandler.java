package dothing.web.socket.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import dothing.web.service.ChatService;

@Component
public class WebSocketHandler extends TextWebSocketHandler{
	
	Map<String, List<WebSocketSession>> sessionMap = new HashMap<>();
	
	@Autowired
	private ChatService chatService;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		Map<String, Object> map = session.getAttributes();
		String errandsNum = (String) map.get("errandsNum");
		
		if(sessionMap.get(errandsNum) == null){
			List<WebSocketSession> list = new ArrayList<>();
			list.add(session);
			sessionMap.put(errandsNum, list);
		}else{
			sessionMap.get(errandsNum).add(session);
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		Map<String, Object> map = session.getAttributes();
		String errandsNum = (String) map.get("errandsNum");
		sessionMap.remove(errandsNum);
	}
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		if(msg.equals("새로운 심부름이 등록되었습니다.")){
			System.out.println("msg : " + msg);
			Iterator<String> iter = sessionMap.keySet().iterator();
			while(iter.hasNext()){
				List<WebSocketSession> list =  sessionMap.get(iter.next());
				for(WebSocketSession sess : list){
					sess.sendMessage(new TextMessage(msg));
				}
			}
		}else{
			Map<String, Object> map = session.getAttributes();
			String errandsNum = (String) map.get("errandsNum");
			
			String msgArr[] = msg.split("#/separator/#");
			//msgArr[0] = errandsNum;
			//msgArr[1] = sender;
			//msgArr[2] = msg;
			//msgArr[3] = writeday;
			
			chatService.write(msgArr);
			
			List<WebSocketSession> list = sessionMap.get(errandsNum);
			for(WebSocketSession sess : list){
				sess.sendMessage(new TextMessage(msg));
			}
			
		}
		
	}
}
