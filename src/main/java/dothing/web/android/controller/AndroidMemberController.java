package dothing.web.android.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public MemberDTO android(HttpServletRequest request) {
		String email = request.getParameter("userId");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		
		if (email == null || password == null) {
			System.out.println("입력값 부족");
			return null;
		}
		MemberDTO memberDTO = memberService.selectMemberById(email);

		if (memberDTO == null) {
			System.out.println("그런얘읎다");
			return null;
		}

		if (!passwordEncoder.matches(password, memberDTO.getPassword())) {
			System.out.println("비번노일치");
			return null;
		}
		if (token != null) {
			androidService.insertToken(memberDTO.getUserId(), token);
			System.out.println("토큰 추가: " + token);
		}
		
		return memberDTO;
	}
	
	@RequestMapping("/apiCheckId")
	@ResponseBody
	public MemberDTO apiCheckId(HttpServletRequest request,MemberDTO memberDTO) throws Exception{
		String email = memberDTO.getUserId();
		String password = memberDTO.getPassword();
		String token = request.getParameter("token");
		String facebook = request.getParameter("facebook");
		
		if (email == null || password == null) {
			System.out.println("입력값 부족");
			return null;
		}
		MemberDTO resultMemberDTO = memberService.selectMemberById(email);

		if (resultMemberDTO == null && facebook == null) {
			System.out.println("그런얘읎다");
			return null;
		}
		if(facebook != null){
			System.out.println("*********"+password);
			System.out.println("facebook 들어옴");
			URL url = new URL(memberDTO.getSelfImg());
			BufferedImage img = ImageIO.read(url);
			memberDTO.setSelfImg("profile.jpg");
			HttpSession session = request.getSession();
			String path = session.getServletContext().getRealPath("") + "\\users\\" + memberDTO.getUserId();
			File folder = new File(path);
			folder.mkdirs();
			File file = new File(path + "\\" + memberDTO.getSelfImg());
			ImageIO.write(img, "jpg", file);
			
			androidService.androidSignIn(memberDTO);	
			resultMemberDTO = memberService.selectMemberById(email);
		}

		if (!passwordEncoder.matches(password, resultMemberDTO.getPassword())) {
			System.out.println("비번노일치");
			return null;
		}
		if (token != null) {
			androidService.insertToken(resultMemberDTO.getUserId(), token);
			System.out.println("토큰 추가: " + token);
		}
		
		
		return resultMemberDTO;
	}

	@RequestMapping("/isSafety")
	public Map<String, Object> isSafety(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		MemberDTO member = memberService.selectMemberById(userId);
		boolean isSafety = false;
		for (String role : memberService.selectAuth(userId)) {
			if (role.equals("ROLE_SAFETY")) {
				isSafety = true;
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("ssn", member.getSsnImg());
		result.put("isSafety", isSafety);
		return result;
	}

	@RequestMapping("/submitSafety")
	public int submitSafety(HttpSession session, MemberDTO member) throws IllegalStateException, IOException {
		int result = 0;
		MultipartFile ssnImgFile = member.getSsnImgFile();
		member.setSsnImg(ssnImgFile.getOriginalFilename());
		result = memberService.updateSafety(member);
		if (result > 0) {
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId() + "\\ssn\\";
			File folder = new File(path);
			folder.mkdirs();
			ssnImgFile.transferTo(new File(path + "\\" + member.getSsnImg()));
		}
		return result;
	}
}
