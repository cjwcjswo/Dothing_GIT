package dothing.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.PointDTO;
import dothing.web.service.ChatService;
import dothing.web.service.ErrandsService;
import dothing.web.service.MemberService;
import dothing.web.util.PageMaker;

@Controller
@RequestMapping("/errand")
public class ErrandsController {
	@Autowired
	ErrandsService errandsService;

	@Autowired
	MemberService memberService;

	
	@Autowired
	ChatService chatService;

	@RequestMapping("/errand")
	public ModelAndView errandsList(Authentication aut, Integer page) {
		MemberDTO member = (MemberDTO)aut.getPrincipal();
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.selectAll()); // �ɺθ� ����Ʈ
		mv.addObject("rankedList", memberService.selectRanked()); // �ɺθ��� ��ŷ
		mv.addObject("moneyList", errandsService.moneyErrands()); // ���Ǵ� �ɺθ�
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
		if(errands.getResponseUser() != null){
			String responseId = errands.getResponseUser().getUserId();
			String responseSelfImg = memberService.selectMemberById(responseId).getSelfImg();
			mv.addObject("responseSelfImg", responseSelfImg);
		}
		String requestId = errands.getRequestUser().getUserId();
		
		
		String requestSelfImg = memberService.selectMemberById(requestId).getSelfImg();
		
		
		mv.addObject("requestSelfImg", requestSelfImg);
		
