package com.pj.springbootmybatis.controller;

import com.pj.springbootmybatis.Service.UserService;
import com.pj.springbootmybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-12 23:00
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/userInfo")
    public List<User> UserInfo(){
        List<User> users = userService.FindAll();
        System.out.println(users);
        return users;
    }
}
