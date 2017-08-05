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
 * 안드로이드 스프링 심부름 관련 컨트롤러
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
	 * 맵뷰를 드래그 할 때 마다 심부름 목록 띄워주기
	 * @param request 파라미터 값 받아오기
	 * @param distance 거리 반경
	 */
	@RequestMapping("/errandSearch")
	@ResponseBody
	public List<ErrandsDTO> errandSearch(HttpServletRequest request, int distance) {
		String lat = (String) request.getParameter("lat"); // 위도
		String lng = (String) request.getParameter("lng"); // 경도
		List<ErrandsDTO> list = errandsService.searchErrands(null, null, null, distance, lat, lng);
		System.out.println(distance + "반경으로" + list.size() + "개 검색됨");
		return list;
	}

	/**
	 * 심부르 등록하기 프로세스
	 * @param session 심부름 사진 저장을 위한 서버 경로 획득
	 * @param dto 심부름 정보 dto
	 */
	@RequestMapping("/insertErrand")
	@ResponseBody
	public Integer uploadImage(HttpSession session, ErrandsDTO dto) throws Exception {
		int result = 0;
		int errandsNum = 0;
		MultipartFile file = dto.getErrandsPhotoFile();
		// 심부름 상세 이미지가 있을경우
		if (file != null) {
			dto.setErrandsPhoto(file.getOriginalFilename());
			result = errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
			
			// 심부름 이미지 저장
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		} 
		// 심부름 상세이미지가  없을 경우
		else { 
			dto.setErrandsPhoto("EMPTY");
			result = errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
		}
		
		// 심부름이 성공적으로 insert 됬을 경우 푸쉬메세지 전송
		if (result > 0) { 
			ErrandsPosDTO posDTO = dto.getErrandsPos();
			// 심부름 등록 주소의 5km반경에 있는 사용자 토큰을 불러온다
			List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(),
					posDTO.getLongitude(), 5); 
			Map<String, String> params = new HashMap<>();
			params.put("errandsNum", errandsNum + "");
			params.put("requestUserId", dto.getRequestUser().getUserId());
			if (userTokenList != null && userTokenList.size() > 0)
				fcmPusher.pushFCMNotification(userTokenList, "두띵", "주변에 새심부름이 등록됬습니다!: " + dto.getTitle(), "DETAIL_ACTIVITY", params);
		}
		return result;
	}

	/**
	 * 내 심부름 요청 목록 불러오기
	 * @param request 유저 아이디값 불러오는 request
	 */
	@RequestMapping("/myRequest")
	@ResponseBody
	public List<ErrandsDTO> myRequest(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		return errandsService.myErrandsRequest(userId, 0);
	}

	/**
	 * 심부름 글 주문자의 상세정보 불러오기
	 * @param request 안드로이드에서 가져온 파라미터 값
	 */
	@RequestMapping("/requesterDetail")
	@ResponseBody
	public Map<String, Object> requesterDetail(HttpServletRequest request) throws Exception {
		String errandNum = (String) request.getParameter("errandNum"); // 심부름 번호
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
		return map;
	}

	/**
	 * 심부름 댓글 등록하기 프로세스
	 * @param request 안드로이드에서 가져온 값들
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
		
		//replyDTO 세팅
		replyDTO.setErrands(errandsDTO);
		replyDTO.setArrivalTime(arrivalTime);
		replyDTO.setReplyContent(replyContent);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(memberId);
		replyDTO.setUser(memberDTO);
		
		int result = errandsService.insertReply(replyDTO);
		
		// 댓글이 등록됬다고 요청자에게 푸쉬메세지를 보냄
		String token = androidService.selectTokenById(errandsDTO.getRequestUser().getUserId());
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		Map<String, String> params = new HashMap<>();
		params.put("errandsNum", errandNum);
		params.put("requestUserId",errandsDTO.getRequestUser().getUserId());
		fcmPusher.pushFCMNotification(tokenList, "댓글이 등록됨!", errandsDTO.getTitle() +"글에 댓글이 등록됬습니다!", "DETAIL_ACTIVITY", params);
		return result + "";

	}

	/**
	 * 내 심부름 수행 목록 불러오기
	 * @param request 유저 아이디값 불러오는 request
	 */
	@RequestMapping("/myResponse")
	@ResponseBody
	public List<ErrandsDTO> myResponse(HttpServletRequest request) {
		String userId = (String) request.getParameter("userId");
		return errandsService.myErrandsResponse(userId, 0);
	}

	/**
	 * 채팅 목록 가져오기
	 * @param request 안드로이드에서 가져온 파라미터
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
	 * 상대방과 채팅한 내역 가져오기
	 * @param request 안드로이드에서 가져온 파라미터(심부름 번호)
	 */
	@RequestMapping("/loadChat")
	@ResponseBody
	public List<String> loadChat(HttpServletRequest request) {
		String errandsNum = request.getParameter("errandsNum");
		return chatService.getContent(errandsNum);
	}

	/**
	 * 심부름 상세보기 프로세스
	 * @param request 안드로이드에서 가져온 파라미터(심부름 번호)
	 */
	@RequestMapping("/errandsDetail")
	@ResponseBody
	public Map<String, Object> errandsDetail(HttpServletRequest request) throws Exception {
		String errandNum = (String) request.getParameter("errandNum");

		Map<String, Object> map = new HashMap<>();
		ErrandsDTO errandsDTO = errandsService.selectErrands(Integer.parseInt(errandNum));

		// 결과에 심부름 상세 정보 추가
		map.put("productPrice", errandsDTO.getProductPrice());
		map.put("errandsPrice", errandsDTO.getErrandsPrice());
		map.put("address", errandsDTO.getErrandsPos().getAddr());
		map.put("errandContent", errandsDTO.getContent());
		map.put("errandImg", errandsDTO.getErrandsPhoto());
		map.put("replyList", errandsDTO.getErrandsReply());
		map.put("errandTime", errandsDTO.getEndTime());
		List<Integer> avgGpaList = new ArrayList<>();
		
		List<ErrandsReplyDTO> replyList = errandsDTO.getErrandsReply();
		
		// 댓글 정보들 추가
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
	 * 심부름 완료 요청
	 * @param gpaDTO 내가 매긴 평점 정보 DTO
	 * @param isRequest 내가 요청자인가 심부름꾼인가 판단
	 * @param hashContext 해쉬태그 내용
	 */
	@RequestMapping("/errandsFinish")
	@ResponseBody
	public boolean errandsFinishi(GPADTO gpaDTO, String isRequest, String hashContext) throws Exception {
		boolean result = false;
		ErrandsDTO errands = errandsService.selectErrands(gpaDTO.getErrandsNum());
		String responseUserId = errands.getResponseUser().getUserId();
		String requestUserId = errands.getRequestUser().getUserId();
		
		// 요청자가 확인할 경우
		if (isRequest.equals("true")) { 
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
			gpaDTO.setRequestManners(0);
			gpaDTO.setUserId(responseUserId);
			errandsService.okRequest(gpaDTO, responseUserId, hashContext, true);
			// 심부름꾼에게 푸쉬메세지 보내기
			String token = androidService.selectTokenById(responseUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				Map<String, String> params = new HashMap<>();
				params.put("errandsNum", gpaDTO.getErrandsNum() +"");
				fcmPusher.pushFCMNotification(tokenList, "심부름 완료 요청!", requestUserId + "님이 심부름 완료를 눌렀습니다!", "CHAT_ACTIVITY", params);
			}

		} 
		// 심부름꾼이 확인할 경우
		else { 
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, "arrival", null, 0);
			gpaDTO.setResponseAccuracy(0);
			gpaDTO.setResponseKindness(0);
			gpaDTO.setResponseSpeed(0);
			gpaDTO.setUserId(requestUserId);
			errandsService.okRequest(gpaDTO, requestUserId, hashContext, true);
			
			//요청자에게 푸쉬메세지 보내기
			String token = androidService.selectTokenById(requestUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				Map<String, String> params = new HashMap<>();
				params.put("errandsNum", gpaDTO.getErrandsNum() +"");
				fcmPusher.pushFCMNotification(tokenList, "심부름 완료 요청!", responseUserId + "님이 심부름 완료를 눌렀습니다!", "CHAT_ACTIVITY", params);
			}
		}

		ErrandsDTO upErrands = errandsService.selectErrands(gpaDTO.getErrandsNum()); 
		
		// 심부름꾼과 요청자가 모두 확인했을 경우
		if (upErrands.getArrivalTime() != null && upErrands.getFinishTime() != null) {
			// 포인트 업데이트
			int totalPrice = upErrands.getErrandsPrice() + upErrands.getProductPrice();
			memberService.updatePoint(totalPrice, upErrands.getResponseUser().getUserId());
			
			// 심부름꾼과 요청자에게 모두 푸쉬메세지 보내기
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

	/**
	 * 심부름을 리스트 형태로 볼 때
	 * @param keyword 검색할 값(제목 / 주소 포함)
	 * @param sort 정렬 순서(1 = 최신순, 2 = 가격 오름차순, 3 = 가격 내림차순)
	 */
	@RequestMapping("/errandListSearch")
	@ResponseBody
	public List<ErrandsDTO> searchErrandsList(String keyword, int sort){
		List<ErrandsDTO> list = errandsService.selectList(sort, keyword, keyword, 0);
		return list;
	}
	
	
	/**
	 * 심부름 수행 프로세스(요청자가 심부름을 선택)
	 * @param requestUserId 요청자 아이디
	 * @param strErrandNum 심부름 번호
	 * @param responseUserId 심부름꾼 아이디
	 */
	@RequestMapping("/startErrand")
	@ResponseBody
	public Map<String, Object> startErrand(String requestUserId, String strErrandNum, String responseUserId) throws Exception {

		Map<String, Object> map = new HashMap<>();
		int errandNum = Integer.parseInt(strErrandNum);
		MemberDTO requestUser = memberService.selectMemberById(requestUserId);
		ErrandsDTO currentErrand = errandsService.selectErrands(errandNum);
		
		// 요청자의 포인트가 부족 할 경우
		int totalPrice = currentErrand.getProductPrice() + currentErrand.getErrandsPrice();
		if (totalPrice > requestUser.getPoint().getCurrentPoint()) {
			map.put("result", "포인트가 부족합니다! 충전해주세요.");
			return map;
		}
		
		errandsService.updateErrands(errandNum, responseUserId, requestUser.getUserId(), "startTime", null, null, -totalPrice);
		requestUser.getPoint().setCurrentPoint((requestUser.getPoint().getCurrentPoint()) - totalPrice);
		
		// 웹에서의 알람 보내기
		memberService.insertNotification(responseUserId, "<a href='../errand/detailView?num="+errandNum+"'>"+ errandNum + "번 글의 " + requestUser.getName() + "님과 매칭되었습니다.</a>");
		
		// 심부름 위치추적 위치정보 세팅
		androidService.initLocation(errandNum, requestUser.getUserId());
		androidService.initLocation(errandNum, responseUserId);

		map.put("result", "성공적으로 매칭되었습니다!!");
		
		// 안드로이드에서 푸쉬메세지 보내기
		String responseToken = androidService.selectTokenById(responseUserId);
		List<String> tokenList = new ArrayList<String>();
		if(responseToken != null){
			tokenList.add(responseToken);
		}
		fcmPusher.pushFCMNotification(tokenList, "심부름 매칭됨!",currentErrand.getTitle() + "심부름에 매칭되었습니다!", null, null);
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
