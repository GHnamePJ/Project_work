package cn.spring.mvc.restfule.controller;

import cn.spring.mvc.restfule.po.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    //@RequestBody注解，使得后端可以接收前端请求JSON字符串，转为实体类对象
    //@ResponseBody注解，使得返回只有JSON字符串，不再返回JSP页面
    @RequestMapping(value="/getList")
    @ResponseBody
    public String loginCheck(@RequestBody UserInfo userInfo) throws JsonProcessingException {
        UserInfo u = new UserInfo();
        u = userInfo;
        System.out.println(u.getLoginName());
        String rs = "";
        if("lisi".equals(u.getLoginName())) {
            rs = "login success";
        }else {
            rs = "login fail";
        }
        //返回普通字符串
        return rs;
    }

    @RequestMapping("/getList/{loginName}")
    @ResponseBody
    public String getUserByUserName(@PathVariable("loginName") String userName) throws JsonProcessingException {
    //public UserInfo getUserByUserName(@PathVariable("loginName") String userName) throws JsonProcessingException {

        UserInfo u = new UserInfo();
        u.setLoginName(userName);
        System.out.println(userName+" " +u.getLoginName());
        u.setId(1);
        u.setPassword("123");
        u.setRealName("lisi");
        ObjectMapper oMapper = new ObjectMapper();
        String jsonStr_user = oMapper.writeValueAsString(u);
        //返回JSON字符串
        return jsonStr_user;
        //return u;
    }
}
