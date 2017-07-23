package dothing.web.android.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		if (email == null || password == null) {
			System.out.println("�Է°� ����");
			return null;
		}
		MemberDTO memberDTO = memberService.selectMemberById(email);

		if (memberDTO == null) {
			System.out.println("�׷������");
			return null;
		}

		if (!passwordEncoder.matches(password, memberDTO.getPassword())) {
			System.out.println("�������ġ");
			return null;
		}
		if (token != null) {
			androidService.insertToken(memberDTO.getUserId(), token);
			System.out.println("��ū �߰�: " + token);
		}
		
		return memberDTO;
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