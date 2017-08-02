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
 * �ɺθ� ���� ��Ʈ�ѷ�
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
	 * �ɺθ� ���ȭ������ �̵�
	 * @param aut �α��� �� ������ ������ �˸��� üũ�ϱ� ����
	 * @param page ������
	 */
	@RequestMapping("/errand")
	public ModelAndView errandsList(Authentication aut, Integer page) {
		MemberDTO member = (MemberDTO) aut.getPrincipal();
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.selectAll()); // �ɺθ� ����Ʈ
		mv.addObject("rankedList", memberService.selectRanked()); // �ɺθ��� ��ŷ
		mv.addObject("moneyList", errandsService.moneyErrands()); // ���Ǵ� �ɺθ�
		mv.addObject("notRead", memberService.notReadNoti(member.getUserId()));
		mv.setViewName("/errand/errand");
		return mv;
	}

	/**
	 * �ɺθ� �󼼺��� ������
	 * @param num �󼼺��� �� �ɺθ� �Խñ� ��ȣ
	 * @param aut ���� ������ ������ �о���� ����
	 * @return
	 */
	@RequestMapping("/detailView")
	public ModelAndView detail(int num, Authentication aut) {
		ModelAndView mv = new ModelAndView();
		
		// �α��� �� ���� ���� ����
		mv.addObject("currentId", ((MemberDTO) aut.getPrincipal()).getUserId());
		mv.addObject("currentUser", (MemberDTO) aut.getPrincipal());
		
		ErrandsDTO errands = errandsService.selectErrands(num);
		mv.addObject("errands", errands);
		// �ɺθ����� �ִٸ�
		if (errands.getResponseUser() != null) {
			// �ɺθ��ۿ� ���� ������ �߰�
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
		// ��û�ڿ� ���� ������ �߰�
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
	 * �ɺθ� ��� �������� �̵�
	 */
	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		
		//��� �ؽ��±� ��Ʈ�� �ҷ�����
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
	 * �ɺθ� ��� ���μ���
	 * @param session �ɺθ� �̹����� �����ų ��θ� ���ؼ�
	 * @param dto �ɺθ� ���� DTO
	 * @param preAddress �ּ�
	 * @param detailAddress ���ּ�
	 */
	@RequestMapping("/insert")
	public ModelAndView insert(HttpSession session, ErrandsDTO dto, @RequestParam("preAddress") String preAddress,
			String detailAddress) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// �������� �˻�
		dto.setEndTime(dto.getEndTime().replaceAll("T", " "));
		if (dto.getTitle() == null) {
			throw new Exception("������ �Է��ϼ���");
		}
		if (dto.getContent() == null) {
			throw new Exception("������ �Է��ϼ���");
		}
		if (preAddress == null || detailAddress == null) {
			throw new Exception("�ּҸ� �Է��ϼ���");
		}

		dto.getErrandsPos().setAddr(preAddress + " " + detailAddress);
		MultipartFile file = dto.getErrandsPhotoFile();
		dto.setErrandsPhoto(file.getOriginalFilename());

		// �ð��� ���ϱ� ���ؼ�
		Date upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dto.getEndTime());
		Date currentTime = new Date();

		int errandsNum = 0;
		if (upTime.getTime() < currentTime.getTime()) {
			throw new Exception("���� �ð��� ���� �ð����� �����ϴ�");
		}
		
		// �̹��� ������� ���, ������ Ȯ���ڰ� ���� ���� ��� ����ó��
		if (dto.getErrandsPhoto() != null && !dto.getErrandsPhoto().trim().equals("")) {
			System.out.println(dto.getErrandsPhoto());
			String ext = (dto.getErrandsPhoto().split("\\."))[1];
			ext = ext.toLowerCase();
			if (!(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif"))) {
				throw new Exception("Ȯ���ڰ� jpg, jpeg, png, gif�� ���ϸ� ���ε� �� �� �ֽ��ϴ�");
			}
			
			errandsService.insertErrands(dto); // �ɺθ� ���
			errandsNum = errandsService.selectNum();
			
			// �̹��� ����
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsNum;
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));

		} 
		// �ɺθ� �̹����� ������� �ʾ��� ���
		else {
			dto.setErrandsPhoto("EMPTY");
			errandsService.insertErrands(dto);
			errandsNum = errandsService.selectNum();
		}
		
		// �ȵ���̵忡�� �ɺθ��� ��ϵ� �ּ���
		// 5km �ݰ泻���� ����ִ� ��������  �ɺθ��� ��ω�ٴ� Ǫ�ø޼����� ������
		ErrandsPosDTO posDTO = dto.getErrandsPos();
		List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(), posDTO.getLongitude(),
				5);
		Map<String, String> params = new HashMap<>();
		params.put("errandsNum", errandsNum + "");
		params.put("requestUserId", dto.getRequestUser().getUserId());
		if (userTokenList != null && userTokenList.size() > 0){
			fcmPusher.pushFCMNotification(userTokenList, "�ζ�", "�ֺ��� ���ɺθ��� ��ω���ϴ�!: " + dto.getTitle(),
					"DETAIL_ACTIVITY", params);
		}
		
		mv.addObject("insertNum", errandsService.selectNum());
		mv.addObject("insertTitle", dto.getTitle());
		mv.addObject("insertImage", dto.getErrandsPhoto());
		mv.setViewName("/errand/empty");
		return mv;
	}

	/**
	 * �ɺθ����� ����� �ٴ� ���μ���
	 * @param dto �ɺθ� ��� ���� DTO
	 */
	@RequestMapping("/insertReply")
	public String insert(ErrandsReplyDTO dto) throws Exception {
		
		// ��û�ڰ� �ø� �ð��� ���ϴ� ����
		dto.setArrivalTime(dto.getArrivalTime().replaceAll("T", " "));
		ErrandsDTO errand = errandsService.selectErrands(dto.getErrands().getErrandsNum());
		Date currentTime = new Date();
		Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(errand.getEndTime());
		Date upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dto.getArrivalTime());
		System.out.println(upTime.getTime() + " " + endTime.getTime());
		if (upTime.getTime() > endTime.getTime()) {
			throw new Exception("�����ð��� �����ð����� �����ϴ�");
		}
		if (upTime.getTime() < currentTime.getTime()) {
			throw new Exception("���� �ð����� �۰� �Է��ϼ̽��ϴ�.");
		}
		
		// ��û�ڿ��� ����� �޷ȴٰ� �˸��޼���
		int num = errand.getErrandsNum();
		String requestUserId = errand.getRequestUser().getUserId();
		memberService.insertNotification(errand.getRequestUser().getUserId(),
				"<a href='../errand/detailView?num=" + num + "'>" + num + "�� �ۿ� "
						+ memberService.selectMemberById(dto.getUser().getUserId()).getName() + "���� ����� �޾ҽ��ϴ�!</a>");
		errandsService.insertReply(dto);
		
		// �ȵ���̵� ������ ����� �޷ȴٰ� Ǫ���޼��� ������
		Map<String, String> params = new HashMap<>();
		String token = androidService.selectTokenById(requestUserId);
		if (token != null) {
			List<String> userTokenList = new ArrayList<>();
			userTokenList.add(token);
			params.put("errandsNum", num + "");
			params.put("requestUserId", requestUserId);
			fcmPusher.pushFCMNotification(userTokenList, "�ζ�", errand.getTitle()+" �ۿ� ����� �޷Ƚ��ϴ�!",
					"DETAIL_ACTIVITY", params);
		}
		return "redirect:/errand/detailView?num=" + dto.getErrands().getErrandsNum();
	}

	/**
	 * �ɺθ� ���� ���μ���
	 * @param auth �α����� ������ �ۼ������� üũ
	 * @param num ������ �ɺθ� �Խñ� ��ȣ
	 * @param id �ۼ��� ���̵�
	 */
	@RequestMapping("/deleteErrands")
	public String deleteErrands(Authentication auth, int num, String id) throws Exception {
		if (!id.equals(((MemberDTO) auth.getPrincipal()).getUserId())) {
			throw new Exception("�ۼ��ڰ� �ƴմϴ�");
		}
		errandsService.deleteErrands(num);
		return "redirect:/errand/errand";
	}

	/**
	 * �ɺθ� ��� ���� ���μ���
	 * @param num ������ų ��� ��ȣ
	 * @param eNum ������ ����� �ִ� �ɺθ� �Խñ� ��ȣ
	 */
	@RequestMapping("/deleteReply")
	public String deleteReply(int num, int eNum) {
		errandsService.deleteReply(num);
		return "redirect:/errand/detailView?num=" + eNum;
	}

	/**
	 * �ɺθ� �˻� ���μ���
	 * @param aut �α��� �� ������ ������ �˶��� �������� ����
	 * @param minPrice �ּ� ����
	 * @param maxPrice �ִ� ����
	 * @param hash �ؽ��±� �˻�
	 * @param distance �˻��� �Ÿ� �ݰ�
	 * @param sLat �˻��� ����
	 * @param sLng �˻��� �浵
	 */
	@RequestMapping("/search")
	public ModelAndView search(Authentication aut, @RequestParam("minPrice") Integer minPrice,
			@RequestParam("maxPrice") Integer maxPrice, @RequestParam("hash") String hash, Integer distance,
			String sLat, String sLng) {
		
		if (distance == 0) distance = null; // �Ÿ��ݰ� �˻��� ���� ���
		ModelAndView mv = new ModelAndView();
		String userId = ((MemberDTO) aut.getPrincipal()).getUserId();
		mv.addObject("notRead", memberService.notReadNoti(userId));
		mv.addObject("errandsList", errandsService.searchErrands(hash, minPrice, maxPrice, distance, sLat, sLng));
		mv.setViewName("/errand/errand");
		return mv;
	}

	/**
	 * �ؽ��±� �˻�â�� ��Ʈ�� ����ִ� ���μ���
	 * @param hash ����ڰ� �Է��� Ű���� ��
	 */
	@RequestMapping("/hash")
	public ModelAndView requestHash(String hash) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hashList", errandsService.requestHash(hash));
		mv.setViewName("jsonView");
		return mv;
	}

	/**
	 * �ɺθ� ��û ���� Ȯ�� �ҷ�����
	 * @param aut �α����� ���� ����
	 * @param page ������
	 */
	@RequestMapping("/myRequest")
	public ModelAndView myRequest(Authentication aut, Integer page) {
		MemberDTO dto = (MemberDTO) aut.getPrincipal();
		
		if (page == null) page = new Integer(1);
		
		//5���� �ɺθ� ���� ���������� ����
		PageMaker pm = new PageMaker(page, errandsService.countMyRequest(dto.getUserId()) / 6 + 1);
		pm.start();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pm", pm);
		mv.addObject("errandsList", errandsService.myErrandsRequest(dto.getUserId(), page));
		mv.setViewName("/errand/myRequest");
		return mv;
	}

	/**
	 * �ɺθ� ���� ���� Ȯ�� �ҷ�����
	 * @param aut �α��� �� ���� ����
	 * @param page ������
	 * @return
	 */
	@RequestMapping("/myResponse")
	public ModelAndView myResponse(Authentication aut, Integer page) {
		MemberDTO dto = (MemberDTO) aut.getPrincipal();
		if (page == null) page = new Integer(1);
		
		// 5���� �ɺθ� ���� ���������� ����
		PageMaker pm = new PageMaker(page, errandsService.countMyResponse(dto.getUserId()) / 6 + 1);
		pm.start();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.myErrandsResponse((dto).getUserId(), page));
		mv.addObject("myId", ((MemberDTO) aut.getPrincipal()).getUserId());
		mv.setViewName("/errand/myResponse");

		return mv;
	}

	/**
	 * �ɺθ� ��� ���μ���
	 * @param aut ����� ���� ����
	 * @param num ��ҽ�ų �ɺθ� ��ȣ
	 * @param point ����ϰ� �������� ����Ʈ
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
	 * �ɺθ����� �����ϰ� ��Ī�Ǵ� ���μ���
	 * @param aut ���� �α����� ���� ����
	 * @param num �ɺθ� �Խñ� ��ȣ
	 * @param responseId �ɺθ��� ���̵�
	 */
	@RequestMapping("/startErrand")
	public ModelAndView startErrand(Authentication aut, int num, String responseId) throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberDTO requestUser = (MemberDTO) aut.getPrincipal();
		ErrandsDTO currentErrand = errandsService.selectErrands(num);
		
		// ��û�ڰ� �䱸�� ����Ʈ�� ���� �� ����Ʈ���� ���� ���
		int totalPrice = currentErrand.getProductPrice() + currentErrand.getErrandsPrice();
		if (totalPrice > requestUser.getPoint().getCurrentPoint()) {
			throw new Exception("����Ʈ�� �����մϴ�! �������ּ���.");
		}
		
		// �ɺθ� ����
		errandsService.updateErrands(num, responseId, requestUser.getUserId(), "startTime", null, null, -totalPrice);
		requestUser.getPoint().setCurrentPoint((requestUser.getPoint().getCurrentPoint()) - totalPrice);
		memberService.insertNotification(responseId, "<a href='../errand/detailView?num=" + num + "'>" + num + "�� ���� "
				+ requestUser.getName() + "�԰� ��Ī�Ǿ����ϴ�.</a>");
		
		mv.addObject("num", num);
		mv.addObject("responseName", memberService.selectMemberById(responseId).getName());
		mv.addObject("responseId", responseId);
		
		// �ȵ���̵忡�� �ɺθ� ��ġ���� �ʱ� ����
		androidService.initLocation(num, requestUser.getUserId());
		androidService.initLocation(num, responseId);
		
		// �ȵ���̵忡�� �ɺθ��� ��Ī��ٰ� Ǫ�ø޼��� ������
		String responseToken = androidService.selectTokenById(responseId);
		List<String> tokenList = new ArrayList<String>();
		if(responseToken != null){
			tokenList.add(responseToken);
		}
		fcmPusher.pushFCMNotification(tokenList, "�ɺθ� ��Ī��!",currentErrand.getTitle() + "�ɺθ��� ��Ī�Ǿ����ϴ�!", null, null);
		
		mv.setViewName("/errand/okay");
		return mv;
	}

	/**
	 * ������ �ް��� �ɺθ� �Ϸ� ���μ���
	 * @param aut ���� �α����� ���� ����
	 * @param requestId ��û�� ���̵�
	 * @param responseId �ɺθ��� ���̵�
	 * @param gpaDTO ���� ���� DTO
	 * @param evalTag ������ ���� �ؽ��±�
	 */
	@RequestMapping("/confirmErrand")
	public ModelAndView confirmErrand(Authentication aut, String requestId, String responseId, GPADTO gpaDTO,
			String evalTag) throws Exception {
		ModelAndView mv = new ModelAndView();
		ErrandsDTO errands = errandsService.selectErrands(gpaDTO.getErrandsNum());
		String responseUserId = errands.getResponseUser().getUserId();
		String requestUserId = errands.getRequestUser().getUserId();
		
		// ��û�ڰ� �Ϸ��ϱ⸦ ������ ���
		if (requestId != null) {
			// �ɺθ� �Ϸ�ð� ������Ʈ / �ɺθ��ۿ��� ����,�ؽ��±� �߰�
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
			gpaDTO.setRequestManners(0);
			gpaDTO.setUserId(errands.getResponseUser().getUserId());
			errandsService.okRequest(gpaDTO, errands.getResponseUser().getUserId(), evalTag, false);
			
			// �ȵ���̵忡�� �ɺθ��ۿ��� Ǫ���޼��� ������
			String token = androidService.selectTokenById(responseUserId);
			if (token != null) {
				List<String> tokenList = new ArrayList<String>();
				tokenList.add(token);
				Map<String, String> params = new HashMap<>();
				params.put("errandsNum", gpaDTO.getErrandsNum() +"");
				fcmPusher.pushFCMNotification(tokenList, "�ɺθ� �Ϸ� ��û!", requestUserId + "���� �ɺθ� �ϷḦ �������ϴ�!", "CHAT_ACTIVITY", params);
			}

		} 
		// �ɺθ����� �Ϸ��ϱ⸦ ������ ���
		else if (responseId != null) {
			// �ɺθ� �Ϸ�ð� ������Ʈ / ��û�ڿ��� ����,�ؽ��±� �߰�
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, "arrival", null, 0);
			gpaDTO.setResponseAccuracy(0);
			gpaDTO.setResponseKindness(0);
			gpaDTO.setResponseSpeed(0);
			gpaDTO.setUserId(errands.getRequestUser().getUserId());
			errandsService.okRequest(gpaDTO, errands.getRequestUser().getUserId(), evalTag, false);
			
			// �ȵ���̵忡�� ��û�ڿ��� Ǫ���޼��� ������
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
			MemberDTO loginUser = (MemberDTO) aut.getPrincipal();
			
			// �α��� �� ������ �ɺθ����̸� �ٷ� ����Ʈ ����
			if (loginUser.getUserId().equals(upErrands.getResponseUser().getUserId())) {
				loginUser.getPoint().setCurrentPoint(loginUser.getPoint().getCurrentPoint() + totalPrice);
			}
			
			// �ȵ���̵忡�� �������� �ɺθ��� �Ϸ��ٰ� Ǫ���޼��� ������
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
		mv.setViewName("redirect:/errand/detailView?num=" + gpaDTO.getErrandsNum());
		return mv;
	}

	/**
	 * �ɺθ� ����� ����Ʈ�� ���� 
	 * @param sort ���� ����(1 = �ֽż�, 2 = ���� ��������, 3 = ���� ��������)
	 * @param addr �˻� �ּ�
	 * @param page ������
	 * @return
	 */
	@RequestMapping("/listing")
	public ModelAndView listing(Integer sort, String addr, Integer page) {
		ModelAndView mv = new ModelAndView();
		if (page == null) page = 1;
		// 10���� �ɺθ����� �ϳ��� ��� ������
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
