package dothing.web.android.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	public List<ErrandsDTO> errandSearch(HttpServletRequest request) {
		String lat = (String) request.getParameter("lat");
		String lng = (String) request.getParameter("lng");
		System.out.println(lat + " : " + lng);
		List<ErrandsDTO> list = errandsService.searchErrands(null, null, null, 3, lat, lng);
		System.out.println(list.size() + "개 검색됨");
		return list;
	}

	/**
	 * 안드로이드 심부름 등록하기
	 */
	@RequestMapping("/insertErrand")
	@ResponseBody
	public Integer uploadImage(HttpSession session, ErrandsDTO dto) throws Exception {
		int result = 0;

		MultipartFile file = dto.getErrandsPhotoFile();
		if (file != null) {
			dto.setErrandsPhoto(file.getOriginalFilename());
			System.out.println("파일이 있을경우" + dto + " 삽입");
			result = errandsService.insertErrands(dto);
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		} else {
			dto.setErrandsPhoto("EMPTY");
			System.out.println("파일이 없을경우" + dto + " 삽입");
			result = errandsService.insertErrands(dto);
		}
		if (result > 0) {
			ErrandsPosDTO posDTO = dto.getErrandsPos();
			List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(),
					posDTO.getLongitude(), 5);
			if (userTokenList != null && userTokenList.size() > 0)
				fcmPusher.pushFCMNotification(userTokenList, "두띵", "주변에 새심부름이 등록됬습니다!: " + dto.getTitle());
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
		

		return map;
	}

	// 댓글 등록
	@RequestMapping("/insertReply")
	@ResponseBody
	public String insertReply(HttpServletRequest request) {
		String memberId = (String) request.getParameter("memberId");
		String errandNum = (String) request.getParameter("errandNum");
		String arrivalTime = (String) request.getParameter("arrivalTime");
		String replyContent = (String) request.getParameter("replyContent");

		ErrandsReplyDTO replyDTO = new ErrandsReplyDTO();
		ErrandsDTO errandsDTO = new ErrandsDTO();
		errandsDTO.setErrandsNum(Integer.parseInt(errandNum));
		replyDTO.setErrands(errandsDTO);
		replyDTO.setArrivalTime(arrivalTime);
		replyDTO.setReplyContent(replyContent);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(memberId);
		replyDTO.setUser(memberDTO);

		int result = errandsService.insertReply(replyDTO);

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
	public List<String> loadChat(HttpServletRequest request){
		String errandsNum = request.getParameter("errandsNum");
		return chatService.getContent(errandsNum);
	}
	/*
	 * 심부름 상세정보 가져오기
	 * */
	@RequestMapping("/errandsDetail")
	@ResponseBody
	public Map<String, Object> errandsDetail(HttpServletRequest request) throws Exception{
		String errandNum = (String)request.getParameter("errandNum");
		
		Map<String, Object> map = new HashMap<>();
		ErrandsDTO errandsDTO = errandsService.selectErrands(Integer.parseInt(errandNum));
		
		map.put("productPrice", errandsDTO.getProductPrice());
		map.put("errandsPrice", errandsDTO.getErrandsPrice());
		map.put("address", errandsDTO.getErrandsPos().getAddr());
		map.put("errandContent", errandsDTO.getContent());
		map.put("errandImg", errandsDTO.getErrandsPhoto());
		map.put("replyList", errandsDTO.getErrandsReply());
		
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
			int avg = Math.round(sum / gpaList.size());
			avgGpaList.add(avg);
			
		}
		map.put("avgGpaList", avgGpaList);
		
	
		
		return map;
	}
	
	

}
