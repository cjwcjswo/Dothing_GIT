package dothing.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(HttpSession session) {
		session.setAttribute("userIdS", "tester");
		return "/main/home";
	}

	
	@RequestMapping("/errand/mapTest")
	public String a(){
		return "/errand/mapTest";
	}

	
	@RequestMapping("/errand/chat")
	public String chat(){
		return "/errand/chat" ; 
	}
	
	@RequestMapping("/board/inquiryBoardList")
	public String inquiryBoardList(){
		return "/board/inquiryBoardList" ;
	}
	
	@RequestMapping("/board/inquiryBoardRead")
	public String inquiryBoardRead(){
		return "/board/inquiryBoardRead" ; 
	}
	
	@RequestMapping("/board/inquiryBoardWrite")
	public String inquiryBoardWrite(){
		return "/board/inquiryBoardWrite" ; 
	}
	

	

}
