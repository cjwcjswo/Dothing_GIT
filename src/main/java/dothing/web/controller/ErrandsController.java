package dothing.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.ErrandsDTO;
import dothing.web.service.ErrandsService;

@Controller
@RequestMapping("/errand")
public class ErrandsController {
	@Autowired
	ErrandsService errandsService;
	
	@RequestMapping("/errand")
	public ModelAndView errandsList(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.selectAll());
		mv.setViewName("/errand/errand");
		return mv;
	}
	
	@RequestMapping("/detailView")
	public ModelAndView detail(int num){
		ModelAndView mv = new ModelAndView();
		mv.addObject("errands", errandsService.selectErrands(num));
		mv.setViewName("/errand/detailView");
		return mv;
	}
	
	@RequestMapping("/insert")
	public String insert(ErrandsDTO dto, String preAddress, String detailAddress){
		dto.getErrandsPos().setAddr(preAddress + " " + detailAddress);
		System.out.println(dto);
		return "/errand/errand";
	}
}
