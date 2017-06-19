package dothing.web.controller;

import java.io.File;

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

import dothing.web.dto.MemberDTO;
import dothing.web.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private PasswordEncoder passwordEncoder;

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
		} else{
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
		} else{
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
	public void safetyRegister(){
	}
	/**
	 * Ajax�� ��� ���� ��������
	 */

	@RequestMapping("/selectMember")
	@ResponseBody
	public MemberDTO selectMember(String id){
		return memberService.selectMemberById(id);
	}
}
