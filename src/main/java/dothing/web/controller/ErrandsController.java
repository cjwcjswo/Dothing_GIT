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

/**
 * 심부름 관련 컨트롤러
 */
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

	/**
	 * 심부름 목록화면으로 이동
	 * @param aut 로그인 한 유저의 안읽은 알림을 체크하기 위함
	 * @param page 페이지
	 */
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

	/**
	 * 심부름 상세보기 페이지
	 * @param num 상세보기 할 심부름 게시글 번호
	 * @param aut 현재 유저의 정보를 읽어오기 위함
	 * @return
	 */
	@RequestMapping("/detailView")
	public ModelAndView detail(int num, Authentication aut) {
		ModelAndView mv = new ModelAndView();
		
		// 로그인 한 유저 정보 저장
		mv.addObject("currentId", ((MemberDTO) aut.getPrincipal()).getUserId());
		mv.addObject("currentUser", (MemberDTO) aut.getPrincipal());
		
		ErrandsDTO errands = errandsService.selectErrands(num);
		mv.addObject("errands", errands);
		// 심부름꾼이 있다면
		if (errands.getResponseUser() != null) {
			// 심부름꾼에 대한 정보를 추가
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
		// 요청자에 대한 정보를 추가
		String requestId = errands.getRequestUser().getUserId();
		String requestSelfImg = memberService.selectMemberById(requestId).getSelfImg();
		String requestUserName = memberService.selectMemberById(requestId).getName();

		mv.addObject("requestId", requestId);
		mv.addObject("requestSelfImg", requestSelfImg);
		mv.addObject("requestUserName", requestUserName);

		mv.setViewName("/errand/detailView");
		return mv;
	}

	/**
	 * 심부름 등록 페이지로 이동
	 */
	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		
		//모든 해시태그 힌트들 불러오기
		List<ErrandsHashtagDTO> list = errandsService.requestHash("");
		List<String> hashList = new ArrayList<>();
		for (ErrandsHashtagDTO dto : list) {
			hashList.add("'" + dto.getHashtag() + "'");
		}
		mv.addObject("hashList", hashList);
		mv.setViewName("/errand/register");
		return mv;
	}

	
	/**
	 * 심부름 등록 프로세스
	 * @param session 심부름 이미지를 저장시킬 경로를 위해서
	 * @param dto 심부름 내용 DTO
	 * @param preAddress 주소
	 * @param detailAddress 상세주소
	 */
	@RequestMapping("/insert")
	public ModelAndView insert(HttpSession session, ErrandsDTO dto, @RequestParam("preAddress") String preAddress,
			String detailAddress) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// 제약조건 검사
		dto.setEndTime(dto.getEndTime().replaceAll("T", " "));
		if (dto.getTitle() == null) {
			throw new Exception("제목을 입력하세요");
		}
		if (dto.getContent() == null) {
			throw new Exception("내용을 입력하세요");
		}
		if (preAddress == null || detailAddress == null) {
			throw new Exception("주소를 입력하세요");
		}

		dto.getErrandsPos().setAddr(preAddress + " " + detailAddress);
		MultipartFile file = dto.getErrandsPhotoFile();
		dto.setErrandsPhoto(file.getOriginalFilename());

		// 시간을 비교하기 위해서
		Date upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dto.getEndTime());
		Date currentTime = new Date();

		int errandsNum = 0;
		if (upTime.getTime() < currentTime.getTime()) {
			throw new Exception("마감 시간이 현재 시간보다 빠릅니다");
		}
		
		// 이미지 등록했을 경우, 파일이 확장자가 맞지 않을 경우 예외처리
		if (dto.getErrandsPhoto() != null && !dto.getErrandsPhoto().trim().equals("")) {
			System.out.println(dto.getErrandsPhoto());
			String ext = (dto.getErrandsPhoto().split("\\."))[1];
			ext = ext.toLowerCase();
			if (!(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif"))) {
				throw new Exception("확장자가 jpg, jpeg, png, gif인 파일만 업로드 할 수 있습니다");
			}
			
			errandsService.insertErrands(dto); // 심부름 등록
			errandsNum = errandsService.selectNum();
			
			// 이미지 저장
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsNum;
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));

		} 
		// 심부름 이미지를 등록하지 않았을 경우
		else {
			dto.setErrandsPhoto("EMPTY");
			errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
		}
		
		// 안드로이드에서 심부름이 등록된 주소의
		// 5km 반경내에서 살고있는 유저에게  심부름이 등록됬다는 푸시메세지를 보낸다
		ErrandsPosDTO posDTO = dto.getErrandsPos();
		List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(), posDTO.getLongitude(),
				5);
		Map<String, String> params = new HashMap<>();
		params.put("errandsNum", errandsNum + "");
		params.put("requestUserId", dto.getRequestUser().getUserId());
		if (userTokenList != null && userTokenList.size() > 0){
			fcmPusher.pushFCMNotification(userTokenList, "두띵", "주변에 새심부름이 등록됬습니다!: " + dto.getTitle(),
					"DETAIL_ACTIVITY", params);
		}
		
		mv.addObject("insertNum", errandsService.selectNum());
		mv.addObject("insertTitle", dto.getTitle());
		mv.addObject("insertImage", dto.getErrandsPhoto());
		mv.setViewName("/errand/empty");
		return mv;
	}

	/**
	 * 심부름꾼이 댓글을 다는 프로세스
	 * @param dto 심부름 댓글 정보 DTO
	 */
	@RequestMapping("/insertReply")
	public String insert(ErrandsReplyDTO dto) throws Exception {
		
		// 요청자가 올린 시간과 비교하는 과정
		dto.setArrivalTime(dto.getArrivalTime().replaceAll("T", " "));
		ErrandsDTO errand = errandsService.selectErrands(dto.getErrands().getErrandsNum());
		Date currentTime = new Date();
		Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(errand.getEndTime());
		Date upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dto.getArrivalTime());
		System.out.println(upTime.getTime() + " " + endTime.getTime());
		if (upTime.getTime() > endTime.getTime()) {
			throw new Exception("도착시간이 마감시간보다 느립니다");
		}
		if (upTime.getTime() < currentTime.getTime()) {
			throw new Exception("현재 시간보다 작게 입력하셨습니다.");
		}
		
		// 요청자에게 댓글이 달렸다고 알림메세지
		int num = errand.getErrandsNum();
		String requestUserId = errand.getRequestUser().getUserId();
		memberService.insertNotification(errand.getRequestUser().getUserId(),
				"<a href='../errand/detailView?num=" + num + "'>" + num + "번 글에 "
						+ memberService.selectMemberById(dto.getUser().getUserId()).getName() + "님이 댓글을 달았습니다!</a>");
		errandsService.insertReply(dto);
		
		// 안드로이드 폰으로 댓글이 달렸다고 푸쉬메세지 보내기
		Map<String, String> params = new HashMap<>();
		String token = androidService.selectTokenById(requestUserId);
		if (token != null) {
			List<String> userTokenList = new ArrayList<>();
			userTokenList.add(token);
			params.put("errandsNum", num + "");
			params.put("requestUserId", requestUserId);
			fcmPusher.pushFCMNotification(userTokenList, "두띵", errand.getTitle()+" 글에 댓글이 달렸습니다!",
					"DETAIL_ACTIVITY", params);
		}
		return "redirect:/errand/detailView?num=" + dto.getErrands().getErrandsNum();
	}

	/**
	 * 심부름 삭제 프로세스
	 * @param auth 로그인한 유저가 작성자인지 체크
	 * @param num 삭제할 심부름 게시글 번호
	 * @param id 작성자 아이디
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
	 * 심부름 댓글 삭제 프로세스
	 * @param num 삭제시킬 댓글 번호
	 * @param eNum 삭제된 댓글이 있는 심부름 게시글 번호
	 */
	@RequestMapping("/deleteReply")
	public String deleteReply(int num, int eNum) {
		errandsService.deleteReply(num);
		return "redirect:/errand/detailView?num=" + eNum;
	}

	/**
	 * 심부름 검색 프로세스
	 * @param aut 로그인 한 유저의 안읽은 알람을 가져오기 위함
	 * @param minPrice 최소 가격
	 * @param maxPrice 최대 가격
	 * @param hash 해쉬태그 검색
	 * @param distance 검색할 거리 반경
	 * @param sLat 검색할 위도
	 * @param sLng 검색할 경도
	 */
	@RequestMapping("/search")
	public ModelAndView search(Authentication aut, @RequestParam("minPrice") Integer minPrice,
			@RequestParam("maxPrice") Integer maxPrice, @RequestParam("hash") String hash, Integer distance,
			String sLat, String sLng) {
		
		if (distance == 0) distance = null; // 거리반경 검색을 안할 경우
		ModelAndView mv = new ModelAndView();
		String userId = ((MemberDTO) aut.getPrincipal()).getUserId();
		mv.addObject("notRead", memberService.notReadNoti(userId));
		mv.addObject("errandsList", errandsService.searchErrands(hash, minPrice, maxPrice, distance, sLat, sLng));
		mv.setViewName("/errand/errand");
		return mv;
	}

	/**
	 * 해쉬태그 검색창에 힌트를 띄어주는 프로세스
	 * @param hash 사용자가 입력한 키워드 값
	 */
	@RequestMapping("/hash")
	public ModelAndView requestHash(String hash) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hashList", errandsService.requestHash(hash));
		mv.setViewName("jsonView");
		return mv;
	}

	/**
	 * 심부름 요청 내역 확인 불러오기
	 * @param aut 로그인한 유저 정보
	 * @param page 페이지
	 */
	@RequestMapping("/myRequest")
	public ModelAndView myRequest(Authentication aut, Integer page) {
		MemberDTO dto = (MemberDTO) aut.getPrincipal();
		
		if (page == null) page = new Integer(1);
		
		//5개의 심부름 글을 한페이지로 묶음
		PageMaker pm = new PageMaker(page, errandsService.countMyRequest(dto.getUserId()) / 6 + 1);
		pm.start();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pm", pm);
		mv.addObject("errandsList", errandsService.myErrandsRequest(dto.getUserId(), page));
		mv.setViewName("/errand/myRequest");
		return mv;
	}

	/**
	 * 심부름 수행 내역 확인 불러오기
	 * @param aut 로그인 한 유저 정보
	 * @param page 페이지
	 * @return
	 */
	@RequestMapping("/myResponse")
	public ModelAndView myResponse(Authentication aut, Integer page) {
		MemberDTO dto = (MemberDTO) aut.getPrincipal();
		if (page == null) page = new Integer(1);
		
		// 5개의 심부름 글을 한페이지로 묶음
		PageMaker pm = new PageMaker(page, errandsService.countMyResponse(dto.getUserId()) / 6 + 1);
		pm.start();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.myErrandsResponse((dto).getUserId(), page));
		mv.addObject("myId", ((MemberDTO) aut.getPrincipal()).getUserId());
		mv.setViewName("/errand/myResponse");

		return mv;
	}

	/**
	 * 심부름 취소 프로세스
	 * @param aut 취소한 유저 정보
	 * @param num 취소시킬 심부름 번호
	 * @param point 취소하고 돌려받을 포인트
	 * @return
	 */
	@RequestMapping("/cancle")
	public ModelAndView cancleErrands(Authentication aut, int num, int point) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/errand/myRequest");
		MemberDTO currentMember = (MemberDTO) aut.getPrincipal();
		
		errandsService.cancelErrands(num, point, currentMember.getUserId());
		PointDTO currentPoint = currentMember.getPoint();
		currentPoint.setCurrentPoint(currentPoint.getCurrentPoint() + point);
		return mv;
	}

	/**
	 * 심부름꾼을 선택하고 매칭되는 프로세스
	 * @param aut 현재 로그인한 유저 정보
	 * @param num 심부름 게시글 번호
	 * @param responseId 심부름꾼 아이디
	 */
	@RequestMapping("/startErrand")
	public ModelAndView startErrand(Authentication aut, int num, String responseId) throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberDTO requestUser = (MemberDTO) aut.getPrincipal();
		ErrandsDTO currentErrand = errandsService.selectErrands(num);
		
		// 요청자가 요구한 포인트가 현재 내 포인트보다 많을 경우
		int totalPrice = currentErrand.getProductPrice() + currentErrand.getErrandsPrice();
		if (totalPrice > requestUser.getPoint().getCurrentPoint()) {
			throw new Exception("포인트가 부족합니다! 충전해주세요.");
		}
		
		// 심부름 시작
		errandsService.updateErrands(num, responseId, requestUser.getUserId(), "startTime", null, null, -totalPrice);
		requestUser.getPoint().setCurrentPoint((requestUser.getPoint().getCurrentPoint()) - totalPrice);
		memberService.insertNotification(responseId, "<a href='../errand/detailView?num=" + num + "'>" + num + "번 글의 "
				+ requestUser.getName() + "님과 매칭되었습니다.</a>");
		
		mv.addObject("num", num);
		mv.addObject("responseName", memberService.selectMemberById(responseId).getName());
		mv.addObject("responseId", responseId);
		
		// 안드로이드에서 심부름 위치추적 초기 세팅
		androidService.initLocation(num, requestUser.getUserId());
		androidService.initLocation(num, responseId);
		
		// 안드로이드에서 심부름이 매칭됬다고 푸시메세지 보내기
		String responseToken = androidService.selectTokenById(responseId);
		List<String> tokenList = new ArrayList<String>();
		if(responseToken != null){
			tokenList.add(responseToken);
		}
		fcmPusher.pushFCMNotification(tokenList, "심부름 매칭됨!",currentErrand.getTitle() + "심부름에 매칭되었습니다!", null, null);
		
		mv.setViewName("/errand/okay");
		return mv;
	}

	/**
	 * 물건을 받고나서 심부름 완료 프로세스
	 * @param aut 현재 로그인한 유저 정보
	 * @param requestId 요청자 아이디
	 * @param responseId 심부름꾼 아이디
	 * @param gpaDTO 평점 정보 DTO
	 * @param evalTag 유저에 대한 해시태그
	 */
	@RequestMapping("/confirmErrand")
	public ModelAndView confirmErrand(Authentication aut, String requestId, String responseId, GPADTO gpaDTO,
			String evalTag) throws Exception {
		ModelAndView mv = new ModelAndView();
		ErrandsDTO errands = errandsService.selectErrands(gpaDTO.getErrandsNum());
		String responseUserId = errands.getResponseUser().getUserId();
		String requestUserId = errands.getRequestUser().getUserId();
		
		// 요청자가 완료하기를 눌렀을 경우
		if (requestId != null) {
			// 심부름 완료시간 업데이트 / 심부름꾼에게 평점,해쉬태그 추가
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
			gpaDTO.setRequestManners(0);
			gpaDTO.setUserId(errands.getResponseUser().getUserId());
			errandsService.okRequest(gpaDTO, errands.getResponseUser().getUserId(), evalTag, false);
			
			// 안드로이드에서 심부름꾼에게 푸쉬메세지 보내기
			String token = androidService.selectTokenById(responseUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				Map<String, String> params = new HashMap<>();
				params.put("errandsNum", gpaDTO.getErrandsNum() +"");
				fcmPusher.pushFCMNotification(tokenList, "심부름 완료 요청!", requestUserId + "님이 심부름 완료를 눌렀습니다!", "CHAT_ACTIVITY", params);
			}

		} 
		// 심부름꾼이 완료하기를 눌렀을 경우
		else if (responseId != null) {
			// 심부름 완료시간 업데이트 / 요청자에게 평점,해쉬태그 추가
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, "arrival", null, 0);
			gpaDTO.setResponseAccuracy(0);
			gpaDTO.setResponseKindness(0);
			gpaDTO.setResponseSpeed(0);
			gpaDTO.setUserId(errands.getRequestUser().getUserId());
			errandsService.okRequest(gpaDTO, errands.getRequestUser().getUserId(), evalTag, false);
			
			// 안드로이드에서 요청자에게 푸쉬메세지 보내기
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
			MemberDTO loginUser = (MemberDTO) aut.getPrincipal();
			
			// 로그인 한 유저가 심부름꾼이면 바로 포인트 적용
			if (loginUser.getUserId().equals(upErrands.getResponseUser().getUserId())) {
				loginUser.getPoint().setCurrentPoint(loginUser.getPoint().getCurrentPoint() + totalPrice);
			}
			
			// 안드로이드에서 양측에게 심부름이 완료됬다고 푸쉬메세지 보내기
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
		mv.setViewName("redirect:/errand/detailView?num=" + gpaDTO.getErrandsNum());
		return mv;
	}

	/**
	 * 심부름 목록을 리스트로 보기 
	 * @param sort 정렬 순서(1 = 최신순, 2 = 가격 오름차순, 3 = 가격 내림차순)
	 * @param addr 검색 주소
	 * @param page 페이지
	 * @return
	 */
	@RequestMapping("/listing")
	public ModelAndView listing(Integer sort, String addr, Integer page) {
		ModelAndView mv = new ModelAndView();
		if (page == null) page = 1;
		// 10개의 심부름글을 하나로 묶어서 보여줌
		PageMaker pm = new PageMaker(page, (errandsService.countList(addr) / 11) + 1);
		pm.start();
		if (sort == null) sort = 1;
		
		if (addr != null) {
			if (addr.trim().equals(""))
				addr = null;
		}
		
		mv.addObject("errandsList", errandsService.selectList(sort, addr, null, page));
		mv.addObject("pm", pm);
		mv.addObject("sort", sort);
		mv.addObject("addr", addr);
		return mv;
	}
}
