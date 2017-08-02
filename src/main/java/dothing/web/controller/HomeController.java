package dothing.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * �⺻ ������ ��Ʈ�ѷ�
 */
@Controller
public class HomeController {

	/**
	 * Ȩȭ������ �̵�
	 */
	@RequestMapping("/")
	public String home() {
		return "main/home";
	}

	/**
	 * ���������� ó��
	 * @param request �����޼����� ��� ����
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
