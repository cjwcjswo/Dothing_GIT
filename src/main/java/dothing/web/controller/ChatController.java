package dothing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

	@RequestMapping("/websocket")
	public String webSocket(){
		return "chat/websocket";
	}
}
