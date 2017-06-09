package dothing.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dothing.web.dto.MemberDTO;
import dothing.web.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	/**
	 * 가입 폼
	 * */
	@RequestMapping("/user/signIn")
	public String joinForm(){
		return "/user/signIn";
	}
	
	/**
	 * 가입하기
	 * */
	@RequestMapping("/member/join")
	public String join(MemberDTO member){
		//insert 호출 
		memberService.joinMember(member);
<<<<<<< HEAD
		return "/main/main";
=======
		return "/main/home";
>>>>>>> 561fef30f44bc7081db372633063a3663b0c3068
	}
	
	/**
	 * 로그인 폼
	 * */
	@RequestMapping("/user/loginForm")
	public String loginForm(){
		return "/user/loginForm";
	}
	
	
	
}
