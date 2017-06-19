package dothing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@RequestMapping("/adminMoney")
	public void adminMoney(){}
	
	@RequestMapping("/adminSafe")
	public void adminSafe(){}
	
	@RequestMapping("/adminUserList")
	public void adminUserList(){}
}
