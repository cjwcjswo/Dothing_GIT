package dothing.web.android.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.service.AndroidService;
import dothing.web.service.ErrandsService;
import dothing.web.util.FcmPusher;

@Controller
@RequestMapping("/androidErrand")
public class AndroidErrandsController {
	@Autowired
	FcmPusher fcmPusher;
	@Autowired
	AndroidService androidService;
	@Autowired
	ErrandsService errandsService;
	
	@RequestMapping("/errandSearch")
	@ResponseBody
	public List<ErrandsDTO> errandSearch(HttpServletRequest request){
		String lat = (String)request.getParameter("lat");
		String lng = (String)request.getParameter("lng");
		System.out.println(lat + " : " + lng);
		List<ErrandsDTO> list = errandsService.searchErrands(null, null, null, 3, lat, lng);
		System.out.println(list.size() +"개 검색됨");
		return list;
	}
	
	@RequestMapping("/insertErrand")
	@ResponseBody
	public Integer uploadImage(HttpSession session, ErrandsDTO dto) throws Exception{
		int result = 0;
		System.out.println(dto);
		MultipartFile file = dto.getErrandsPhotoFile();
		if(file != null){
			dto.setErrandsPhoto(file.getOriginalFilename());
			result = errandsService.insertErrands(dto);
			String path = session.getServletContext().getRealPath("") + "\\errands\\" + errandsService.selectNum();
			File folder = new File(path);
			folder.mkdirs();
			file.transferTo(new File(path + "\\" + dto.getErrandsPhoto()));
		}else{
			dto.setErrandsPhoto("EMPTY");
			result = errandsService.insertErrands(dto);
		}
		if(result > 0){
			ErrandsPosDTO posDTO = dto.getErrandsPos();
			List<String> userTokenList = androidService.selectTokenByDistance(posDTO.getLatitude(), posDTO.getLongitude(), 5);
			fcmPusher.pushFCMNotification(userTokenList, "두띵", "주변에 새심부름이 등록됬습니다!: " + dto.getTitle());
		}
		return result;
	}
}
