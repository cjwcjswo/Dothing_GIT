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
 * �ȵ���̵�  ������ ���� ���� ��Ʈ�ѷ�
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
	 * ȸ�� ���� ���μ���
	 * @param request ������ ���� �����ų ���� ��� ȹ��
	 * @param memberDTO ���� ��ų ��� ���� dto
	 */
	@RequestMapping("/signIn")
	@ResponseBody
	public Map<String,String> signIn(HttpServletRequest request,MemberDTO memberDTO) throws Exception{
		MultipartFile file = memberDTO.getSelfImgFile();
		// ������ ���� ����
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
	 * ���� ���� ������
	 * @param email ���� �̸��� �ּ�
	 */
	@RequestMapping("/sendEmail")
	@ResponseBody
	public String sendEmail(String email){
		return androidService.androidSendEmail(email);
	}
	
	/**
	 * �α��� ���μ���
	 * @param request �ȵ���̵忡�� ������ �Ķ����
	 */
	@RequestMapping("/checkId")
	@ResponseBody
	public MemberDTO android(HttpServletRequest request) {
		String email = request.getParameter("userId");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		
		// �������� üũ
		if (email == null || password == null) {
			return null;
		}
		MemberDTO memberDTO = memberService.selectMemberById(email);

		// ���� �ä����� ���� ���
		if (memberDTO == null) {
			return null;
		}

		// ��й�ȣ�� Ʋ�� ���
		if (!passwordEncoder.matches(password, memberDTO.getPassword())) {
			return null;
		}
		if (token != null) {
			androidService.insertToken(memberDTO.getUserId(), token);
		}
		return memberDTO;
	}
	
	
	/**
	 * �α��� API �α��� ���μ���
	 * @param request �ȵ���̵忡�� ������ �Ķ����
	 * @param memberDTO ȸ�� ���� dto
	 */
	@RequestMapping("/apiCheckId")
	@ResponseBody
	public MemberDTO apiCheckId(HttpServletRequest request,MemberDTO memberDTO) throws Exception{
		String email = memberDTO.getUserId();
		String password = memberDTO.getPassword();
		String token = request.getParameter("token");
		String facebook = request.getParameter("facebook");
		
		// �������� üũ
		if (email == null || password == null) {
			return null;
		}
		MemberDTO resultMemberDTO = memberService.selectMemberById(email);

		// ���� ������ ���� ���
		if (resultMemberDTO == null && facebook == null) {
			return null;
		}
		
		// ���̽������� ������ �ȵ����� ��� ȸ������
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

		// ��й�ȣ�� ��ġ���� ���� ���
		if (!passwordEncoder.matches(password, resultMemberDTO.getPassword())) {
			return null;
		}

		if (token != null) {
			androidService.insertToken(resultMemberDTO.getUserId(), token);
		}
		
		
		return resultMemberDTO;
	}



	
	/**
	 * ���� �ɺθ��� ��� ���μ���
	 * @param session ���� �ɺθ��� �̹��� ���� ��� ȹ��
	 * @param member ������ ���� ���� dto
	 */
	@RequestMapping("/submitSafety")
	@ResponseBody
	public int submitSafety(HttpSession session, MemberDTO member) throws IllegalStateException, IOException {
		int result = 0;
		MultipartFile ssnImgFile = member.getSsnImgFile();
		member.setSsnImg(ssnImgFile.getOriginalFilename());
		
		result = memberService.updateSafety(member);
		
		// �����ɺθ��� �̹��� ����
		if (result > 0) {
			String path = session.getServletContext().getRealPath("") + "\\users\\" + member.getUserId() + "\\ssn\\";
			File folder = new File(path);
			folder.mkdirs();
			ssnImgFile.transferTo(new File(path + "\\" + member.getSsnImg()));
		}
		return result;
	}

	/**
	 * GPS�� �ٲ� �� ���� ���� ��ġ���� ����
	 * @param request �ȵ���̵忡�� ������ �Ķ���� ��
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
	 * ������ ��ġ������ �ҷ����� ��Ʈ�ѷ�
	 * @param request �ȵ���̵忡�� ������ �Ķ����
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
	 * �ɺθ� �ϷḦ �̹� �ߴ��� ���ߴ��� �ҷ�����
	 * @param request �ȵ���̵忡�� ������ �Ķ����
	 */
	@RequestMapping("/isEvalFinish")
	@ResponseBody
	public boolean isEvalFinish(HttpServletRequest request){
		String errandsNum = request.getParameter("errandsNum");
		String isRequest = request.getParameter("isRequest");
		ErrandsDTO dto = errandsService.selectErrands(Integer.parseInt(errandsNum));
		
		
		// ��û���� ���
		if(isRequest.equals("true")){
			String finishTime = dto.getFinishTime();
			if(finishTime == null) return true;
			else return false;
		}
		// �ɺθ����� ���
		else{
			String arrivalTime = dto.getArrivalTime();
			if(arrivalTime == null) return true;
			else return false;
		}
	}
}
