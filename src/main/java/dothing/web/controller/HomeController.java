package dothing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		System.out.println("A");
		return "/main/home";
	}

	@RequestMapping("/user/loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}
	
	@RequestMapping("/errand/mapTest")
	public String a(){
		return "/errand/mapTest";
	}

	@RequestMapping("/user/signIn")
	public String signIn(){
		return "/user/signIn" ; 
	}

	
	@RequestMapping("/errand/chat")
	public String chat(){
		return "/errand/chat" ; 
	}
	

	

}
