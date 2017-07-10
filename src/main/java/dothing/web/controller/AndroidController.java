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
		
		String result = androidService.androidLogin(email, password);
		System.out.println(result);
		
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("test2", "aa");
		map.put("test3", "bb");
		
		return map;
	}
}
