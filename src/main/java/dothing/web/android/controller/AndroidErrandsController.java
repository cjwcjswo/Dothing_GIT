package dothing.web.android.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.service.AndroidService;
import dothing.web.service.ChatService;
import dothing.web.service.ErrandsService;
import dothing.web.service.MemberService;
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
	@Autowired
	MemberService memberService;
	@Autowired
	ChatService chatService;

	/**
	 * �ȵ���̵� �� �巡���Ҷ����� distance��ŭ �ɺθ� �ݰ� �˻�
	 */
	@RequestMapping("/errandSearch")
	@ResponseBody
	public List<ErrandsDTO> errandSearch(HttpServletRequest request, int distance) {
		String lat = (String) request.getParameter("lat");
		String lng = (String) request.getParameter("lng");
		List<ErrandsDTO> list = errandsService.searchErrands(null, null, null, distance, lat, lng);
		System.out.println(distance + "�ݰ�����" + list.size() + "�� �˻���");
		return list;
	}

	/**
	 * �ȵ���̵� �ɺθ� ����ϱ�
	 */
	@RequestMapping("/insertErrand")
	@ResponseBody
	public Integer uploadImage(HttpSession session, ErrandsDTO dto) throws Exception {
		int result = 0;
		int errandsNum = 0;
		MultipartFile file = dto.getErrandsPhotoFile();
		if (file != null) {
			dto.setErrandsPhoto(file.getOriginalFilename());
			System.out.println("������ �������" + dto + " ����");
			result = errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		} else {
			dto.setErrandsPhoto("EMPTY");
			System.out.println("������ �������" + dto + " ����");
			result = errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
		}
		if (result > 0) {
			ErrandsPosDTO posDTO = dto.getErrandsPos();
			List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(),
					posDTO.getLongitude(), 5);
			if (userTokenList != null && userTokenList.size() > 0)
				fcmPusher.pushFCMNotification(userTokenList, "�ζ�", "�ֺ��� ���ɺθ��� ��ω���ϴ�!: " + dto.getTitle(), "DETAIL_ACTIVITY", errandsNum +"");
		}
		return result;
	}

	/**
	 * �� �ɺθ� ��û��� ��������
	 */
	@RequestMapping("/myRequest")
	@ResponseBody
	public List<ErrandsDTO> myRequest(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		System.out.println("��û:" + userId);
		return errandsService.myErrandsRequest(userId, 0);
	}

	// �ֹ��� ������
	@RequestMapping("/requesterDetail")
	@ResponseBody
	public Map<String, Object> requesterDetail(HttpServletRequest request) throws Exception {
		String errandNum = (String) request.getParameter("errandNum");
		Map<String, Object> map = androidService.selectRequesterDetail(Integer.parseInt(errandNum));

		// ȸ��������������
		MemberDTO memberDTO = memberService.selectMemberById((String) map.get("requesterId"));
		map.put("introduce", memberDTO.getIntroduce());
		map.put("requesterImg", memberDTO.getSelfImg());


		List<String> list = (List<String>) map.get("hashtagList");
		
		System.out.println("size : " + list.size());
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("������Ʈ ������: " + map);
		return map;
	}

	// ��� ���
	@RequestMapping("/insertReply")
	@ResponseBody
	public String insertReply(HttpServletRequest request) throws Exception {
		String memberId = (String) request.getParameter("memberId");
		String errandNum = (String) request.getParameter("errandNum");
		String arrivalTime = (String) request.getParameter("arrivalTime");
		String replyContent = (String) request.getParameter("replyContent");

		ErrandsReplyDTO replyDTO = new ErrandsReplyDTO();
		ErrandsDTO errandsDTO = errandsService.selectErrands(Integer.parseInt(errandNum));
		replyDTO.setErrands(errandsDTO);
		replyDTO.setArrivalTime(arrivalTime);
		replyDTO.setReplyContent(replyContent);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(memberId);
		replyDTO.setUser(memberDTO);
		
		int result = errandsService.insertReply(replyDTO);
		
		String token = androidService.selectTokenById(errandsDTO.getRequestUser().getUserId());
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		fcmPusher.pushFCMNotification(tokenList, "����� ��ϵ�!", errandsDTO.getTitle() +"�ۿ� ����� ��ω���ϴ�!", "DETAIL_ACTIVITY", errandNum);
		return result + "";

	}

	/**
	 * �� �ɺθ� ������ ��������
	 */
	@RequestMapping("/myResponse")
	@ResponseBody
	public List<ErrandsDTO> myResponse(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		return errandsService.myErrandsResponse(userId, 0);
	}

	/**
	 * ä�� ��� ��������
	 */
	@RequestMapping("/selectChatList")
	@ResponseBody
	public List<ErrandsDTO> selectChatList(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		System.out.println("�������̵�: " + userId);
		List<ErrandsDTO> list = androidService.selectChatList(userId);
		for (ErrandsDTO dto : list) {
			System.out.println("A:" + dto.getRequestUser().getUserId());
			System.out.println("B:" + dto.getResponseUser().getUserId());
			dto.setRequestUser(memberService.selectMemberById(dto.getRequestUser().getUserId()));
			dto.setResponseUser(memberService.selectMemberById(dto.getResponseUser().getUserId()));
		}
		return list;
	}

	/**
	 * ä�� ���� ��������
	 */
	@RequestMapping("/loadChat")
	@ResponseBody
	public List<String> loadChat(HttpServletRequest request) {
		String errandsNum = request.getParameter("errandsNum");
		return chatService.getContent(errandsNum);
	}

	/*
	 * �ɺθ� ������ ��������
	 */
	@RequestMapping("/errandsDetail")
	@ResponseBody
	public Map<String, Object> errandsDetail(HttpServletRequest request) throws Exception {
		String errandNum = (String) request.getParameter("errandNum");

		Map<String, Object> map = new HashMap<>();
		ErrandsDTO errandsDTO = errandsService.selectErrands(Integer.parseInt(errandNum));

		map.put("productPrice", errandsDTO.getProductPrice());
		map.put("errandsPrice", errandsDTO.getErrandsPrice());
		map.put("address", errandsDTO.getErrandsPos().getAddr());
		map.put("errandContent", errandsDTO.getContent());
		map.put("errandImg", errandsDTO.getErrandsPhoto());
		map.put("replyList", errandsDTO.getErrandsReply());
		map.put("errandTime", errandsDTO.getEndTime());
		List<Integer> avgGpaList = new ArrayList<>();
		
		List<ErrandsReplyDTO> replyList = errandsDTO.getErrandsReply();
		for(int i=0; i<replyList.size(); i++) {
			ErrandsReplyDTO reply =  replyList.get(i);
			/*System.out.println(reply.getArrivalTime() + " " + reply.getReplyContent() + " " + reply.getReplyDate() + " " + 
			reply.getUser().getUserId() + " " + reply.getUser().getName() + " " + reply.getUser().getSelfImg());*/
			
			List<GPADTO> gpaList = memberService.averageGPA(reply.getUser().getUserId());
			int sum = 0;
			for(int j=0; j<gpaList.size(); j++) {
				GPADTO gpa = gpaList.get(j);
				sum += Math.round((gpa.getResponseAccuracy()+gpa.getResponseKindness()+gpa.getResponseSpeed()) / 3);
			}
			int avg = 0;
			if(gpaList.size() == 0) avg = 0; 
			else avg = Math.round(sum / gpaList.size());
			avgGpaList.add(avg);
			
		}
		map.put("avgGpaList", avgGpaList);
		
	
		
		return map;
	}

	/**
	 * �ɺθ� �Ϸ� ��û
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/errandsFinish")
	@ResponseBody
	public boolean errandsFinishi(GPADTO gpaDTO, String isRequest, String hashContext) throws Exception {
		boolean result = false;
		System.out.println(gpaDTO + ":" + isRequest + ":" + hashContext);
		ErrandsDTO errands = errandsService.selectErrands(gpaDTO.getErrandsNum()); // �ش�
		// �ɺθ�
		// �ҷ�����
		String responseUserId = errands.getResponseUser().getUserId();
		String requestUserId = errands.getRequestUser().getUserId();
		if (isRequest.equals("true")) { // ��û�ڰ� Ȯ���� ���
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
			gpaDTO.setRequestManners(0);
			gpaDTO.setUserId(responseUserId);
			errandsService.okRequest(gpaDTO, responseUserId, hashContext, true);
			String token = androidService.selectTokenById(responseUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				fcmPusher.pushFCMNotification(tokenList, "�ɺθ� �Ϸ� ��û!", requestUserId + "���� �ɺθ� �ϷḦ �������ϴ�!", "CHAT_ACTIVITY", gpaDTO.getErrandsNum() +"");
			}

		} else { // �ɺθ����� Ȯ���� ���
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, "arrival", null, 0);
			gpaDTO.setResponseAccuracy(0);
			gpaDTO.setResponseKindness(0);
			gpaDTO.setResponseSpeed(0);
			gpaDTO.setUserId(requestUserId);
			errandsService.okRequest(gpaDTO, requestUserId, hashContext, true);
			String token = androidService.selectTokenById(requestUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				fcmPusher.pushFCMNotification(tokenList, "�ɺθ� �Ϸ� ��û!", responseUserId + "���� �ɺθ� �ϷḦ �������ϴ�!", "CHAT_ACTIVITY", gpaDTO.getErrandsNum() +"");
			}
		}

		ErrandsDTO upErrands = errandsService.selectErrands(gpaDTO.getErrandsNum()); // �ش�
		// �ɺθ�
		// �ҷ�����
		// �ɺθ��۰� ��û�ڰ� ��� Ȯ������ ���
		if (upErrands.getArrivalTime() != null && upErrands.getFinishTime() != null) {
			// ����Ʈ �����̶�
			int totalPrice = upErrands.getErrandsPrice() + upErrands.getProductPrice();
			System.out.println(totalPrice + " ��Ż�����̽� ");
			memberService.updatePoint(totalPrice, upErrands.getResponseUser().getUserId());
			String requestToken = androidService.selectTokenById(requestUserId);
			String responseToken = androidService.selectTokenById(responseUserId);
			List<String> tokenList = new ArrayList<String>();
			if (requestToken != null) {
				tokenList.add(requestToken);
			}
			if(responseToken != null){
				tokenList.add(responseToken);
			}
			fcmPusher.pushFCMNotification(tokenList, "�ɺθ� �Ϸ�!", errands.getTitle() + ": �ɺθ��� ���������� �������ϴ�.", null, null);
		}
		result = true;
		return result;
	}

	@RequestMapping("/errandListSearch")
	@ResponseBody
	public List<ErrandsDTO> searchErrandsList(String keyword, int sort){
		List<ErrandsDTO> list = errandsService.selectList(sort, keyword, keyword, 0);
		return list;
	}
	
	
	/**
	 * �ɺθ� ���� ���μ���(�ɺθ��� �������� ��)
	 */
	@RequestMapping("/startErrand")
	public Map<String, Object> startErrand(String requestUserId, String strErrandNum, String responseUserId) throws Exception {
		//ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<>();
		int errandNum = Integer.parseInt(strErrandNum);
		MemberDTO requestUser = memberService.selectMemberById(requestUserId);
		ErrandsDTO currentErrand = errandsService.selectErrands(errandNum);
		int totalPrice = currentErrand.getProductPrice() + currentErrand.getErrandsPrice();
		if (totalPrice > requestUser.getPoint().getCurrentPoint()) {
			map.put("result", "����Ʈ�� �����մϴ�! �������ּ���.");
			throw new Exception("����Ʈ�� �����մϴ�! �������ּ���.");
		}
		errandsService.updateErrands(errandNum, responseUserId, requestUser.getUserId(), "startTime", null, null, -totalPrice);
		requestUser.getPoint().setCurrentPoint((requestUser.getPoint().getCurrentPoint()) - totalPrice);
		memberService.insertNotification(responseUserId, "<a href='../errand/detailView?num="+errandNum+"'>"+ errandNum + "�� ���� " + requestUser.getName() + "�԰� ��Ī�Ǿ����ϴ�.</a>");
		//mv.addObject("num", num);
		//mv.addObject("responseName", memberService.selectMemberById(responseId).getName());
		//mv.addObject("responseId", responseId);
		androidService.initLocation(errandNum, requestUser.getUserId());
		androidService.initLocation(errandNum, responseUserId);
		//mv.setViewName("/errand/okay");
		map.put("result", "���������� ��Ī�Ǿ����ϴ�!!");
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
