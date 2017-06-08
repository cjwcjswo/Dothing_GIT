package dothing.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ErrandsController {

	@RequestMapping("/tiles/header/errand")
	public String errandApply(){
		return "/errand/errand" ;
	}
	
}
