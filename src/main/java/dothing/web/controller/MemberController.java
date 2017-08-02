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
 * ������ ���� ��Ʈ�ѷ�
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
	 * ȸ�� ���� �������� �̵�
	 */
	@RequestMapping("/signIn")
	public String joinForm() {
		return "/user/signIn";
	}


	/**
	 * ȸ�� �����ϱ� ���μ���
	 * @param session ������ ���� �̹��� ���������� ��θ� �ҷ����� ����
	 * @param member ȸ�� �������� DTO
	 * @param preAddr �ּ�
	 * @param detailAddr ���ּ�
	 * @throws Exception
	 */
	@RequestMapping("/join")
	public String join(HttpSession session, MemberDTO member, String preAddr, String detailAddr) throws Exception {

		// �������� üũ
		MultipartFile selfImgFile = member.getSelfImgFile();
		if (selfImgFile.getSize() == 0 || selfImgFile.getOriginalFilename() == null) {
			throw new Exception("������ ������ ÷�����ּ���");
		}
		
		if (preAddr == null || detailAddr == null || preAddr.equals("") || detailAddr.equals("")) {
			throw new Exception("�ּҶ��� Ȯ�����ּ���.");
		}
		if (member.getName() == null || member.getUserId() == null || member.getPassword() == null) {
			throw new Exception("�������� ���� ������ �ֽ��ϴ�");
		}

		if (member.getSex() == null){
			throw new Exception("������ �����ϼ���");
		}
		
		member.setSelfImg(selfImgFile.getOriginalFilename());

		// �̹��� Ȯ���� �˻�
		String ext = (member.getSelfImg().split("\\."))[1];
		ext = ext.toLowerCase();
		if (!(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif"))) {
			throw new Exception("Ȯ���ڰ� jpg, jpeg, png, gif�� ���ϸ� ���ε� �� �� �ֽ��ϴ�");
		}
		
		
		memberService.joinMember(member, false);
		
		// ������ ���� ����
		if (member.getSelfImg() != null && !member.getSelfImg().trim().equals("")) {
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId();
			File folder = new File(path);
			folder.mkdirs();
			selfImgFile.transferTo(new File(path + "\\" + member.getSelfImg()));
		}
		return "/user/okay";
	}

	/**
	 * �α��� ȭ������ �̵�
	 */
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}

	/**
	 * ID �ߺ� üũ
	 * @param userId �ߺ� üũ�� ���̵�
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
	 * ������������ �̵�
	 * @param aut �α��� �� ���� ����
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
	 * �������� ����
	 * @param session ������ ���� �����Ҷ� ���� ��� ȹ��
	 * @param aut �α��� �� ���� ����
	 * @param updateMember ������Ʈ �� ���� dto
	 * @param currentPassword ���� ��й�ȣ
	 * @param newPassword ���ο� ��й�ȣ
	 * @param renewPassword ���ο� ��й�ȣ Ȯ�� �Է�
	 */
	@RequestMapping("/update")
	public ModelAndView update(HttpSession session, Authentication aut, MemberDTO updateMember, String currentPassword,
			String newPassword, String renewPassword) throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberDTO member = (MemberDTO) aut.getPrincipal();
		// �������� üũ
		if (!passwordEncoder.matches(currentPassword, member.getPassword())) {
			throw new Exception("��й�ȣ�� ���� �ʽ��ϴ�!");
		}
		if (!renewPassword.equals(newPassword)) {
			throw new Exception("���ο� ��й�ȣ�� �ٽ� �Է����ּ���");
		}
		if (updateMember.getPreAddr() != null && !updateMember.getPreAddr().equals("")) {
			if (updateMember.getDetailAddr() == null || updateMember.getDetailAddr().equals("")) {
				throw new Exception("�� �ּҸ� �Է��ϼ���");
			}
		} else {
			updateMember.setPreAddr(null);
		}

		MultipartFile newProfile = updateMember.getSelfImgFile();
		//������ ������ �ٲٴ°��
		if ((newProfile.getOriginalFilename() != null && newProfile.getSize() != 0)) {
			// ���� �̸� ȹ�� �� ���� ��ο� ����
			updateMember.setSelfImg(newProfile.getOriginalFilename());
			member.setSelfImg(newProfile.getOriginalFilename());
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId();
			File folder = new File(path);
			folder.mkdirs();
			newProfile.transferTo(new File(path + "\\" + updateMember.getSelfImg()));
		} else {
			updateMember.setSelfImg(null);
		}
		
		//��й�ȣ�� �ٲٴ°��
		if (!(newPassword == null || newPassword.equals(""))) {
			updateMember.setPassword(newPassword);
		}
		updateMember.setUserId(member.getUserId());
		memberService.updateMember(updateMember, member);
		mv.setViewName("redirect:/user/myPage");
		return mv;
	}

	/**
	 * ���� �ɺθ��� ��û ��������
	 * @param aut �α����� ���� ����
	 */
	@RequestMapping("/safetyRegister")
	public ModelAndView safetyRegister(Authentication aut) {
		boolean isSafety = false;
		MemberDTO currentUser = (MemberDTO) aut.getPrincipal();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/safetyRegister");
		mv.addObject("member", memberService.selectMemberById(currentUser.getUserId()));
		// �α��� �� ������ ���� �ɺθ����ΰ� �Ǵ�
		for (String role : memberService.selectAuth(currentUser.getUserId())) {
			if (role.equals("ROLE_SAFETY")) {
				isSafety = true;
			}
		}
		mv.addObject("isSafety", isSafety);
		return mv;
	}

	/**
	 * ���� �ɺθ��� ��� ���μ���
	 * @param session �����ɺθ��� �̹��� ��� �� ��� ���� ��� ȹ��
	 * @param aut �α����� ���� ����
	 * @param member ������Ʈ �� ���� dto
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
		
		// �����ɺθ��� ���� ������η� ���� ����
		String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId() + "\\ssn\\";
		File folder = new File(path);
		folder.mkdirs();
		ssnImgFile.transferTo(new File(path + "\\" + member.getSsnImg()));

		return "redirect:/user/safetyRegister";
	}

	/**
	 * ������ ���� �˸���� �ҷ�����
	 * @param aut �α����� ���� ����
	 * @param page ������
	 * @return
	 */
	@RequestMapping("/alert")
	public ModelAndView alert(Authentication aut, Integer page) {
		if(page == null) page = 1;
		MemberDTO member = (MemberDTO) aut.getPrincipal();

		List<NotificationDTO> alertList = memberService.selectNotificationById(member.getUserId(), page);

		// 10���� �˸��� 1���� �������� ����
		PageMaker pm = new PageMaker(page, memberService.countNotification(member.getUserId())/ 11 + 1);
		pm.start();
		
		ModelAndView mv = new ModelAndView();
		
		memberService.allRead(member.getUserId()); // ��� �˸� ���� ó��
		mv.setViewName("/user/alert");
		mv.addObject("alertList", alertList);
		mv.addObject("pm", pm);
		return mv;
	}


	/**
	 * ���������� ����Ʈ �������� �̵�
	 * @param auth �α��� �� ���� ����
	 */
	@RequestMapping("/charge")
	public ModelAndView charge(Authentication auth) {
		ModelAndView mv = new ModelAndView();
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();

		//����Ʈ ��볻��
		List<ErrandsDTO> list = adminMoneyService.pointList(userId);
		List<ErrandsDTO> successList = adminMoneyService.searchPointSuccess(userId);
		mv.setViewName("/user/charge");
		mv.addObject("list", list);
		mv.addObject("successList", successList);
		return mv;
	}
	
	
	/**
	 * ���������� ����Ʈ ���� ���μ���
	 * @param auth �α��� �� ���� ����
	 * @param select ���� �� ����Ʈ �ݾ�
	 * @param way card = ī�� ����, bandBook = �� ���� �Ա�
	 * @return
	 */
	@RequestMapping("/pointCharge")
	public ModelAndView pointCharge(Authentication auth, String select, String way){
		
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		int value = 0;
		ModelAndView mv = new ModelAndView();
		// ������ �Ա��� ���
		if(way.equals("bandBook")){
			value = Integer.parseInt(select);
			adminMoneyService.pointChargeBandBook(userId, value);
			mv.setViewName("/user/charge");
		}
		// ī�� ���� �� ���
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
	 * �α��� API�� ���� �������� �� �߰� ���� �Է�
	 * @param id �α��� API ���̵�
	 * @param photo �α��� API ������ ����
	 * @param email �α��� API �̸���
	 * @param gender �α��� API ����
	 * @param name �α��� API �̸�
	 */
	@RequestMapping("/addInformation")
	public ModelAndView loginAPI(String id, String photo, String email, String gender, String name){

		ModelAndView mv = new ModelAndView();
		MemberDTO member = memberService.selectMemberById(email);
		// ������ ������ ȸ���� �߰������� �Է¹��� �ʾ��� ���(���Ե��� �ʾ������)
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
		// �̹� ���Ե� ���
		else{
			mv.addObject("id", email);
			mv.addObject("password", id);
			mv.setViewName("/user/empty");
		}
		return mv;
	}
	
	/**
	 * �α��� API�� ȸ������ �� �� ���μ���
	 * @param session ������ ���� �����ų ���� ��� ȹ��
	 * @param member ���Խ�ų ȸ�� ���� dto
	 * @param photo ������ ����
	 */
	@RequestMapping("/apiControl")
	public String apiControl(HttpSession session, MemberDTO member, String photo) throws Exception{

		// URL �̹��� ������ ������
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
	 * ���� �Ϸ� ������
	 */
	@RequestMapping("/okay")
	public void okay(){}
	
	/**
	 * API�� �α��� ���� �� ���̵�� �н����带 �����ϴ� ������
	 */
	@RequestMapping("/empty")
	public void empty(){}
	
	/**
	 * ���� ���� �ٽú�����
	 * @param userId �ش��ϴ� ���� ���̵�
	 * @param num ���� ��ȣ
	 */
	@RequestMapping("/sendMail")
	@ResponseBody
	public void sendMail(String userId, Integer num){
		memberService.sendEmail(userId, num);
	}
	
	/**
	 * ���� ��ȣ Ȯ���ϱ�
	 * @param email �ش��ϴ� ���� �̸���
	 * @param authNum ���� ��ȣ
	 */
	@RequestMapping("/emailOk")
	public String emailOk(String email, Integer authNum) throws Exception{
		MemberDTO member = memberService.selectMemberById(email);
		//������ȣ�� �ٸ����
		if(member.getState().intValue() != authNum.intValue()){
			throw new Exception("������ȣ�� �ٸ��ϴ�!");
		}
		memberService.finishEmail(email);
		return "/user/loginForm";
	}
}
