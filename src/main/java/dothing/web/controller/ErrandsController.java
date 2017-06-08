package dothing.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.service.ErrandsService;

@Controller
@RequestMapping("/errand")
public class ErrandsController {
	@Autowired
	ErrandsService errandsService;
	
	@RequestMapping("/errands")
	public ModelAndView errandsList(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("errandsList", errandsService.selectAll());
		System.out.println(errandsService.selectAll());
		mv.setViewName("/errand/errands");
		return mv;
	}
}
