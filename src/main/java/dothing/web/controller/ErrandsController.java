package dothing.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.PointDTO;
import dothing.web.service.AndroidService;
import dothing.web.service.ChatService;
import dothing.web.service.ErrandsService;
import dothing.web.service.MemberService;
import dothing.web.util.FcmPusher;
import dothing.web.util.PageMaker;

@Controller
@RequestMapping("/errand")
public class ErrandsController {
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

	@RequestMapping("/errand")
	public ModelAndView errandsList(Authentication aut, Integer page) {
		MemberDTO member = (MemberDTO) aut.getPrincipal();
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.selectAll()); // 심부름 리스트
		mv.addObject("rankedList", memberService.selectRanked()); // 심부름꾼 랭킹
		mv.addObject("moneyList", errandsService.moneyErrands()); // 돈되는 심부름
		mv.addObject("notRead", memberService.notReadNoti(member.getUserId()));
		mv.setViewName("/errand/errand");
		return mv;
	}

	@RequestMapping("/detailView")
	public ModelAndView detail(int num, Authentication aut) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentId", ((MemberDTO) aut.getPrincipal()).getUserId());
		mv.addObject("currentUser", (MemberDTO) aut.getPrincipal());
		ErrandsDTO errands = errandsService.selectErrands(num);
		mv.addObject("errands", errands);
		if (errands.getResponseUser() != null) {
			String responseId = errands.getResponseUser().getUserId();
			String responseUserName = errands.getResponseUser().getName();
			String responseSelfImg = memberService.selectMemberById(responseId).getSelfImg();
			mv.addObject("responseSelfImg", responseSelfImg);
			mv.addObject("responseId", responseId);
			mv.addObject("responseUserName", responseUserName);
			List<String> list = chatService.getContent(num + "");
			if (list != null) {
				mv.addObject("list", chatService.getContent(num + ""));
			}
		}
		String requestId = errands.getRequestUser().getUserId();
		

		String requestSelfImg = memberService.selectMemberById(requestId).getSelfImg();
		String requestUserName = memberService.selectMemberById(requestId).getName();
		System.out.println("requestUserName : " + requestUserName);

		mv.addObject("requestId", requestId);
		mv.addObject("requestSelfImg", requestSelfImg);
		mv.addObject("requestUserName", requestUserName);

		
		mv.setViewName("/errand/detailView");
		return mv;
	}

	/**
	 * 심부름 등록페이지로 이동
	 */
	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		List<ErrandsHashtagDTO> list = errandsService.requestHash("");
		List<String> hashList = new ArrayList<>();
		for(ErrandsHashtagDTO dto:list){
			hashList.add("'" + dto.getHashtag() + "'");
		}
		mv.addObject("hashList", hashList);
		mv.setViewName("/errand/register");
		return mv;
	}

	@RequestMapping("/insert")
	public ModelAndView insert(HttpSession session, ErrandsDTO dto, @RequestParam("preAddress") String preAddress,
			String detailAddress) throws Exception {
		ModelAndView mv = new ModelAndView();
		dto.setEndTime(dto.getEndTime().replaceAll("T", " "));
		if(dto.getTitle() == null){
			throw new Exception("제목을 입력하세요");
		}
		if(dto.getContent() == null){
			throw new Exception("내용을 입력하세요");
		}
		if(preAddress == null || detailAddress == null){
			throw new Exception("주소를 입력하세요");
		}
		
		dto.getErrandsPos().setAddr(preAddress + " " + detailAddress);
		MultipartFile file = dto.getErrandsPhotoFile();
		dto.setErrandsPhoto(file.getOriginalFilename());
		
		Date upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dto.getEndTime());
		Date currentTime = new Date();
		
	int errandsNum = 0;
		if(upTime.getTime() < currentTime.getTime()){
			throw new Exception("마감 시간이 현재 시간보다 빠릅니다");
		}
		//파일이 확장자가 맞지 않을 경우 예외처리
		if (dto.getErrandsPhoto() != null && !dto.getErrandsPhoto().trim().equals("")) {
			System.out.println(dto.getErrandsPhoto());
			String ext = (dto.getErrandsPhoto().split("\\."))[1];
			ext = ext.toLowerCase();
			if (!(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif"))) {
				throw new Exception("확장자가 jpg, jpeg, png, gif인 파일만 업로드 할 수 있습니다");
			}
			errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsNum;
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));

		} else {
			dto.setErrandsPhoto("EMPTY");
			errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
		}
		
		ErrandsPosDTO posDTO = dto.getErrandsPos();
		System.out.println(posDTO.getLatitude() +":"+posDTO.getLongitude());
		List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(), posDTO.getLongitude(), 5);
		Map<String, String> params = new HashMap<>();
		params.put("errandsNum", errandsNum +"");
		params.put("requestUserId", dto.getRequestUser().getUserId());
		if(userTokenList !=  null&&userTokenList.size() > 0  )
			fcmPusher.pushFCMNotification(userTokenList, "두띵", "주변에 새심부름이 등록됬습니다!: " + dto.getTitle(), "DETAIL_ACTIVITY", params);


		mv.addObject("insertNum", errandsService.selectNum());
		mv.addObject("insertTitle", dto.getTitle());
		mv.addObject("insertImage", dto.getErrandsPhoto());
		mv.setViewName("/errand/empty");
		return mv;
	}

	@RequestMapping("/insertReply")
	public String insert(HttpSession session, ErrandsReplyDTO dto) throws Exception {
		dto.setArrivalTime(dto.getArrivalTime().replaceAll("T", " "));
		ErrandsDTO errand = errandsService.selectErrands(dto.getErrands().getErrandsNum());
		Date currentTime = new Date();
		Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(errand.getEndTime());
		Date upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dto.getArrivalTime());
		System.out.println(upTime.getTime() + " " + endTime.getTime());
		if(upTime.getTime() > endTime.getTime()){
			throw new Exception("도착시간이 마감시간보다 느립니다");
		}
		if(upTime.getTime() < currentTime.getTime()){
			throw new Exception("현재 시간보다 작게 입력하셨습니다.");
		}
		int num = errand.getErrandsNum();
		memberService.insertNotification(errand.getRequestUser().getUserId(),
				"<a href='../errand/detailView?num="+num+"'>"+num + "번 글에 " + memberService.selectMemberById(dto.getUser().getUserId()).getName() + "님이 댓글을 달았습니다!</a>");
		errandsService.insertReply(dto);
		return "redirect:/errand/detailView?num=" + dto.getErrands().getErrandsNum();
	}

	/**
	 * 심부름 삭제
	 */
	@RequestMapping("/deleteErrands")
	public String deleteErrands(Authentication auth, int num, String id) throws Exception {
		if (!id.equals(((MemberDTO) auth.getPrincipal()).getUserId())) {
			throw new Exception("작성자가 아닙니다");
		}
		errandsService.deleteErrands(num);
		return "redirect:/errand/errand";
	}

	/**
	 * 리플 삭제
	 */
	@RequestMapping("/deleteReply")
	public String deleteReply(int num, int eNum) {
		errandsService.deleteReply(num);
		return "redirect:/errand/detailView?num=" + eNum;
	}

	@RequestMapping("/search")
	public ModelAndView search(Authentication aut, @RequestParam("minPrice") Integer minPrice, @RequestParam("maxPrice") Integer maxPrice,
			@RequestParam("hash") String hash, Integer distance, String sLat, String sLng) {
		System.out.println(
				"최소: " + minPrice + " 최대: " + maxPrice + " 해쉬: " + hash + " " + distance + " " + sLat + " " + sLng);
		if (distance == 0)
			distance = null;
		ModelAndView mv = new ModelAndView();
		String userId = ((MemberDTO)aut.getPrincipal()).getUserId();
		mv.addObject("notRead", memberService.notReadNoti(userId));
		mv.addObject("errandsList", errandsService.searchErrands(hash, minPrice, maxPrice, distance, sLat, sLng));
		mv.setViewName("/errand/errand");
		return mv;
	}
	/**
	 * 검색에서의 해쉬요청
	 */
	@RequestMapping("/hash")
	public ModelAndView requestHash(String hash) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hashList", errandsService.requestHash(hash));
		mv.setViewName("jsonView");
		return mv;
	}
	

	/**
	 * 심부름 요청내역 확인
	 */
	@RequestMapping("/myRequest")
	public ModelAndView myRequest(Authentication aut, Integer page) {
		MemberDTO dto = (MemberDTO) aut.getPrincipal();
		if (page == null)
			page = new Integer(1);
		PageMaker pm = new PageMaker(page, errandsService.countMyRequest(dto.getUserId()) / 6 + 1);
		pm.start();
		ModelAndView mv = new ModelAndView();
		mv.addObject("pm", pm);
		mv.addObject("errandsList", errandsService.myErrandsRequest(dto.getUserId(), page));
		mv.setViewName("/errand/myRequest");
		return mv;
	}

	/**
	 * 심부름 수행내역 확인
	 */
	@RequestMapping("/myResponse")
	public ModelAndView myResponse(Authentication aut, Integer page) {
		MemberDTO dto = (MemberDTO) aut.getPrincipal();
		if (page == null)
			page = new Integer(1);
		PageMaker pm = new PageMaker(page, errandsService.countMyResponse(dto.getUserId()) / 6 + 1);
		pm.start();
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.myErrandsResponse((dto).getUserId(), page));
		mv.addObject("myId", ((MemberDTO) aut.getPrincipal()).getUserId());
		mv.setViewName("/errand/myResponse");

		return mv;
	}

	/**
	 * 심부름 취소
	 */
	@RequestMapping("/cancle")
	public ModelAndView cancleErrands(Authentication aut, int num, int point) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/errand/myRequest");
		MemberDTO currentMember = (MemberDTO) aut.getPrincipal();
		errandsService.cancleErrands(num, point, currentMember.getUserId());
		PointDTO currentPoint = currentMember.getPoint();
		currentPoint.setCurrentPoint(currentPoint.getCurrentPoint() + point);
		return mv;
	}

	/**
	 * 심부름 수행 프로세스(심부름꾼 선택했을 때)
	 */
	@RequestMapping("/startErrand")
	public ModelAndView startErrand(Authentication aut, int num, String responseId) throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberDTO requestUser = (MemberDTO) aut.getPrincipal();
		ErrandsDTO currentErrand = errandsService.selectErrands(num);
		int totalPrice = currentErrand.getProductPrice() + currentErrand.getErrandsPrice();
		if (totalPrice > requestUser.getPoint().getCurrentPoint()) {
			throw new Exception("포인트가 부족합니다! 충전해주세요.");
		}
		errandsService.updateErrands(num, responseId, requestUser.getUserId(), "startTime", null, null, -totalPrice);
		requestUser.getPoint().setCurrentPoint((requestUser.getPoint().getCurrentPoint()) - totalPrice);
		memberService.insertNotification(responseId, "<a href='../errand/detailView?num="+num+"'>"+ num + "번 글의 " + requestUser.getName() + "님과 매칭되었습니다.</a>");
		mv.addObject("num", num);
		mv.addObject("responseName", memberService.selectMemberById(responseId).getName());
		mv.addObject("responseId", responseId);
		androidService.initLocation(num, requestUser.getUserId());
		androidService.initLocation(num, responseId);
		mv.setViewName("/errand/okay");
		return mv;
	}

	/**
	 * 심부름 확인 프로세스
	 */
	@RequestMapping("/confirmErrand")
	public ModelAndView confirmErrand(Authentication aut, String requestId, String responseId, GPADTO gpaDTO,
			String evalTag) {
		ModelAndView mv = new ModelAndView();
		ErrandsDTO errands = errandsService.selectErrands(gpaDTO.getErrandsNum()); // 해당
																					// 심부름
																					// 불러오기

		if (requestId != null) { // 요청자가 확인할 경우
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
			gpaDTO.setRequestManners(0);
			gpaDTO.setUserId(errands.getResponseUser().getUserId());
			errandsService.okRequest(gpaDTO, errands.getResponseUser().getUserId(), evalTag, false);

		} else if (responseId != null) { // 심부름꾼이 확인할 경우
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, "arrival", null, 0);
			gpaDTO.setResponseAccuracy(0);
			gpaDTO.setResponseKindness(0);
			gpaDTO.setResponseSpeed(0);
			gpaDTO.setUserId(errands.getRequestUser().getUserId());
			errandsService.okRequest(gpaDTO, errands.getRequestUser().getUserId(), evalTag, false);
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
			MemberDTO loginUser = (MemberDTO) aut.getPrincipal();
			if (loginUser.getUserId().equals(upErrands.getResponseUser().getUserId())) {
				loginUser.getPoint().setCurrentPoint(loginUser.getPoint().getCurrentPoint() + totalPrice);
			}

		}
		mv.setViewName("redirect:/errand/detailView?num=" + gpaDTO.getErrandsNum());
		return mv;
	}

	/**
	 * 리스트로보기
	 */
	@RequestMapping("/listing")
	public ModelAndView listing(Integer sort, String addr, Integer page) {
		ModelAndView mv = new ModelAndView();
		if (page == null)
			page = 1;
		PageMaker pm = new PageMaker(page, (errandsService.countList(addr) / 11) + 1);
		if (sort == null) {
			sort = 1;
		}
		if (addr != null) {
			if (addr.trim().equals(""))
				addr = null;
		}
		pm.start();
		mv.addObject("errandsList", errandsService.selectList(sort, addr, null, page));
		mv.addObject("pm", pm);
		mv.addObject("sort", sort);
		mv.addObject("addr", addr);
		return mv;
	}
}
