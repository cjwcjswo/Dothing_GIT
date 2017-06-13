package dothing.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(HttpSession session) {
		return "main/home";
	}

	@RequestMapping("/error")
	public ModelAndView error(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", request.getAttribute("errorMessage"));
		mv.setViewName("/error/errorMessage");
		return mv;
	}

	
	@RequestMapping("/errand/chat")
	public ModelAndView chat(String errandsNum){
		return new ModelAndView("/errand/chat", "errandsNum", errandsNum);
		//return "/errand/chat" ; 
	}
	


}
