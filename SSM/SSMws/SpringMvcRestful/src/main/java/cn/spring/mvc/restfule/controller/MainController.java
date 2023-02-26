package cn.spring.mvc.restfule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//项目入口
@Controller
public class MainController {
	@RequestMapping("/")
	public String getIndex(){	
		return "login";  //视图
	}
}
