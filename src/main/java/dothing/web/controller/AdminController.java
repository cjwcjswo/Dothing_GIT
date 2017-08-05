package dothing.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.MemberDTO;
import dothing.web.dto.PointDTO;
import dothing.web.service.AdminMoneyService;
import dothing.web.service.MemberService;
import dothing.web.util.PageMaker;

/**
 * 운영자 페이지 컨트롤러
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	MemberService memberService;

	@Autowired
	AdminMoneyService adminMoneyService;

	/**
	 * 무통장 입금 충전 페이지
	 * @param page 페이지
	 */
	@RequestMapping("/adminMoney")
	public ModelAndView adminMoney(Integer page) {
		if (page == null) page = new Integer(1);
		
		//페이지 계산(5개의 글을 한 페이지로 만듦)
		PageMaker pm = new PageMaker(page, adminMoneyService.countPointList() / 6 + 1);
		pm.start();

		List<PointDTO> list = adminMoneyService.selectAll(page);
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/adminMoney");
		mv.addObject("pm", pm);
		mv.addObject("list", list);

		return mv;
	}

	/**
	 * 유저에게 포인트를 올려주는 컨트롤러
	 * @param userId 포인트를 요청한 유저 아이디
	 */
	@RequestMapping(value = "/pointChange", method = RequestMethod.GET)
	public String pointChange(@RequestParam(value = "userId") String userId) throws Exception {
		
		memberService.insertNotification(userId,
				"요청하신 포인트 " + adminMoneyService.getRequestPoint(userId) + "가 충전이 완료되었습니다.");
		
		adminMoneyService.changePoint(userId);

		return "redirect:/admin/adminMoney";
	}

	/**
	 * 유저가 신청한 포인트를 취소시키는 컨트롤러
	 * @param userId 포인트를 요청한 유저 아이디
	 */
	@RequestMapping(value = "/pointCancel", method = RequestMethod.GET)
	public String pointCancel(@RequestParam(value = "userId") String userId) throws Exception {
		
		memberService.insertNotification(userId,
				"요청하신 포인트 " + adminMoneyService.getRequestPoint(userId) + "가 입금되지 않아 충전되지 않았습니다. 다시 신청해주세요.");
		
		adminMoneyService.pointCancel(userId);

		return "redirect:/admin/adminMoney";
	}
	
	/**
	 * 운영자가 안전심부름꾼 신청자를 확인하는 페이지로 이동하는 컨트롤러
	 * @param page 페이지
	 */
	@RequestMapping("/adminSafe")
	public ModelAndView adminSafe(Integer page) {
		if (page == null) page = 1;
		
		ModelAndView mv = new ModelAndView();
		List<MemberDTO> memberList = memberService.selectNotSafety(page);
		
		// 5개의 신청 목록을 한 페이지로 만듦
		PageMaker pm = new PageMaker(page, memberList.size() / 6 + 1);
		pm.start();
		
		mv.setViewName("/admin/adminSafe");
		mv.addObject("pm", pm);
		mv.addObject("memberList", memberList);
		return mv;
	}
	
	/**
	 * 안전심부름꾼을 DB에 넣어주는 컨트롤러
	 * @param id 안전심부름꾼 승인 시킬 유저 아이디
	 */
	@RequestMapping("/adminSafe/submit")
	public String submitSafe(String id) {
		memberService.insertSafety(id);
		return "redirect:/admin/adminSafe";
	}
	
	/**
	 * 안전심부름꾼 승인을 받아주지 않는 컨트롤러
	 * @param id 승인 취소시킬 유저 아이디
	 */
	@RequestMapping("/adminSafe/cancle")
	public String cancleSafe(String id) {
		memberService.insertNotification(id, "조건은 만족하지 않아 안전심부름꾼 승인이 취소되었습니다");
		memberService.cancleSafety(id);
		return "redirect:/admin/adminSafe";
	}
	
	/**
	 * 유저 관리 페이지에서 유저 리스트를 불러오는 컨트롤러
	 * @param page 페이지
	 * @param id 유저 이름으로 검색 시 keyword
	 */
	@RequestMapping("/adminUserList")
	public ModelAndView adminUserList(Integer page, String id) {
		if (page == null) page = 1;
		List<MemberDTO> memberList = memberService.selectAll(page, id);
		
		// 10명의 유저를 한 페이지로 묶음
		PageMaker pm = new PageMaker(page, memberService.countAll(id) / 11 + 1);
		pm.start();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/adminUserList");
		
		mv.addObject("pm", pm);
		mv.addObject("memberList", memberList);
		
		// 페이지를 이동했을때 검색 했던 keyword를 유지시켜주기 위함
		if (id != null) mv.addObject("sid", id);
		return mv;
	}
	
	/**
	 * 유저를 강제탈퇴 시키는 컨트롤러
	 * @param userId 강제 탈퇴 시킬 유저 아이디
	 */
	@RequestMapping("/delete")
	public String deleteUser(String userId) {
		memberService.deleteUser(userId);
		return "redirect:/admin/adminUserList";
	}
}
