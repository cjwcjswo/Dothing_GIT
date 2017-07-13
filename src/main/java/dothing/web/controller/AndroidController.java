package dothing.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dothing.web.dto.ErrandsDTO;
import dothing.web.service.AndroidService;
import dothing.web.service.ErrandsService;

@Controller
@RequestMapping("/android")
public class AndroidController {
	@Autowired
	ErrandsService errandsService;
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
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, String> test(HttpServletRequest request){
		System.out.println("�ȵ���̵忡�� ������");
		System.out.println((String)request.getParameter("title"));
		System.out.println((String)request.getParameter("memo"));
		Map<String, String> result = new HashMap<>();
		
		result.put("A", "ȣ��");
		result.put("B", "ȣ��");
		result.put("C", "�쿡");
		return result;
	}
	
	@RequestMapping("/errand")
	@ResponseBody
	public List<ErrandsDTO> errand(){
		System.out.println("������");
		return errandsService.selectAll();
	}
}
