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
	 * 안드로이드 맵 드래그할때마다 distance만큼 심부름 반경 검색
	 */
	@RequestMapping("/errandSearch")
	@ResponseBody
	public List<ErrandsDTO> errandSearch(HttpServletRequest request, int distance) {
		String lat = (String) request.getParameter("lat");
		String lng = (String) request.getParameter("lng");
		List<ErrandsDTO> list = errandsService.searchErrands(null, null, null, distance, lat, lng);
		System.out.println(distance + "반경으로" + list.size() + "개 검색됨");
		return list;
	}

	/**
	 * 안드로이드 심부름 등록하기
	 */
	@RequestMapping("/insertErrand")
	@ResponseBody
	public Integer uploadImage(HttpSession session, ErrandsDTO dto) throws Exception {
		int result = 0;
		int errandsNum = 0;
		MultipartFile file = dto.getErrandsPhotoFile();
		if (file != null) {
			dto.setErrandsPhoto(file.getOriginalFilename());
			System.out.println("파일이 있을경우" + dto + " 삽입");
			result = errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		} else {
			dto.setErrandsPhoto("EMPTY");
			System.out.println("파일이 없을경우" + dto + " 삽입");
			result = errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
		}
		if (result > 0) {
			ErrandsPosDTO posDTO = dto.getErrandsPos();
			List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(),
					posDTO.getLongitude(), 5);
			if (userTokenList != null && userTokenList.size() > 0)
				fcmPusher.pushFCMNotification(userTokenList, "두띵", "주변에 새심부름이 등록됬습니다!: " + dto.getTitle(), "DETAIL_ACTIVITY", errandsNum +"");
		}
		return result;
	}

	/**
	 * 내 심부름 요청목록 가져오기
	 */
	@RequestMapping("/myRequest")
	@ResponseBody
	public List<ErrandsDTO> myRequest(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		System.out.println("요청:" + userId);
		return errandsService.myErrandsRequest(userId, 0);
	}

	// 주문자 상세정보
	@RequestMapping("/requesterDetail")
	@ResponseBody
	public Map<String, Object> requesterDetail(HttpServletRequest request) throws Exception {
		String errandNum = (String) request.getParameter("errandNum");
		Map<String, Object> map = androidService.selectRequesterDetail(Integer.parseInt(errandNum));

		// 회원정보가져오기
		MemberDTO memberDTO = memberService.selectMemberById((String) map.get("requesterId"));
		map.put("introduce", memberDTO.getIntroduce());
		map.put("requesterImg", memberDTO.getSelfImg());


		List<String> list = (List<String>) map.get("hashtagList");
		
		System.out.println("size : " + list.size());
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("리퀘스트 디테일: " + map);
		return map;
	}

	// 댓글 등록
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
		fcmPusher.pushFCMNotification(tokenList, "댓글이 등록됨!", errandsDTO.getTitle() +"글에 댓글이 등록됬습니다!", "DETAIL_ACTIVITY", errandNum);
		return result + "";

	}

	/**
	 * 내 심부름 수행목록 가져오기
	 */
	@RequestMapping("/myResponse")
	@ResponseBody
	public List<ErrandsDTO> myResponse(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		return errandsService.myErrandsResponse(userId, 0);
	}

	/**
	 * 채팅 목록 가져오기
	 */
	@RequestMapping("/selectChatList")
	@ResponseBody
	public List<ErrandsDTO> selectChatList(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		System.out.println("유저아이디: " + userId);
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
	 * 채팅 내역 가져오기
	 */
	@RequestMapping("/loadChat")
	@ResponseBody
	public List<String> loadChat(HttpServletRequest request) {
		String errandsNum = request.getParameter("errandsNum");
		return chatService.getContent(errandsNum);
	}

	/*
	 * 심부름 상세정보 가져오기
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
	 * 심부름 완료 요청
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/errandsFinish")
	@ResponseBody
	public boolean errandsFinishi(GPADTO gpaDTO, String isRequest, String hashContext) throws Exception {
		boolean result = false;
		System.out.println(gpaDTO + ":" + isRequest + ":" + hashContext);
		ErrandsDTO errands = errandsService.selectErrands(gpaDTO.getErrandsNum()); // 해당
		// 심부름
		// 불러오기
		String responseUserId = errands.getResponseUser().getUserId();
		String requestUserId = errands.getRequestUser().getUserId();
		if (isRequest.equals("true")) { // 요청자가 확인할 경우
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
			gpaDTO.setRequestManners(0);
			gpaDTO.setUserId(responseUserId);
			errandsService.okRequest(gpaDTO, responseUserId, hashContext, true);
			String token = androidService.selectTokenById(responseUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				fcmPusher.pushFCMNotification(tokenList, "심부름 완료 요청!", requestUserId + "님이 심부름 완료를 눌렀습니다!", "CHAT_ACTIVITY", gpaDTO.getErrandsNum() +"");
			}

		} else { // 심부름꾼이 확인할 경우
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
				fcmPusher.pushFCMNotification(tokenList, "심부름 완료 요청!", responseUserId + "님이 심부름 완료를 눌렀습니다!", "CHAT_ACTIVITY", gpaDTO.getErrandsNum() +"");
			}
		}

		ErrandsDTO upErrands = errandsService.selectErrands(gpaDTO.getErrandsNum()); // 해당
		// 심부름
		// 불러오기
		// 심부름꾼과 요청자가 모두 확인했을 경우
		if (upErrands.getArrivalTime() != null && upErrands.getFinishTime() != null) {
			// 포인트 업데이뚜
			int totalPrice = upErrands.getErrandsPrice() + upErrands.getProductPrice();
			System.out.println(totalPrice + " 토탈프라이스 ");
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
			fcmPusher.pushFCMNotification(tokenList, "심부름 완료!", errands.getTitle() + ": 심부름이 성공적으로 끝났습니다.", null, null);
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
	 * 심부름 수행 프로세스(심부름꾼 선택했을 때)
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
			map.put("result", "포인트가 부족합니다! 충전해주세요.");
			throw new Exception("포인트가 부족합니다! 충전해주세요.");
		}
		errandsService.updateErrands(errandNum, responseUserId, requestUser.getUserId(), "startTime", null, null, -totalPrice);
		requestUser.getPoint().setCurrentPoint((requestUser.getPoint().getCurrentPoint()) - totalPrice);
		memberService.insertNotification(responseUserId, "<a href='../errand/detailView?num="+errandNum+"'>"+ errandNum + "번 글의 " + requestUser.getName() + "님과 매칭되었습니다.</a>");
		//mv.addObject("num", num);
		//mv.addObject("responseName", memberService.selectMemberById(responseId).getName());
		//mv.addObject("responseId", responseId);
		androidService.initLocation(errandNum, requestUser.getUserId());
		androidService.initLocation(errandNum, responseUserId);
		//mv.setViewName("/errand/okay");
		map.put("result", "성공");
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
