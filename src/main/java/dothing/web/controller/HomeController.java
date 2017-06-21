package dothing.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.CrawlDataDTO;
import dothing.web.service.CrawlService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private CrawlService crawlService;
	
	@RequestMapping("/")
	public String home(HttpSession session) {
		return "main/home";
	}

	@RequestMapping("/error")
	public ModelAndView error(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", request.getAttribute("errorMessage"));
		mv.setViewName("/error/errorMessage");
		return mv;
	}

	
	@RequestMapping("/errand/chat")
	public ModelAndView chat(String errandsNum, HttpSession session){
		session.setAttribute("errandsNum", errandsNum);
		return new ModelAndView("/errand/chat", "errandsNum", errandsNum);
	}
	
	@RequestMapping("/crawl")
	public ModelAndView crawl(String pageName, String productName){
		Map<String, List<CrawlDataDTO>> map = crawlService.parseMainPage(pageName, productName);
		System.out.println("list's size : "+crawlService.parseMainPage(pageName, productName).get(pageName).size());
		return new ModelAndView("crawl/crawlPage", "crawlData", crawlService.parseMainPage(pageName, productName).get(pageName));
	}
	
	


}
