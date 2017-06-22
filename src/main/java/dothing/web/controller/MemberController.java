package dothing.web.controller;

import java.io.File;
import java.util.List;

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
	 * ���� ��
	 */
	@RequestMapping("/signIn")
	public String joinForm() {
		return "/user/signIn";
	}

	/**
	 * �����ϱ�
	 */

	@RequestMapping("/join")
	public String join(HttpSession session, MemberDTO member, String preAddr, String detailAddr) throws Exception {
		// insert ȣ��

		MultipartFile selfImgFile = member.getSelfImgFile();
		if (selfImgFile.getSize() == 0 || selfImgFile.getOriginalFilename() == null) {
			throw new Exception("������ ������ ÷�����ּ���");
		}
		if (preAddr == null || detailAddr == null || preAddr.equals("") || detailAddr.equals("")) {
			throw new Exception("�ּҶ��� Ȯ�����ּ���.");
		}
		if (member.getName() == null || member.getUserId() == null || member.getPassword() == null
				|| member.getEmail() == null || member.getPhone() == null) {
			throw new Exception("�������� ���� ������ �ֽ��ϴ�");
		}
		member.setSelfImg(selfImgFile.getOriginalFilename());
		member.setAddr(preAddr + " " + detailAddr);
		memberService.joinMember(member);
		if (member.getSelfImg() != null && !member.getSelfImg().trim().equals("")) {
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId();
			File folder = new File(path);
			folder.mkdirs();
			selfImgFile.transferTo(new File(path + "\\" + member.getSelfImg()));
		}
		return "/main/home";
	}

	/**
	 * �α��� ��
	 */
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}

	/**
	 * ID �ߺ� üũ
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
	 * ���������� �̵�
	 */
	@RequestMapping("/myPage")
	public ModelAndView myPage(Authentication aut) {
		ModelAndView mv = new ModelAndView();
		MemberDTO member = (MemberDTO) aut.getPrincipal();
		member.setGpaList(errandsService.selectGPAById((member.getUserId())));
		member.setHashList(memberService.selectHashtag(member.getUserId()));
		mv.addObject("member", member);
		mv.setViewName("/user/myPage");
		return mv;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpSession session, Authentication aut, MemberDTO updateMember, String currentPassword,
			String newPassword, String renewPassword, String preAddr, String detailAddr) throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberDTO member = (MemberDTO) aut.getPrincipal();
		if (!passwordEncoder.matches(currentPassword, member.getPassword())) {
			throw new Exception("��й�ȣ�� ���� �ʽ��ϴ�!");
		}
		if (!renewPassword.equals(newPassword)) {
			throw new Exception("���ο� ��й�ȣ�� �ٽ� �Է����ּ���");
		}
		if (preAddr != null && !preAddr.equals("")) {
			if (detailAddr == null || detailAddr.equals("")) {
				throw new Exception("�� �ּҸ� �Է��ϼ���");
			} else {
				updateMember.setAddr(preAddr + " " + detailAddr);
				member.setAddr(preAddr + " " + detailAddr);
			}
		} else {
			updateMember.setAddr(null);
		}

		MultipartFile newProfile = updateMember.getSelfImgFile();
		if ((newProfile.getOriginalFilename() != null && newProfile.getSize() != 0)) {
			updateMember.setSelfImg(newProfile.getOriginalFilename());
			member.setSelfImg(newProfile.getOriginalFilename());
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId();
			File folder = new File(path);
			folder.mkdirs();
			newProfile.transferTo(new File(path + "\\" + updateMember.getSelfImg()));
		} else {
			updateMember.setSelfImg(null);
		}
		if (!(newPassword == null || newPassword.equals(""))) {
			updateMember.setPassword(newPassword);
		}
		updateMember.setUserId(member.getUserId());
		memberService.updateMember(updateMember, member);
		mv.setViewName("redirect:/user/myPage");
		return mv;
	}

	@RequestMapping("/safetyRegister")
	public ModelAndView safetyRegister(Authentication aut) {
		boolean isSafety = false;
		MemberDTO currentUser = (MemberDTO) aut.getPrincipal();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/safetyRegister");
		mv.addObject("member", memberService.selectMemberById(currentUser.getUserId()));
		for (String role : memberService.selectAuth(currentUser.getUserId())) {
			if (role.equals("ROLE_SAFETY")) {
				isSafety = true;
			}
		}
		mv.addObject("isSafety", isSafety);
		return mv;
	}

	/**
	 * �����ɺθ��� ���
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/submitSafety")
	public String submitSafety(HttpSession session, Authentication aut, MemberDTO member) throws Exception {
		MultipartFile ssnImgFile = member.getSsnImgFile();
		if (ssnImgFile == null || ssnImgFile.getSize() == 0) {
			throw new Exception("������ �־��ּ���!");
		}
		member.setUserId(((MemberDTO) aut.getPrincipal()).getUserId());
		member.setSsnImg(ssnImgFile.getOriginalFilename());
		memberService.updateSafety(member);
		String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId() + "\\ssn\\";
		File folder = new File(path);
		folder.mkdirs();
		ssnImgFile.transferTo(new File(path + "\\" + member.getSsnImg()));

		return "redirect:/user/safetyRegister";
	}

	@RequestMapping("/alert")
	public ModelAndView alert(Authentication aut, Integer page) {
		if(page == null) page = 1;
		MemberDTO member = (MemberDTO) aut.getPrincipal();

		List<NotificationDTO> alertList = memberService.selectNotificationById(member.getUserId(), page);

		PageMaker pm = new PageMaker(page, memberService.countNotification(member.getUserId())/ 11 + 1);

		pm.start();
		ModelAndView mv = new ModelAndView();
		memberService.allRead(member.getUserId());
		mv.setViewName("/user/alert");
		mv.addObject("alertList", alertList);
		mv.addObject("pm", pm);
		return mv;
	}

	/**
	 * Ajax�� ��� ���� ��������
	 */

	@RequestMapping("/selectMember")
	@ResponseBody
	public MemberDTO selectMember(String id) {
		return memberService.selectMemberById(id);
	}

	@RequestMapping("/profileLayer")
	public void profileLayer() {
	}
    
	/**
	 * ���������� ����Ʈ
	 */
	@RequestMapping("/charge")
	public ModelAndView charge(Authentication auth) {
		ModelAndView mv = new ModelAndView();
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		List<ErrandsDTO> list = adminMoneyService.pointList(userId);//����Ʈ ��볻��
		List<ErrandsDTO> successList = adminMoneyService.searchPointSuccess(userId);
		mv.setViewName("/user/charge");
		mv.addObject("list", list);
		mv.addObject("successList", successList);
		for(ErrandsDTO dto : successList){
			System.out.println(dto.getFinishTime());
		}
		return mv;
	}
	
	
	/**
	 * ��������������Ʈ ����
	 */
	@RequestMapping("/pointCharge")
	public ModelAndView pointCharge(Authentication auth, String select, String way){
		
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		int value = 0;
		ModelAndView mv = new ModelAndView();
		if(way.equals("bandBook")){
			value = Integer.parseInt(select);
			adminMoneyService.pointChargeBandBook(userId, value);
			mv.setViewName("/user/charge");
		}
		else if(way.equals("card")){
			value = Integer.parseInt(select);
			adminMoneyService.pointChargeCard(userId, value);
			
			MemberDTO currentUser = (MemberDTO) auth.getPrincipal();
			((MemberDTO) auth.getPrincipal()).getPoint().setCurrentPoint(currentUser.getPoint().getCurrentPoint()+value);
			
			mv.setViewName("/user/charge");
		}
		return mv;
	}
	
	
}
