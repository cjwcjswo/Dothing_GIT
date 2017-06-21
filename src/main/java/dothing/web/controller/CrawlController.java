package dothing.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.service.CrawlService;

@Controller
@RequestMapping("/crawl")
public class CrawlController {

	@Autowired
	private CrawlService crawlService;
	/*
	@RequestMapping("/")
	public String url(){
		System.out.println("url");
		return "crawl/crawlPage";
	}
	
	@RequestMapping("/")
	public ModelAndView crawl(String pageName){
		System.out.println("crawl controller!");
		Map<String, List<String>> map = crawlService.parseMainPage(pageName);
		return new ModelAndView("crawl/crawlPage", "crawlData", map.get(pageName));
	}
	*/
}
