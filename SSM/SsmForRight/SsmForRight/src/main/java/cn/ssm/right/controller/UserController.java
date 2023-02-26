package cn.ssm.right.controller;

import cn.ssm.right.http.Results;
import cn.ssm.right.po.User;
import cn.ssm.right.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@SessionAttributes(value={"validate_code", "user"})
@RequestMapping("/user")
public class UserController {
    //依赖注入
    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public Results loginCheck(
            @RequestBody User user,
                              @RequestParam("verify_code") String verifyCode,
                              @ModelAttribute("validate_code") StringBuffer verifyCodeOrg,
                              Model model){
        System.out.println(verifyCode);
        System.out.println(verifyCodeOrg.toString());
        //调用service方法，验证用户合法性
//        int rs = userService.checkUser(user);
        Results results = null;
//        //当用户合法，且验证码相同时，登录成功
        if(verifyCode.equalsIgnoreCase(verifyCodeOrg.toString())){

            model.addAttribute("user", user);
            results = Results.success("success", user);
        } else {
            results = Results.fail();
        }
        return results;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public Results registerUser(User user,
                                @RequestParam("file") CommonsMultipartFile file,
                                HttpServletRequest request) {
        System.out.println("register user:" + user);
        System.out.println("fileName："+file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("")+"\\"+ fileName + new Date().getTime();
        System.out.println("path:" + path);
        //创建File对象
        File newFile=new File(path);
        try{
            //通过CommonsMultipartFile的方法直接写文件
            file.transferTo(newFile);
            return Results.success();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return Results.fail();
        }
    }
}
