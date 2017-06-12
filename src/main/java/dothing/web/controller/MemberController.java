package dothing.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dothing.web.dto.MemberDTO;
import dothing.web.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	/**
	 * 가입 폼
	 * */
	@RequestMapping("/signIn")
	public String joinForm(){
		return "/user/signIn";
	}
	
	/**
	 * 가입하기
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * */

	@RequestMapping("/join")
	public String join(HttpSession session, MemberDTO member) throws IllegalStateException, IOException{
		//insert 호출 

		String path = session.getServletContext().getRealPath("") + "\\user\\" + member.getUserId();
		File folder = new File(path);
		folder.mkdirs();
		MultipartFile selfImgFile = member.getSelfImgFile();
		member.setSelfImg(selfImgFile.getOriginalFilename());
		selfImgFile.transferTo(new File(path + "\\" + member.getSelfImg()));
		memberService.joinMember(member);
	
		return "/main/home";


	}
	
	/**
	 * 로그인 폼
	 * */
	@RequestMapping("/loginForm")
	public String loginForm(){
		return "/user/loginForm";
	}
	
	
	
}
