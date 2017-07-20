package dothing.web.android.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.service.AndroidService;
import dothing.web.service.ErrandsService;
import dothing.web.util.FcmPusher;

@Controller
@RequestMapping("/androidErrand")
public class AndroidErrandsController {
	@Autowired
	FcmPusher fcmPusher;
	@Autowired
	AndroidService androidService;
	@Autowired
	ErrandsService errandsService;
	
	/**
	 * �ȵ���̵� �� �巡���Ҷ����� distance��ŭ �ɺθ� �ݰ� �˻�
	 *  */
	@RequestMapping("/errandSearch")
	@ResponseBody
	public List<ErrandsDTO> errandSearch(HttpServletRequest request){
		String lat = (String)request.getParameter("lat");
		String lng = (String)request.getParameter("lng");
		System.out.println(lat + " : " + lng);
		List<ErrandsDTO> list = errandsService.searchErrands(null, null, null, 3, lat, lng);
		System.out.println(list.size() +"�� �˻���");
		return list;
	}
	
	/**
	 * �ȵ���̵� �ɺθ� ����ϱ�
	 */
	@RequestMapping("/insertErrand")
	@ResponseBody
	public Integer uploadImage(HttpSession session, ErrandsDTO dto) throws Exception{
		int result = 0;
	
		MultipartFile file = dto.getErrandsPhotoFile();
		if(file != null){
			dto.setErrandsPhoto(file.getOriginalFilename());
			result = errandsService.insertErrands(dto);
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		}else{
			dto.setErrandsPhoto("EMPTY");
			result = errandsService.insertErrands(dto);
		}
		if(result > 0){
			ErrandsPosDTO posDTO = dto.getErrandsPos();
			List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(), posDTO.getLongitude(), 5);
			if(userTokenList !=  null&&userTokenList.size() > 0  )
			fcmPusher.pushFCMNotification(userTokenList, "�ζ�", "�ֺ��� ���ɺθ��� ��ω���ϴ�!: " + dto.getTitle());
		}
		return result;
	}
	

	/**
	 * �� �ɺθ� ��û��� ��������
	 */
	@RequestMapping("/myRequest")
	@ResponseBody
	public List<ErrandsDTO> myRequest(HttpServletRequest request){
		String userId = (String)request.getParameter("userId");
		System.out.println("��û:" +userId);
		return errandsService.myErrandsRequest(userId,0);
	}

	
	//�ֹ��� ������
	@RequestMapping("/requesterDetail")
	@ResponseBody
	public Map<String, Object> requesterDetail(HttpServletRequest request) throws Exception{
		String errandNum = (String)request.getParameter("errandNum");
		return androidService.selectRequesterDetail(Integer.parseInt(errandNum));
	}
	
	//��� ���
	@RequestMapping("/insertReply")
	@ResponseBody
	public String insertReply(HttpServletRequest request) {
		String memberId = (String)request.getParameter("memberId");
		String errandNum = (String)request.getParameter("errandNum");
		String arrivalTime = (String)request.getParameter("arrivalTime");
		String replyContent = (String)request.getParameter("replyContent");
		
		 ErrandsReplyDTO replyDTO = new ErrandsReplyDTO();
		ErrandsDTO errandsDTO =  new ErrandsDTO();
		errandsDTO.setErrandsNum(Integer.parseInt(errandNum));
		replyDTO.setErrands(errandsDTO);
		replyDTO.setArrivalTime(arrivalTime);
		replyDTO.setReplyContent(replyContent);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(memberId);
		replyDTO.setUser(memberDTO);
		
		int result = errandsService.insertReply(replyDTO);
		
		
		
		return result+"";
		
	}
	
	
	/**
	 * �� �ɺθ� ������ ��������
	 */
	@RequestMapping("/myResponse")
	@ResponseBody
	public List<ErrandsDTO> myResponse(HttpServletRequest request){
		String userId = (String)request.getParameter("userId");
		return errandsService.myErrandsResponse(userId,0);
	}
	
}
