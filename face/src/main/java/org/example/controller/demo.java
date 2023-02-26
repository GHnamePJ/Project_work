package org.example.controller;

import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class demo {
    //依赖注入
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //处理登录
//    @RequestMapping( "/demo")
//    @ResponseBody
//    public Results register() {
//        return new Results(true,"1");
//
//    }
}