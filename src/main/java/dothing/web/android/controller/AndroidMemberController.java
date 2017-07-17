package dothing.web.android.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dothing.web.dto.MemberDTO;
import dothing.web.service.AndroidService;
import dothing.web.service.MemberService;

@Controller
@RequestMapping("/androidMember")
public class AndroidMemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	AndroidService androidService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping("/checkId")
	@ResponseBody
	public MemberDTO android(HttpServletRequest request){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		if(email == null || password == null){
			System.out.println("입력값 부족");
			return null;
		}
		MemberDTO memberDTO = memberService.selectMemberById(email);
		
		if(memberDTO == null) {
			System.out.println("그런얘읎다");
			return null;
		}
		
		if(!passwordEncoder.matches(password, memberDTO.getPassword())){
			System.out.println("비번노일치");
			return null;
		}
		if(token != null){
		androidService.insertToken(memberDTO.getUserId(), token);
		System.out.println("토큰 추가: " + token);
		}
		return memberDTO;
	}
	
}
