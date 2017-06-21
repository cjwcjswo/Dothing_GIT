package dothing.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.PointDTO;
import dothing.web.service.AdminMoneyService;
import dothing.web.service.MemberService;
import dothing.web.util.PageMaker;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	AdminMoneyService adminMoneyService;
	
	@RequestMapping("/adminMoney")
	public ModelAndView adminMoney(Integer page){
		
		if (page == null)
			page = new Integer(1);
		PageMaker pm = new PageMaker(page, adminMoneyService.countPointList()/ 6 + 1);
		pm.start();
				
		List<PointDTO> list = adminMoneyService.selectAll(page);
		for(PointDTO dto : list){
			System.out.println("dto: " + dto);
		}
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("admin/adminMoney");
		mv.addObject("pm", pm);
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping(value="/pointChange", method=RequestMethod.GET)
	public String pointChange(@RequestParam(value="userId")String userId) throws Exception{
		
		adminMoneyService.changePoint(userId);

		return "redirect:/admin/adminMoney";
	}
	
	@RequestMapping(value="/pointCancel", method=RequestMethod.GET)
	public String pointCancel(@RequestParam(value="userId")String userId) throws Exception{
		
		adminMoneyService.pointCancel(userId);

		return "redirect:/admin/adminMoney";
	}
	
	
	
	@RequestMapping("/adminSafe")
	public ModelAndView adminSafe(Integer page){
		if(page == null) page = 1;
		ModelAndView mv = new ModelAndView();
		List<MemberDTO> memberList = new ArrayList<>();
		PageMaker pm = new PageMaker(page, memberList.size() / 6 + 1);
		pm.start();
		mv.setViewName("/admin/adminSafe");
		mv.addObject("pm", pm);
		mv.addObject("memberList", memberList);
		return mv;
	}
	
	@RequestMapping("/adminSafe/submit")
	public String submitSafe(String id){
		memberService.insertSafety(id);
		return "redirect:/admin/adminSafe";
	}
	@RequestMapping("/adminUserList")
	public ModelAndView adminUserList(Integer page, String id){
		if(page == null) page = 1;
		List<MemberDTO> memberList = memberService.selectAll(page, id);
		PageMaker pm = new PageMaker(page, memberService.countAll(id) / 11 + 1);
		pm.start();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/adminUserList");
		mv.addObject("pm", pm);
		mv.addObject("memberList", memberList);
		if(id != null){
			mv.addObject("sid", id);
		}
		return mv;
	}
	
	@RequestMapping("/delete")
	public String deleteUser(String userId){
		memberService.deleteUser(userId);
		return "redirect:/admin/adminUserList";
	}
}
