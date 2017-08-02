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
 * ��� ������ ��Ʈ�ѷ�
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	MemberService memberService;

	@Autowired
	AdminMoneyService adminMoneyService;

	/**
	 * ������ �Ա� ���� ������
	 * @param page ������
	 */
	@RequestMapping("/adminMoney")
	public ModelAndView adminMoney(Integer page) {
		if (page == null) page = new Integer(1);
		
		//������ ���(5���� ���� �� �������� ����)
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
	 * �������� ����Ʈ�� �÷��ִ� ��Ʈ�ѷ�
	 * @param userId ����Ʈ�� ��û�� ���� ���̵�
	 */
	@RequestMapping(value = "/pointChange", method = RequestMethod.GET)
	public String pointChange(@RequestParam(value = "userId") String userId) throws Exception {
		
		memberService.insertNotification(userId,
				"��û�Ͻ� ����Ʈ " + adminMoneyService.getRequestPoint(userId) + "�� ������ �Ϸ�Ǿ����ϴ�.");
		
		adminMoneyService.changePoint(userId);

		return "redirect:/admin/adminMoney";
	}

	/**
	 * ������ ��û�� ����Ʈ�� ��ҽ�Ű�� ��Ʈ�ѷ�
	 * @param userId ����Ʈ�� ��û�� ���� ���̵�
	 */
	@RequestMapping(value = "/pointCancel", method = RequestMethod.GET)
	public String pointCancel(@RequestParam(value = "userId") String userId) throws Exception {
		
		memberService.insertNotification(userId,
				"��û�Ͻ� ����Ʈ " + adminMoneyService.getRequestPoint(userId) + "�� �Աݵ��� �ʾ� �������� �ʾҽ��ϴ�. �ٽ� ��û���ּ���.");
		
		adminMoneyService.pointCancel(userId);

		return "redirect:/admin/adminMoney";
	}
	
	/**
	 * ��ڰ� �����ɺθ��� ��û�ڸ� Ȯ���ϴ� �������� �̵��ϴ� ��Ʈ�ѷ�
	 * @param page ������
	 */
	@RequestMapping("/adminSafe")
	public ModelAndView adminSafe(Integer page) {
		if (page == null) page = 1;
		
		ModelAndView mv = new ModelAndView();
		List<MemberDTO> memberList = memberService.selectNotSafety(page);
		
		// 5���� ��û ����� �� �������� ����
		PageMaker pm = new PageMaker(page, memberList.size() / 6 + 1);
		pm.start();
		
		mv.setViewName("/admin/adminSafe");
		mv.addObject("pm", pm);
		mv.addObject("memberList", memberList);
		return mv;
	}
	
	/**
	 * �����ɺθ����� DB�� �־��ִ� ��Ʈ�ѷ�
	 * @param id �����ɺθ��� ���� ��ų ���� ���̵�
	 */
	@RequestMapping("/adminSafe/submit")
	public String submitSafe(String id) {
		memberService.insertSafety(id);
		return "redirect:/admin/adminSafe";
	}
	
	/**
	 * �����ɺθ��� ������ �޾����� �ʴ� ��Ʈ�ѷ�
	 * @param id ���� ��ҽ�ų ���� ���̵�
	 */
	@RequestMapping("/adminSafe/cancle")
	public String cancleSafe(String id) {
		memberService.insertNotification(id, "������ �������� �ʾ� �����ɺθ��� ������ ��ҵǾ����ϴ�");
		memberService.cancleSafety(id);
		return "redirect:/admin/adminSafe";
	}
	
	/**
	 * ���� ���� ���������� ���� ����Ʈ�� �ҷ����� ��Ʈ�ѷ�
	 * @param page ������
	 * @param id ���� �̸����� �˻� �� keyword
	 */
	@RequestMapping("/adminUserList")
	public ModelAndView adminUserList(Integer page, String id) {
		if (page == null) page = 1;
		List<MemberDTO> memberList = memberService.selectAll(page, id);
		
		// 10���� ������ �� �������� ����
		PageMaker pm = new PageMaker(page, memberService.countAll(id) / 11 + 1);
		pm.start();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/adminUserList");
		
		mv.addObject("pm", pm);
		mv.addObject("memberList", memberList);
		
		// �������� �̵������� �˻� �ߴ� keyword�� ���������ֱ� ����
		if (id != null) mv.addObject("sid", id);
		return mv;
	}
	
	/**
	 * ������ ����Ż�� ��Ű�� ��Ʈ�ѷ�
	 * @param userId ���� Ż�� ��ų ���� ���̵�
	 */
	@RequestMapping("/delete")
	public String deleteUser(String userId) {
		memberService.deleteUser(userId);
		return "redirect:/admin/adminUserList";
	}
}
