package dothing.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.NotificationDTO;
import dothing.web.service.AdminMoneyService;
import dothing.web.service.ErrandsService;
import dothing.web.service.MemberService;
import dothing.web.util.PageMaker;

/**
 * 유저에 관한 컨트롤러
 */
@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ErrandsService errandsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminMoneyService adminMoneyService;

	/**
	 * 회원 가입 페이지로 이동
	 */
	@RequestMapping("/signIn")
	public String joinForm() {
		return "/user/signIn";
	}


	/**
	 * 회원 가입하기 프로세스
	 * @param session 프로필 사진 이미지 저장을위해 경로를 불러오기 위함
	 * @param member 회원 가입정보 DTO
	 * @param preAddr 주소
	 * @param detailAddr 상세주소
	 * @throws Exception
	 */
	@RequestMapping("/join")
	public String join(HttpSession session, MemberDTO member, String preAddr, String detailAddr) throws Exception {

		// 제약조건 체크
		MultipartFile selfImgFile = member.getSelfImgFile();
		if (selfImgFile.getSize() == 0 || selfImgFile.getOriginalFilename() == null) {
			throw new Exception("프로필 사진을 첨부해주세요");
		}
		
		if (preAddr == null || detailAddr == null || preAddr.equals("") || detailAddr.equals("")) {
			throw new Exception("주소란을 확인해주세요.");
		}
		if (member.getName() == null || member.getUserId() == null || member.getPassword() == null) {
			throw new Exception("기입하지 않은 정보가 있습니다");
		}

		if (member.getSex() == null){
			throw new Exception("성별을 선택하세요");
		}
		
		member.setSelfImg(selfImgFile.getOriginalFilename());

		// 이미지 확장자 검사
		String ext = (member.getSelfImg().split("\\."))[1];
		ext = ext.toLowerCase();
		if (!(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif"))) {
			throw new Exception("확장자가 jpg, jpeg, png, gif인 파일만 업로드 할 수 있습니다");
		}
		
		
		memberService.joinMember(member, false);
		
		// 프로필 사진 저장
		if (member.getSelfImg() != null && !member.getSelfImg().trim().equals("")) {
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId();
			File folder = new File(path);
			folder.mkdirs();
			selfImgFile.transferTo(new File(path + "\\" + member.getSelfImg()));
		}
		return "/user/okay";
	}

	/**
	 * 로그인 화면으로 이동
	 */
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}

	/**
	 * ID 중복 체크
	 * @param userId 중복 체크할 아이디
	 */
	@RequestMapping("/check")
	public ResponseEntity<String> checkId(String userId) {
		String result = memberService.selectSearch(userId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "text/html;charset=UTF-8");
		ResponseEntity<String> re = new ResponseEntity<String>(result, headers, HttpStatus.OK);

		return re;
	}

	
	/**
	 * 마이페이지로 이동
	 * @param aut 로그인 한 유저 정보
	 */
	@RequestMapping("/myPage")
	public ModelAndView myPage(Authentication aut) {
		ModelAndView mv = new ModelAndView();
		String id = ((MemberDTO) aut.getPrincipal()).getUserId();
		MemberDTO member = memberService.selectMemberById(id);
		member.setGpaList(errandsService.selectGPAById((member.getUserId())));
		member.setHashList(memberService.selectHashtag(member.getUserId()));
		mv.addObject("member", member);
		mv.setViewName("/user/myPage");
		return mv;
	}

	/**
	 * 개인정보 수정
	 * @param session 프로필 사진 수정할때 서버 경로 획득
	 * @param aut 로그인 한 유저 정보
	 * @param updateMember 업데이트 할 정보 dto
	 * @param currentPassword 현재 비밀번호
	 * @param newPassword 새로운 비밀번호
	 * @param renewPassword 새로운 비밀번호 확인 입력
	 */
	@RequestMapping("/update")
	public ModelAndView update(HttpSession session, Authentication aut, MemberDTO updateMember, String currentPassword,
			String newPassword, String renewPassword) throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberDTO member = (MemberDTO) aut.getPrincipal();
		// 제약조건 체크
		if (!passwordEncoder.matches(currentPassword, member.getPassword())) {
			throw new Exception("비밀번호가 맞지 않습니다!");
		}
		if (!renewPassword.equals(newPassword)) {
			throw new Exception("새로운 비밀번호를 다시 입력해주세요");
		}
		if (updateMember.getPreAddr() != null && !updateMember.getPreAddr().equals("")) {
			if (updateMember.getDetailAddr() == null || updateMember.getDetailAddr().equals("")) {
				throw new Exception("상세 주소를 입력하세요");
			}
		} else {
			updateMember.setPreAddr(null);
		}

		MultipartFile newProfile = updateMember.getSelfImgFile();
		//프로필 사진을 바꾸는경우
		if ((newProfile.getOriginalFilename() != null && newProfile.getSize() != 0)) {
			// 파일 이름 획득 후 서버 경로에 저장
			updateMember.setSelfImg(newProfile.getOriginalFilename());
			member.setSelfImg(newProfile.getOriginalFilename());
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId();
			File folder = new File(path);
			folder.mkdirs();
			newProfile.transferTo(new File(path + "\\" + updateMember.getSelfImg()));
		} else {
			updateMember.setSelfImg(null);
		}
		
		//비밀번호를 바꾸는경우
		if (!(newPassword == null || newPassword.equals(""))) {
			updateMember.setPassword(newPassword);
		}
		updateMember.setUserId(member.getUserId());
		memberService.updateMember(updateMember, member);
		mv.setViewName("redirect:/user/myPage");
		return mv;
	}

	/**
	 * 안전 심부름꾼 신청 페이지로
	 * @param aut 로그인한 유저 정보
	 */
	@RequestMapping("/safetyRegister")
	public ModelAndView safetyRegister(Authentication aut) {
		boolean isSafety = false;
		MemberDTO currentUser = (MemberDTO) aut.getPrincipal();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/safetyRegister");
		mv.addObject("member", memberService.selectMemberById(currentUser.getUserId()));
		// 로그인 한 유저가 안전 심부름꾼인가 판단
		for (String role : memberService.selectAuth(currentUser.getUserId())) {
			if (role.equals("ROLE_SAFETY")) {
				isSafety = true;
			}
		}
		mv.addObject("isSafety", isSafety);
		return mv;
	}

	/**
	 * 안전 심부름꾼 등록 프로세스
	 * @param session 안전심부름꾼 이미지 등록 할 경우 서버 경로 획득
	 * @param aut 로그인한 유저 정보
	 * @param member 업데이트 할 유저 dto
	 */
	@RequestMapping("/submitSafety")
	public String submitSafety(HttpSession session, Authentication aut, MemberDTO member) throws Exception {
		MultipartFile ssnImgFile = member.getSsnImgFile();
		if (ssnImgFile == null || ssnImgFile.getSize() == 0) {
			throw new Exception("사진을 넣어주세요!");
		}
		
		member.setUserId(((MemberDTO) aut.getPrincipal()).getUserId());
		member.setSsnImg(ssnImgFile.getOriginalFilename());
		memberService.updateSafety(member);
		
		// 안전심부름꾼 사진 서버경로로 파일 저장
		String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId() + "\\ssn\\";
		File folder = new File(path);
		folder.mkdirs();
		ssnImgFile.transferTo(new File(path + "\\" + member.getSsnImg()));

		return "redirect:/user/safetyRegister";
	}

	/**
	 * 유저가 받은 알림목록 불러오기
	 * @param aut 로그인한 유저 정보
	 * @param page 페이지
	 * @return
	 */
	@RequestMapping("/alert")
	public ModelAndView alert(Authentication aut, Integer page) {
		if(page == null) page = 1;
		MemberDTO member = (MemberDTO) aut.getPrincipal();

		List<NotificationDTO> alertList = memberService.selectNotificationById(member.getUserId(), page);

		// 10개의 알림을 1개의 페이지로 묶음
		PageMaker pm = new PageMaker(page, memberService.countNotification(member.getUserId())/ 11 + 1);
		pm.start();
		
		ModelAndView mv = new ModelAndView();
		
		memberService.allRead(member.getUserId()); // 모든 알림 읽음 처리
		mv.setViewName("/user/alert");
		mv.addObject("alertList", alertList);
		mv.addObject("pm", pm);
		return mv;
	}


	/**
	 * 마이페이지 포인트 페이지로 이동
	 * @param auth 로그인 한 유저 정보
	 */
	@RequestMapping("/charge")
	public ModelAndView charge(Authentication auth) {
		ModelAndView mv = new ModelAndView();
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();

		//포인트 사용내역
		List<ErrandsDTO> list = adminMoneyService.pointList(userId);
		List<ErrandsDTO> successList = adminMoneyService.searchPointSuccess(userId);
		mv.setViewName("/user/charge");
		mv.addObject("list", list);
		mv.addObject("successList", successList);
		return mv;
	}
	
	
	/**
	 * 마이페이지 포인트 충전 프로세스
	 * @param auth 로그인 한 유저 정보
	 * @param select 충전 할 포인트 금액
	 * @param way card = 카드 충전, bandBook = 무 통장 입금
	 * @return
	 */
	@RequestMapping("/pointCharge")
	public ModelAndView pointCharge(Authentication auth, String select, String way){
		
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		int value = 0;
		ModelAndView mv = new ModelAndView();
		// 무통장 입금일 경우
		if(way.equals("bandBook")){
			value = Integer.parseInt(select);
			adminMoneyService.pointChargeBandBook(userId, value);
			mv.setViewName("/user/charge");
		}
		// 카드 충전 일 경우
		else if(way.equals("card")){
			value = Integer.parseInt(select);
			adminMoneyService.pointChargeCard(userId, value);
			
			MemberDTO currentUser = (MemberDTO) auth.getPrincipal();
			((MemberDTO) auth.getPrincipal()).getPoint().setCurrentPoint(currentUser.getPoint().getCurrentPoint()+value);
			
			mv.setViewName("/user/charge");
		}
		return mv;
	}
	
	/**
	 * 로그인 API로 최초 접속했을 때 추가 정보 입력
	 * @param id 로그인 API 아이디
	 * @param photo 로그인 API 프로필 사진
	 * @param email 로그인 API 이메일
	 * @param gender 로그인 API 성별
	 * @param name 로그인 API 이름
	 */
	@RequestMapping("/addInformation")
	public ModelAndView loginAPI(String id, String photo, String email, String gender, String name){

		ModelAndView mv = new ModelAndView();
		MemberDTO member = memberService.selectMemberById(email);
		// 기존에 연동된 회원이 추가정보를 입력받지 않았을 경우(가입되지 않았을경우)
		if(member == null) { 
			mv.addObject("id", email);
			mv.addObject("photo", photo);
			mv.addObject("pw",id);
			
			if(gender.equals("male")) gender = "man";
			else gender = "woman";
			
			mv.addObject("gender", gender);
			mv.addObject("name", name);
			mv.setViewName("/user/loginAPI");
		}
		// 이미 가입된 경우
		else{
			mv.addObject("id", email);
			mv.addObject("password", id);
			mv.setViewName("/user/empty");
		}
		return mv;
	}
	
	/**
	 * 로그인 API로 회원가입 할 때 프로세스
	 * @param session 프로필 사진 저장시킬 서버 경로 획득
	 * @param member 가입시킬 회원 정보 dto
	 * @param photo 프로필 사진
	 */
	@RequestMapping("/apiControl")
	public String apiControl(HttpSession session, MemberDTO member, String photo) throws Exception{

		// URL 이미지 파일을 저장함
		URL url = new URL(photo);
		BufferedImage img = ImageIO.read(url);
		member.setSelfImg("profile.jpg");
		String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId();
		File folder = new File(path);
		folder.mkdirs();
		File file = new File(path + "\\" + member.getSelfImg());
		ImageIO.write(img, "jpg", file);
		
		memberService.joinMember(member, true);
		return "/user/okay";
	}
	
	/**
	 * 가입 완료 페이지
	 */
	@RequestMapping("/okay")
	public void okay(){}
	
	/**
	 * API로 로그인 했을 때 아이디와 패스워드를 전달하는 페이지
	 */
	@RequestMapping("/empty")
	public void empty(){}
	
	/**
	 * 인증 메일 다시보내기
	 * @param userId 해당하는 유저 아이디
	 * @param num 인증 번호
	 */
	@RequestMapping("/sendMail")
	@ResponseBody
	public void sendMail(String userId, Integer num){
		memberService.sendEmail(userId, num);
	}
	
	/**
	 * 인증 번호 확인하기
	 * @param email 해당하는 유저 이메일
	 * @param authNum 인증 번호
	 */
	@RequestMapping("/emailOk")
	public String emailOk(String email, Integer authNum) throws Exception{
		MemberDTO member = memberService.selectMemberById(email);
		//인증번호가 다를경우
		if(member.getState().intValue() != authNum.intValue()){
			throw new Exception("인증번호가 다릅니다!");
		}
		memberService.finishEmail(email);
		return "/user/loginForm";
	}
}
