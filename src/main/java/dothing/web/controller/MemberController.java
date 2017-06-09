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
	 * ���� ��
	 * */
	@RequestMapping("/user/signIn")
	public String joinForm(){
		return "user/signIn";
	}
	
	/**
	 * �����ϱ�
	 * */
	@RequestMapping("/member/join")
	public String join(MemberDTO member){
		//insert ȣ�� 
		memberService.joinMember(member);
		return "main/main";
	}
	
	/**
	 * �α��� ��
	 * */
	@RequestMapping("/user/loginForm")
	public String loginForm(){
		return "user/loginForm";
	}
	
	
	
}
