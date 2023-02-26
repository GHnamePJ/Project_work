package cn.ssm.right.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String toLogin(){
        return "/login";
    }

    @RequestMapping("/role")
    public String toRole(){
        return "/role";
    }
}
