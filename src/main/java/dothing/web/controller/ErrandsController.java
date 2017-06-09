package dothing.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.ErrandsDTO;
import dothing.web.service.ErrandsService;

@Controller
@RequestMapping("/errand")
public class ErrandsController {
	@Autowired
	ErrandsService errandsService;

	@RequestMapping("/errand")
	public ModelAndView errandsList() {
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
	public String register(HttpSession session) {
		session.setAttribute("userIdS", "tester");
		return "/errand/register";
	}

	@RequestMapping("/insert")
	public String insert(HttpSession session, ErrandsDTO dto, String preAddress, String detailAddress)
			throws IllegalStateException, IOException {
		dto.setEndTime(dto.getEndTime().replaceAll("T"," "));
		dto.getErrandsPos().setAddr(preAddress + " " + detailAddress);
		MultipartFile file = dto.getErrandsPhotoFile();
		dto.setErrandsPhoto(file.getOriginalFilename());
		errandsService.insertErrands(dto);
		if (dto.getErrandsPhoto() != null && !dto.getErrandsPhoto().trim().equals("")) {
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		}
		return "redirect:/errand/errand";
	}
}
