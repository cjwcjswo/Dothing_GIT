package dothing.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		System.out.println("A");
		return "home";
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
<<<<<<< HEAD
	@RequestMapping("mapTest")
	public void a(){}
=======
>>>>>>> 42f3cf7c418456e7be38f50dec8e780ca6628bff
	@RequestMapping("signIn")
	public String signIn(){
		return "signIn" ; 
	}
<<<<<<< HEAD
=======

>>>>>>> 42f3cf7c418456e7be38f50dec8e780ca6628bff
}
