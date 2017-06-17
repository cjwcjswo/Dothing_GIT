package dothing.web.controller;

import java.io.File;
import java.io.IOException;

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
import dothing.web.dto.MemberDTO;
import dothing.web.service.ErrandsService;
import dothing.web.util.PageMaker;

@Controller
@RequestMapping("/errand")
public class ErrandsController {
	@Autowired
	ErrandsService errandsService;

	@RequestMapping("/errand")
	public ModelAndView errandsList(Integer page) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.selectAll());
		mv.setViewName("/errand/errand");
		return mv;
	}

	@RequestMapping("/detailView")
	public ModelAndView detail(int num, Authentication aut) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentId", ((MemberDTO) aut.getPrincipal()).getUserId());
		mv.addObject("errands", errandsService.selectErrands(num));
		mv.setViewName("/errand/detailView");
		return mv;
	}

	@RequestMapping("/register")
	public String register() {

		return "/errand/register";
	}

	@RequestMapping("/insert")
	public String insert(HttpSession session, ErrandsDTO dto, @RequestParam("preAddress") String preAddress,
			String detailAddress) throws IllegalStateException, IOException {
		dto.setEndTime(dto.getEndTime().replaceAll("T", " "));
		dto.getErrandsPos().setAddr(preAddress + " " + detailAddress);
		MultipartFile file = dto.getErrandsPhotoFile();
		dto.setErrandsPhoto(file.getOriginalFilename());
		int insertResult = errandsService.insertErrands(dto, session.getServletContext().getRealPath(""));
		if (insertResult > 0) {
			session.setAttribute("insertResult", insertResult);
		}
		if (dto.getErrandsPhoto() != null && !dto.getErrandsPhoto().trim().equals("")) {
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		}
		return "redirect:/errand/errand";
	}

	@RequestMapping("/insertReply")
	public String insert(HttpSession session, ErrandsReplyDTO dto) {
		dto.setArrivalTime(dto.getArrivalTime().replaceAll("T", " "));
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
	public ModelAndView search(@RequestParam("minPrice") Integer minPrice, @RequestParam("maxPrice") Integer maxPrice,
			@RequestParam("hash") String hash, Integer distance, String sLat, String sLng) {
		System.out.println(
				"최소: " + minPrice + " 최대: " + maxPrice + " 해쉬: " + hash + " " + distance + " " + sLat + " " + sLng);
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
	 * 심부름 요청내역 확인
	 */
	@RequestMapping("/myRequest")
	public ModelAndView myRequest(Authentication aut, Integer page) {
		if (page == null)
			page = new Integer(1);
		PageMaker pm = new PageMaker(page, errandsService.countMyRequest()/ 6 + 1);
		pm.start();
		ModelAndView mv = new ModelAndView();
		mv.addObject("pm", pm);
		mv.addObject("errandsList", errandsService.myErrandsRequest(((MemberDTO) aut.getPrincipal()).getUserId(), page));
		mv.setViewName("/errand/myRequest");
		return mv;
	}

	/**
	 * 심부름 수행내역 확인
	 */
	@RequestMapping("/myResponse")
	public ModelAndView myResponse(Authentication aut, Integer page) {
		if (page == null)
			page = new Integer(1);
		PageMaker pm = new PageMaker(page, errandsService.countMyResponse() / 6 + 1);
		pm.start();
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.myErrandsResponse(((MemberDTO) aut.getPrincipal()).getUserId(), page));
		mv.setViewName("/errand/myResponse");
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
		errandsService.updateErrands(num, responseId, requestUser.getUserId(), "startTime", null, null, totalPrice);
		mv.addObject("num", num);
		mv.setViewName("/errand/okay");
		return mv;
	}

}
