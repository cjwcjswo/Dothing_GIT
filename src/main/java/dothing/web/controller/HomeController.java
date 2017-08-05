package dothing.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 기본 페이지 컨트롤러
 */
@Controller
public class HomeController {

	/**
	 * 홈화면으로 이동
	 */
	@RequestMapping("/")
	public String home() {
		return "main/home";
	}

	/**
	 * 에러페이지 처리
	 * @param request 에러메세지를 담기 위함
	 */
	@RequestMapping("/error")
	public ModelAndView error(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", request.getAttribute("errorMessage"));
		mv.setViewName("/error/errorMessage");
		return mv;
	}

	


	@RequestMapping("/etc/about-us")
	public String aboutUs(){
		
		return "/etc/about-us" ;
		
	}
	
	@RequestMapping("/etc/contact")
	public String contact(){
		return "/etc/contact" ;
	}
	
	
	@RequestMapping("/etc/terms-conditions")
	public String termsConditions(){
		return "/etc/terms-conditions" ;
	}



}
