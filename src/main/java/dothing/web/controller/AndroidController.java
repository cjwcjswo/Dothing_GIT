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
		System.out.println("asdjhasd");
		String result="";
		if(email != null && password != null)
			result = androidService.androidLogin(email, password);
		
		System.out.println(result);
		
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("result", result);
		
		return map;
	}
	
<<<<<<< HEAD
	@RequestMapping("/signIn")
	@ResponseBody
	public void signIn(HttpServletRequest request){
		System.out.println("회원가입");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		System.out.println(email);
=======
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, String> test(HttpServletRequest request){
		System.out.println("안드로이드에서 접근함");
		System.out.println((String)request.getParameter("title"));
		System.out.println((String)request.getParameter("memo"));
		Map<String, String> result = new HashMap<>();
		
		result.put("A", "호우");
		result.put("B", "호잇");
		result.put("C", "헤에");
		return result;
	}
	
	@RequestMapping("/errand")
	@ResponseBody
	public List<ErrandsDTO> errand(){
		System.out.println("접근함");
		return errandsService.selectAll();
	}
	
	@RequestMapping("/errandSearch")
	@ResponseBody
	public List<ErrandsDTO> errandSearch(HttpServletRequest request){
		String lat = (String)request.getParameter("lat");
		String lng = (String)request.getParameter("lng");
		System.out.println(lat + " : " + lng);
		List<ErrandsDTO> list = errandsService.searchErrands(null, null, null, 3, lat, lng);
		System.out.println(list.size() +"개 검색됨");
		return list;
>>>>>>> a856d5e3098421fb341078e7a72bb5e5b571fe33
	}
}
