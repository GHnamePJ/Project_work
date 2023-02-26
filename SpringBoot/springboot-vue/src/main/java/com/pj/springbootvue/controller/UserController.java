package com.pj.springbootvue.controller;

import com.pj.springbootvue.http.ReturnCode;
import com.pj.springbootvue.pojo.User;
import com.pj.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-14 17:19
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;
    //查询角色
    @RequestMapping("/userInfo")
    public ReturnCode UserInfo(@RequestBody User user){
        List<User> users = userService.FindAll();
        System.out.println(users);
        return new ReturnCode(1,"成功",users);
    }
    @RequestMapping("/tea")
    public String Tea(){
        return "收到，请查收你的大杯幽幽古道珍珠";
    }
    //修改头像
    @RequestMapping("/photo")
    public ReturnCode Photo(@RequestBody User user){
        //前端base64格式图片信息
//        System.out.println(user.getPhoto());
        return new ReturnCode(1,"保存成功");
    }
    //修改密码
    @RequestMapping("/change_password")
    public ReturnCode ChangePassword(@RequestBody User user){
        System.out.println(user.getPassword());
        System.out.println(user.getNewpassword());
        return new ReturnCode(1,"修改成功");
    }
}


