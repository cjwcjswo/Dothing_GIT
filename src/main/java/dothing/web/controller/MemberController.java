package dothing.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	 * */

	@RequestMapping("/join")
	public String join(HttpSession session, MemberDTO member) throws Exception{
		//insert 호출 
		
		MultipartFile selfImgFile = member.getSelfImgFile();
		member.setSelfImg(selfImgFile.getOriginalFilename());
		
		memberService.joinMember(member);
		if(member.getSelfImg() != null && !member.getSelfImg().trim().equals("")){
			String path = session.getServletContext().getRealPath("") + "\\user\\" + member.getUserId();
			File folder = new File(path);
			folder.mkdirs();
			selfImgFile.transferTo(new File(path + "\\" + member.getSelfImg()));
		}
		return "/main/home";
	}
	
	/**
	 * 로그인 폼
	 * */
	@RequestMapping("/loginForm")
	public String loginForm(){
		return "/user/loginForm";
	}
	
	/**
	 * ID 중복 체크
	 * */
	@RequestMapping("/check")
	public ResponseEntity<String> checkId(String userId){
		String result = memberService.selectSearch(userId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "text/html;charset=UTF-8");
		ResponseEntity<String> re = 
				new ResponseEntity<String>(result, headers, HttpStatus.OK);
		
		return re; 
	}
	
	
}
