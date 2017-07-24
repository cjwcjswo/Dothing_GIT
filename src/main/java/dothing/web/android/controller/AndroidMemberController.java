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

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.service.AndroidService;
import dothing.web.service.ErrandsService;
import dothing.web.service.MemberService;

@Controller
@RequestMapping("/androidMember")
public class AndroidMemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	AndroidService androidService;
	@Autowired
	ErrandsService errandsService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping("/checkId")
	@ResponseBody
	public MemberDTO android(HttpServletRequest request) {
		String email = request.getParameter("email");
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

	@RequestMapping("/isSafety")
	@ResponseBody
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
	@ResponseBody
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

	@RequestMapping("/updateLocation")
	@ResponseBody
	public Integer updateLocation(HttpServletRequest request) {
		int result = 0;
		String memberId = (String) request.getParameter("id");
		String latitude = (String) request.getParameter("latitude");
		String longitude = (String) request.getParameter("longitude");
		result = androidService.updateLocation(memberId, latitude, longitude);
		System.out.println(result + "등록 됨, " + latitude + ":" + longitude);
		return result;
	}

	@RequestMapping("/requestLocation")
	@ResponseBody
	public ChatPosDTO requestLocation(HttpServletRequest request) {
		int errandsNum = Integer.parseInt(request.getParameter("errandsNum"));
		String memberId = request.getParameter("id");
		System.out.println(errandsNum + " " + memberId + " -> requestLocation");
		ChatPosDTO chatPosDTO = androidService.selectLocation(errandsNum, memberId);
		System.out.println(chatPosDTO);
		return chatPosDTO;
	}
	
	@RequestMapping("/isEvalFinish")
	@ResponseBody
	public boolean isEvalFinish(HttpServletRequest request){
		String errandsNum = request.getParameter("errandsNum");
		String isRequest = request.getParameter("isRequest");
		System.out.println("[isEvalFinish] " + errandsNum + ":" + isRequest);
		ErrandsDTO dto = errandsService.selectErrands(Integer.parseInt(errandsNum));
		System.out.println(dto);
		if(isRequest.equals("true")){
			String finishTime = dto.getFinishTime();
			if(finishTime == null) return true;
			else return false;
		}else{
			String arrivalTime = dto.getArrivalTime();
			if(arrivalTime == null) return true;
			else return false;
		}
	}
}
