package dothing.web.controller;

import java.io.File;
import java.io.IOException;
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
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.service.ErrandsService;

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
	public ModelAndView detail(int num) {
		ModelAndView mv = new ModelAndView();
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
		if(insertResult > 0){
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

	@RequestMapping("/deleteErrands")
	public String deleteErrands(Authentication auth, int num, String id) throws Exception {
		if (!id.equals(((MemberDTO) auth.getPrincipal()).getUserId())) {
			throw new Exception("작성자가 아닙니다");
		}
		errandsService.deleteErrands(num);
		return "redirect:/errand/errand";
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("minPrice") Integer minPrice, @RequestParam("maxPrice") Integer maxPrice,
			@RequestParam("hash") String hash) {
		System.out.println("최소: " + minPrice + " 최대: " + maxPrice + " 해쉬: " + hash);
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.searchErrands(hash, minPrice, maxPrice));
		mv.setViewName("/errand/errand");
		return mv;
	}
	
	@RequestMapping("/hash")
	public ModelAndView requestHash(String hash){
		ModelAndView mv = new ModelAndView();
		mv.addObject("hashList", errandsService.requestHash(hash));
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping("/myRequest")
	public ModelAndView myRequest(Authentication aut){
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList",errandsService.myErrandsRequest(((MemberDTO)aut.getPrincipal()).getUserId()));
		mv.setViewName("/errand/myRequest");
		return mv;
	}
	
	@RequestMapping("/myResponse")
	public ModelAndView myResponse(Authentication aut){
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList",errandsService.myErrandsResponse(((MemberDTO)aut.getPrincipal()).getUserId()));
		mv.setViewName("/errand/myResponse");
		return mv;
	}
}
