package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.po.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    //@ResponseBody作用是返回结果是字符串，不再是JSP页面
    @ResponseBody//表示返回的是一个主体
    @RequestMapping("/check")
//    返回JSON对象字符串
//@RequestBody
    public String checkHandler( @RequestBody UserInfo userInfo) throws JsonProcessingException {
        System.out.print(userInfo);
        if ("pj".equals(userInfo.getRealName())
                && "123456".equals(userInfo.getPassword())) {
//            model.addAttribute("realName", userInfo.getRealName());
            //JAVA实体类转为JSON
            ObjectMapper objMapper = new ObjectMapper();
//            String user_json_str = objMapper.writeValueAsString(userInfo);//JSON对象字符串
            String str = objMapper.writeValueAsString(new String("验证成功"));
            System.out.print(userInfo.getRealName()+userInfo.getPassword()+str);
            return str;
        } else {
            ObjectMapper objMapper = new ObjectMapper();
//            String error_str = objMapper.writeValueAsString(userInfo);//JSON对象字符串
            String str = objMapper.writeValueAsString(new String("验证失败"));
            System.out.print(userInfo.getRealName()+userInfo.getPassword()+str);
            return str;
        }
    }
}
