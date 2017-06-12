package dothing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	
	@RequestMapping("/errand/mapTest")
	public String a(){
		return "/errand/mapTest";
	}

	
	@RequestMapping("/errand/chat")
	public ModelAndView chat(String errandsNum){
		return new ModelAndView("/errand/chat", "errandsNum", errandsNum);
		//return "/errand/chat" ; 
	}
	
	@RequestMapping("/errand/register")
	public String register(){
		return "/errand/register" ; 
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
