package dothing.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.MemberDTO;
import dothing.web.service.MemberService;
import dothing.web.util.PageMaker;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	MemberService memberService;
	@RequestMapping("/adminMoney")
	public void adminMoney(){}
	
	@RequestMapping("/adminSafe")
	public void adminSafe(){}
	
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
