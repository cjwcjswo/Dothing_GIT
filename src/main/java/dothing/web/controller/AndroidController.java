package dothing.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dothing.web.service.AndroidService;

@Controller
@RequestMapping("/android")
public class AndroidController {
	
	@Autowired
	AndroidService androidService;
	
	@RequestMapping("/checkId")
	@ResponseBody
	public Map<String,String> android(HttpServletRequest request){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("asdjhasd");
		String result="";
		if(email != null && password != null)
			result = androidService.androidLogin(email, password);
		
		System.out.println(result);
		
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("result", result);
		
		return map;
	}
	
	@RequestMapping("/signIn")
	@ResponseBody
	public void signIn(HttpServletRequest request){
		System.out.println("회원가입");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		System.out.println(email);
	}
}
