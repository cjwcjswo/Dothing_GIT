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
/**
 * �ȵ���̵� ������ �ɺθ� ���� ��Ʈ�ѷ�
 */
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
	 * �ʺ並 �巡�� �� �� ���� �ɺθ� ��� ����ֱ�
	 * @param request �Ķ���� �� �޾ƿ���
	 * @param distance �Ÿ� �ݰ�
	 */
	@RequestMapping("/errandSearch")
	@ResponseBody
	public List<ErrandsDTO> errandSearch(HttpServletRequest request, int distance) {
		String lat = (String) request.getParameter("lat"); // ����
		String lng = (String) request.getParameter("lng"); // �浵
		List<ErrandsDTO> list = errandsService.searchErrands(null, null, null, distance, lat, lng);
		System.out.println(distance + "�ݰ�����" + list.size() + "�� �˻���");
		return list;
	}

	/**
	 * �ɺθ� ����ϱ� ���μ���
	 * @param session �ɺθ� ���� ������ ���� ���� ��� ȹ��
	 * @param dto �ɺθ� ���� dto
	 */
	@RequestMapping("/insertErrand")
	@ResponseBody
	public Integer uploadImage(HttpSession session, ErrandsDTO dto) throws Exception {
		int result = 0;
		int errandsNum = 0;
		MultipartFile file = dto.getErrandsPhotoFile();
		// �ɺθ� �� �̹����� �������
		if (file != null) {
			dto.setErrandsPhoto(file.getOriginalFilename());
			result = errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
			
			// �ɺθ� �̹��� ����
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		} 
		// �ɺθ� ���̹�����  ���� ���
		else { 
			dto.setErrandsPhoto("EMPTY");
			result = errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
		}
		
		// �ɺθ��� ���������� insert ���� ��� Ǫ���޼��� ����
		if (result > 0) { 
			ErrandsPosDTO posDTO = dto.getErrandsPos();
			// �ɺθ� ��� �ּ��� 5km�ݰ濡 �ִ� ����� ��ū�� �ҷ��´�
			List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(),
					posDTO.getLongitude(), 5); 
			Map<String, String> params = new HashMap<>();
			params.put("errandsNum", errandsNum + "");
			params.put("requestUserId", dto.getRequestUser().getUserId());
			if (userTokenList != null && userTokenList.size() > 0)
				fcmPusher.pushFCMNotification(userTokenList, "�ζ�", "�ֺ��� ���ɺθ��� ��ω���ϴ�!: " + dto.getTitle(), "DETAIL_ACTIVITY", params);
		}
		return result;
	}

	/**
	 * �� �ɺθ� ��û ��� �ҷ�����
	 * @param request ���� ���̵� �ҷ����� request
	 */
	@RequestMapping("/myRequest")
	@ResponseBody
	public List<ErrandsDTO> myRequest(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		return errandsService.myErrandsRequest(userId, 0);
	}

	/**
	 * �ɺθ� �� �ֹ����� ������ �ҷ�����
	 * @param request �ȵ���̵忡�� ������ �Ķ���� ��
	 */
	@RequestMapping("/requesterDetail")
	@ResponseBody
	public Map<String, Object> requesterDetail(HttpServletRequest request) throws Exception {
		String errandNum = (String) request.getParameter("errandNum"); // �ɺθ� ��ȣ
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
		return map;
	}

	/**
	 * �ɺθ� ��� ����ϱ� ���μ���
	 * @param request �ȵ���̵忡�� ������ ����
	 */
	@RequestMapping("/insertReply")
	@ResponseBody
	public String insertReply(HttpServletRequest request) throws Exception {
		String memberId = (String) request.getParameter("memberId");
		String errandNum = (String) request.getParameter("errandNum");
		String arrivalTime = (String) request.getParameter("arrivalTime");
		String replyContent = (String) request.getParameter("replyContent");

		ErrandsReplyDTO replyDTO = new ErrandsReplyDTO();
		ErrandsDTO errandsDTO = errandsService.selectErrands(Integer.parseInt(errandNum));
		
		//replyDTO ����
		replyDTO.setErrands(errandsDTO);
		replyDTO.setArrivalTime(arrivalTime);
		replyDTO.setReplyContent(replyContent);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(memberId);
		replyDTO.setUser(memberDTO);
		
		int result = errandsService.insertReply(replyDTO);
		
		// ����� ��ω�ٰ� ��û�ڿ��� Ǫ���޼����� ����
		String token = androidService.selectTokenById(errandsDTO.getRequestUser().getUserId());
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		Map<String, String> params = new HashMap<>();
		params.put("errandsNum", errandNum);
		params.put("requestUserId",errandsDTO.getRequestUser().getUserId());
		fcmPusher.pushFCMNotification(tokenList, "����� ��ϵ�!", errandsDTO.getTitle() +"�ۿ� ����� ��ω���ϴ�!", "DETAIL_ACTIVITY", params);
		return result + "";

	}

	/**
	 * �� �ɺθ� ���� ��� �ҷ�����
	 * @param request ���� ���̵� �ҷ����� request
	 */
	@RequestMapping("/myResponse")
	@ResponseBody
	public List<ErrandsDTO> myResponse(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		return errandsService.myErrandsResponse(userId, 0);
	}

	/**
	 * ä�� ��� ��������
	 * @param request �ȵ���̵忡�� ������ �Ķ����
	 * @return
	 */
	@RequestMapping("/selectChatList")
	@ResponseBody
	public List<ErrandsDTO> selectChatList(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		List<ErrandsDTO> list = androidService.selectChatList(userId);
		
		for (ErrandsDTO dto : list) {
			dto.setRequestUser(memberService.selectMemberById(dto.getRequestUser().getUserId()));
			dto.setResponseUser(memberService.selectMemberById(dto.getResponseUser().getUserId()));
		}
		
		return list;
	}

	/**
	 * ����� ä���� ���� ��������
	 * @param request �ȵ���̵忡�� ������ �Ķ����(�ɺθ� ��ȣ)
	 */
	@RequestMapping("/loadChat")
	@ResponseBody
	public List<String> loadChat(HttpServletRequest request) {
		String errandsNum = request.getParameter("errandsNum");
		return chatService.getContent(errandsNum);
	}

	/**
	 * �ɺθ� �󼼺��� ���μ���
	 * @param request �ȵ���̵忡�� ������ �Ķ����(�ɺθ� ��ȣ)
	 */
	@RequestMapping("/errandsDetail")
	@ResponseBody
	public Map<String, Object> errandsDetail(HttpServletRequest request) throws Exception {
		String errandNum = (String) request.getParameter("errandNum");

		Map<String, Object> map = new HashMap<>();
		ErrandsDTO errandsDTO = errandsService.selectErrands(Integer.parseInt(errandNum));

		// ����� �ɺθ� �� ���� �߰�
		map.put("productPrice", errandsDTO.getProductPrice());
		map.put("errandsPrice", errandsDTO.getErrandsPrice());
		map.put("address", errandsDTO.getErrandsPos().getAddr());
		map.put("errandContent", errandsDTO.getContent());
		map.put("errandImg", errandsDTO.getErrandsPhoto());
		map.put("replyList", errandsDTO.getErrandsReply());
		map.put("errandTime", errandsDTO.getEndTime());
		List<Integer> avgGpaList = new ArrayList<>();
		
		List<ErrandsReplyDTO> replyList = errandsDTO.getErrandsReply();
		
		// ��� ������ �߰�
		for(int i=0; i<replyList.size(); i++) {
			ErrandsReplyDTO reply =  replyList.get(i);
			List<GPADTO> gpaList = memberService.averageGPA(reply.getUser().getUserId());
			int sum = 0;
			for(int j=0; j<gpaList.size(); j++) {
				GPADTO gpa = gpaList.get(j);
				sum += Math.round((gpa.getResponseAccuracy()+gpa.getResponseKindness()+gpa.getResponseSpeed()) / 3.0);
			}
			int avg = 0;
			if(gpaList.size() == 0) avg = 0; 
			else avg = Math.round(sum / (float)gpaList.size());
			avgGpaList.add(avg);
			
		}
		
		map.put("avgGpaList", avgGpaList);
		return map;
	}

	/**
	 * �ɺθ� �Ϸ� ��û
	 * @param gpaDTO ���� �ű� ���� ���� DTO
	 * @param isRequest ���� ��û���ΰ� �ɺθ����ΰ� �Ǵ�
	 * @param hashContext �ؽ��±� ����
	 */
	@RequestMapping("/errandsFinish")
	@ResponseBody
	public boolean errandsFinishi(GPADTO gpaDTO, String isRequest, String hashContext) throws Exception {
		boolean result = false;
		ErrandsDTO errands = errandsService.selectErrands(gpaDTO.getErrandsNum());
		String responseUserId = errands.getResponseUser().getUserId();
		String requestUserId = errands.getRequestUser().getUserId();
		
		// ��û�ڰ� Ȯ���� ���
		if (isRequest.equals("true")) { 
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
			gpaDTO.setRequestManners(0);
			gpaDTO.setUserId(responseUserId);
			errandsService.okRequest(gpaDTO, responseUserId, hashContext, true);
			// �ɺθ��ۿ��� Ǫ���޼��� ������
			String token = androidService.selectTokenById(responseUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				Map<String, String> params = new HashMap<>();
				params.put("errandsNum", gpaDTO.getErrandsNum() +"");
				fcmPusher.pushFCMNotification(tokenList, "�ɺθ� �Ϸ� ��û!", requestUserId + "���� �ɺθ� �ϷḦ �������ϴ�!", "CHAT_ACTIVITY", params);
			}

		} 
		// �ɺθ����� Ȯ���� ���
		else { 
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, "arrival", null, 0);
			gpaDTO.setResponseAccuracy(0);
			gpaDTO.setResponseKindness(0);
			gpaDTO.setResponseSpeed(0);
			gpaDTO.setUserId(requestUserId);
			errandsService.okRequest(gpaDTO, requestUserId, hashContext, true);
			
			//��û�ڿ��� Ǫ���޼��� ������
			String token = androidService.selectTokenById(requestUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				Map<String, String> params = new HashMap<>();
				params.put("errandsNum", gpaDTO.getErrandsNum() +"");
				fcmPusher.pushFCMNotification(tokenList, "�ɺθ� �Ϸ� ��û!", responseUserId + "���� �ɺθ� �ϷḦ �������ϴ�!", "CHAT_ACTIVITY", params);
			}
		}

		ErrandsDTO upErrands = errandsService.selectErrands(gpaDTO.getErrandsNum()); 
		
		// �ɺθ��۰� ��û�ڰ� ��� Ȯ������ ���
		if (upErrands.getArrivalTime() != null && upErrands.getFinishTime() != null) {
			// ����Ʈ ������Ʈ
			int totalPrice = upErrands.getErrandsPrice() + upErrands.getProductPrice();
			memberService.updatePoint(totalPrice, upErrands.getResponseUser().getUserId());
			
			// �ɺθ��۰� ��û�ڿ��� ��� Ǫ���޼��� ������
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

	/**
	 * �ɺθ��� ����Ʈ ���·� �� ��
	 * @param keyword �˻��� ��(���� / �ּ� ����)
	 * @param sort ���� ����(1 = �ֽż�, 2 = ���� ��������, 3 = ���� ��������)
	 */
	@RequestMapping("/errandListSearch")
	@ResponseBody
	public List<ErrandsDTO> searchErrandsList(String keyword, int sort){
		List<ErrandsDTO> list = errandsService.selectList(sort, keyword, keyword, 0);
		return list;
	}
	
	
	/**
	 * �ɺθ� ���� ���μ���(��û�ڰ� �ɺθ��� ����)
	 * @param requestUserId ��û�� ���̵�
	 * @param strErrandNum �ɺθ� ��ȣ
	 * @param responseUserId �ɺθ��� ���̵�
	 */
	@RequestMapping("/startErrand")
	@ResponseBody
	public Map<String, Object> startErrand(String requestUserId, String strErrandNum, String responseUserId) throws Exception {

		Map<String, Object> map = new HashMap<>();
		int errandNum = Integer.parseInt(strErrandNum);
		MemberDTO requestUser = memberService.selectMemberById(requestUserId);
		ErrandsDTO currentErrand = errandsService.selectErrands(errandNum);
		
		// ��û���� ����Ʈ�� ���� �� ���
		int totalPrice = currentErrand.getProductPrice() + currentErrand.getErrandsPrice();
		if (totalPrice > requestUser.getPoint().getCurrentPoint()) {
			map.put("result", "����Ʈ�� �����մϴ�! �������ּ���.");
			return map;
		}
		
		errandsService.updateErrands(errandNum, responseUserId, requestUser.getUserId(), "startTime", null, null, -totalPrice);
		requestUser.getPoint().setCurrentPoint((requestUser.getPoint().getCurrentPoint()) - totalPrice);
		
		// �������� �˶� ������
		memberService.insertNotification(responseUserId, "<a href='../errand/detailView?num="+errandNum+"'>"+ errandNum + "�� ���� " + requestUser.getName() + "�԰� ��Ī�Ǿ����ϴ�.</a>");
		
		// �ɺθ� ��ġ���� ��ġ���� ����
		androidService.initLocation(errandNum, requestUser.getUserId());
		androidService.initLocation(errandNum, responseUserId);

		map.put("result", "���������� ��Ī�Ǿ����ϴ�!!");
		
		// �ȵ���̵忡�� Ǫ���޼��� ������
		String responseToken = androidService.selectTokenById(responseUserId);
		List<String> tokenList = new ArrayList<String>();
		if(responseToken != null){
			tokenList.add(responseToken);
		}
		fcmPusher.pushFCMNotification(tokenList, "�ɺθ� ��Ī��!",currentErrand.getTitle() + "�ɺθ��� ��Ī�Ǿ����ϴ�!", null, null);
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