		List<String> list = chatService.getContent(num+"");
		if(list != null){
			mv.addObject("list", chatService.getContent(num+""));
		}
		mv.setViewName("/errand/detailView");
		return mv;
	}

	@RequestMapping("/register")
	public String register() {

		return "/errand/register";
	}

	@RequestMapping("/insert")
	public ModelAndView insert(HttpSession session, ErrandsDTO dto, @RequestParam("preAddress") String preAddress,
			String detailAddress) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		dto.setEndTime(dto.getEndTime().replaceAll("T", " "));
		dto.getErrandsPos().setAddr(preAddress + " " + detailAddress);
		MultipartFile file = dto.getErrandsPhotoFile();
		dto.setErrandsPhoto(file.getOriginalFilename());
		int insertResult = errandsService.insertErrands(dto, session.getServletContext().getRealPath(""));
		if (insertResult > 0) {
			
			mv.addObject("insertNum", errandsService.selectNum());
			mv.addObject("insertResult", insertResult);
		}
		if (dto.getErrandsPhoto() != null && !dto.getErrandsPhoto().trim().equals("")) {
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		}
		mv.setViewName("/errand/empty");
		return mv;
	}

	@RequestMapping("/insertReply")
	public String insert(HttpSession session, ErrandsReplyDTO dto) {
		dto.setArrivalTime(dto.getArrivalTime().replaceAll("T", " "));
		ErrandsDTO errand = errandsService.selectErrands(dto.getErrands().getErrandsNum());
		memberService.insertNotification(errand.getRequestUser().getUserId(), errand.getErrandsNum() + "�� �ۿ� " + dto.getUser().getUserId() + "���� ����� �޾ҽ��ϴ�!");
		errandsService.insertReply(dto);
		return "redirect:/errand/detailView?num=" + dto.getErrands().getErrandsNum();
	}

	/**
	 * �ɺθ� ����
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
	 * ���� ����
	 */
	@RequestMapping("/deleteReply")
	public String deleteReply(int num, int eNum) {
		errandsService.deleteReply(num);
		return "redirect:/errand/detailView?num=" + eNum;
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("minPrice") Integer minPrice, @RequestParam("maxPrice") Integer maxPrice,
			@RequestParam("hash") String hash, Integer distance, String sLat, String sLng) {
		System.out.println(
				"�ּ�: " + minPrice + " �ִ�: " + maxPrice + " �ؽ�: " + hash + " " + distance + " " + sLat + " " + sLng);
		if (distance == 0)
			distance = null;
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.searchErrands(hash, minPrice, maxPrice, distance, sLat, sLng));
		mv.setViewName("/errand/errand");
		return mv;
	}

	@RequestMapping("/hash")
	public ModelAndView requestHash(String hash) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hashList", errandsService.requestHash(hash));
		mv.setViewName("jsonView");
		return mv;
	}

	/**
	 * �ɺθ� ��û���� Ȯ��
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
	 * �ɺθ� ���೻�� Ȯ��
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
		mv.setViewName("/errand/myResponse");
		return mv;
	}
	/**
	 * �ɺθ� ���
	 */
	@RequestMapping("/cancle")
	public ModelAndView cancleErrands(Authentication aut, int num, int point){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/errand/myRequest");
		MemberDTO currentMember = (MemberDTO)aut.getPrincipal();
		errandsService.cancleErrands(num, point, currentMember.getUserId());
		PointDTO currentPoint = currentMember.getPoint();
		currentPoint.setCurrentPoint(currentPoint.getCurrentPoint() + point);
		return mv;
	}
	/**
	 * �ɺθ� ���� ���μ���(�ɺθ��� �������� ��)
	 */
	@RequestMapping("/startErrand")
	public ModelAndView startErrand(Authentication aut, int num, String responseId) throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberDTO requestUser = (MemberDTO) aut.getPrincipal();
		ErrandsDTO currentErrand = errandsService.selectErrands(num);
		int totalPrice = currentErrand.getProductPrice() + currentErrand.getErrandsPrice();
		if (totalPrice > requestUser.getPoint().getCurrentPoint()) {
			throw new Exception("����Ʈ�� �����մϴ�! �������ּ���.");
		}
		errandsService.updateErrands(num, responseId, requestUser.getUserId(), "startTime", null, null, -totalPrice);
		requestUser.getPoint().setCurrentPoint((requestUser.getPoint().getCurrentPoint()) - totalPrice);
		mv.addObject("num", num);
		mv.addObject("responseId", responseId);
		mv.setViewName("/errand/okay");
		return mv;
	}

	/**
	 * �ɺθ� Ȯ�� ���μ���
	 */
	@RequestMapping("/confirmErrand")
	public ModelAndView confirmErrand(Authentication aut, String requestId, String responseId, GPADTO gpaDTO,
			String evalTag) {
		ModelAndView mv = new ModelAndView();
		ErrandsDTO errands = errandsService.selectErrands(gpaDTO.getErrandsNum()); // �ش� �ɺθ� �ҷ�����

		if (requestId != null) { // ��û�ڰ� Ȯ���� ���
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
			gpaDTO.setRequestManners(0);
			gpaDTO.setUserId(errands.getResponseUser().getUserId());
			errandsService.okRequest(gpaDTO, errands.getResponseUser().getUserId(), evalTag);
			
		} else if (responseId != null) { // �ɺθ����� Ȯ���� ���
			errandsService.updateErrands(gpaDTO.getErrandsNum(), null, null, null, "arrival", null, 0);
			gpaDTO.setResponseAccuracy(0);
			gpaDTO.setResponseKindness(0);
			gpaDTO.setResponseSpeed(0);
			gpaDTO.setUserId(errands.getRequestUser().getUserId());
			errandsService.okRequest(gpaDTO, errands.getRequestUser().getUserId(), evalTag);
		}
		
		ErrandsDTO upErrands = errandsService.selectErrands(gpaDTO.getErrandsNum()); // �ش� �ɺθ� �ҷ�����
		// �ɺθ��۰� ��û�ڰ� ��� Ȯ������ ���
		if (upErrands.getArrivalTime() != null && upErrands.getFinishTime() != null) {
			// ����Ʈ �����̶�
			int totalPrice = upErrands.getErrandsPrice() + upErrands.getProductPrice();
			System.out.println(totalPrice + " ��Ż�����̽� ");
			memberService.updatePoint(totalPrice, upErrands.getResponseUser().getUserId());
			MemberDTO loginUser = (MemberDTO)aut.getPrincipal();
			if(loginUser.getUserId().equals(upErrands.getResponseUser().getUserId())){
				loginUser.getPoint().setCurrentPoint(loginUser.getPoint().getCurrentPoint() + totalPrice);
			}

		}
		mv.setViewName("redirect:/errand/detailView?num=" + gpaDTO.getErrandsNum());
		return mv;
	}
}
