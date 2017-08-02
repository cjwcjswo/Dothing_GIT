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

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.service.AndroidService;
import dothing.web.service.ErrandsService;
import dothing.web.service.MemberService;

/**
 * 안드로이드  스프링 유저 관련 컨트롤러
 */
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

	/**
	 * 회원 가입 프로세스
	 * @param request 프로필 사진 저장시킬 서버 경로 획득
	 * @param memberDTO 가입 시킬 멤버 정보 dto
	 */
	@RequestMapping("/signIn")
	@ResponseBody
	public Map<String,String> signIn(HttpServletRequest request,MemberDTO memberDTO) throws Exception{
		MultipartFile file = memberDTO.getSelfImgFile();
		// 프로필 사진 저장
		if(file != null){
			memberDTO.setSelfImg(file.getOriginalFilename());
			String path = request.getRealPath("/")+"/users/"+memberDTO.getUserId();
			File createFile = new File(path);
			if(!createFile.exists()){
				createFile.mkdirs();
			}
			file.transferTo(new File(path+"/"+file.getOriginalFilename()));
		}
		
		
		String result = androidService.androidSignIn(memberDTO)+"";
		
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("result", result);
		return map;
	}
	
	/**
	 * 인증 메일 보내기
	 * @param email 보낼 이메일 주소
	 */
	@RequestMapping("/sendEmail")
	@ResponseBody
	public String sendEmail(String email){
		return androidService.androidSendEmail(email);
	}
	
	/**
	 * 로그인 프로세스
	 * @param request 안드로이드에서 가져온 파라미터
	 */
	@RequestMapping("/checkId")
	@ResponseBody
	public MemberDTO android(HttpServletRequest request) {
		String email = request.getParameter("userId");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		
		// 제약조건 체크
		if (email == null || password == null) {
			return null;
		}
		MemberDTO memberDTO = memberService.selectMemberById(email);

		// 유젖 ㅓㅇ보가 없을 경우
		if (memberDTO == null) {
			return null;
		}

		// 비밀번호가 틀릴 경우
		if (!passwordEncoder.matches(password, memberDTO.getPassword())) {
			return null;
		}
		if (token != null) {
			androidService.insertToken(memberDTO.getUserId(), token);
		}
		return memberDTO;
	}
	
	
	/**
	 * 로그인 API 로그인 프로세스
	 * @param request 안드로이드에서 가져온 파라미터
	 * @param memberDTO 회원 정보 dto
	 */
	@RequestMapping("/apiCheckId")
	@ResponseBody
	public MemberDTO apiCheckId(HttpServletRequest request,MemberDTO memberDTO) throws Exception{
		String email = memberDTO.getUserId();
		String password = memberDTO.getPassword();
		String token = request.getParameter("token");
		String facebook = request.getParameter("facebook");
		
		// 제약조건 체크
		if (email == null || password == null) {
			return null;
		}
		MemberDTO resultMemberDTO = memberService.selectMemberById(email);

		// 유저 정보가 없을 경우
		if (resultMemberDTO == null && facebook == null) {
			return null;
		}
		
		// 페이스북으로 가입이 안되있을 경우 회원가입
		if(facebook != null){
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

		// 비밀번호가 일치하지 않을 경우
		if (!passwordEncoder.matches(password, resultMemberDTO.getPassword())) {
			return null;
		}

		if (token != null) {
			androidService.insertToken(resultMemberDTO.getUserId(), token);
		}
		
		
		return resultMemberDTO;
	}



	
	/**
	 * 안전 심부름꾼 등록 프로세스
	 * @param session 안전 심부름꾼 이미지 저장 경로 획득
	 * @param member 저장할 유저 정보 dto
	 */
	@RequestMapping("/submitSafety")
	@ResponseBody
	public int submitSafety(HttpSession session, MemberDTO member) throws IllegalStateException, IOException {
		int result = 0;
		MultipartFile ssnImgFile = member.getSsnImgFile();
		member.setSsnImg(ssnImgFile.getOriginalFilename());
		
		result = memberService.updateSafety(member);
		
		// 안전심부름꾼 이미지 저장
		if (result > 0) {
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId() + "\\ssn\\";
			File folder = new File(path);
			folder.mkdirs();
			ssnImgFile.transferTo(new File(path + "\\" + member.getSsnImg()));
		}
		return result;
	}

	/**
	 * GPS가 바뀔 때 마다 유저 위치정보 갱신
	 * @param request 안드로이드에서 가져온 파라미터 값
	 * @return
	 */
	@RequestMapping("/updateLocation")
	@ResponseBody
	public Integer updateLocation(HttpServletRequest request) {
		int result = 0;
		String memberId = (String) request.getParameter("id");
		String latitude = (String) request.getParameter("latitude");
		String longitude = (String) request.getParameter("longitude");
		result = androidService.updateLocation(memberId, latitude, longitude);
		
		return result;
	}

	/**
	 * 유저의 위치정보를 불러오는 컨트롤러
	 * @param request 안드로이드에서 가져온 파라미터
	 */
	@RequestMapping("/requestLocation")
	@ResponseBody
	public ChatPosDTO requestLocation(HttpServletRequest request) {
		int errandsNum = Integer.parseInt(request.getParameter("errandsNum"));
		String memberId = request.getParameter("id");
		ChatPosDTO chatPosDTO = androidService.selectLocation(errandsNum, memberId);
		System.out.println(chatPosDTO);
		return chatPosDTO;
	}
	
	/**
	 * 심부름 완료를 이미 했는지 안했는지 불러오기
	 * @param request 안드로이드에서 가져온 파라미터
	 */
	@RequestMapping("/isEvalFinish")
	@ResponseBody
	public boolean isEvalFinish(HttpServletRequest request){
		String errandsNum = request.getParameter("errandsNum");
		String isRequest = request.getParameter("isRequest");
		ErrandsDTO dto = errandsService.selectErrands(Integer.parseInt(errandsNum));
		
		
		// 요청자일 경우
		if(isRequest.equals("true")){
			String finishTime = dto.getFinishTime();
			if(finishTime == null) return true;
			else return false;
		}
		// 심부름꾼일 경우
		else{
			String arrivalTime = dto.getArrivalTime();
			if(arrivalTime == null) return true;
			else return false;
		}
	}
}
