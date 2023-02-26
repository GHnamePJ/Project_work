package org.example.filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.http.ResponseData;
import org.example.pojo.User;
import org.example.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;


public class TokenInterceptor implements HandlerInterceptor{

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView model) throws Exception {
    }
    //拦截每个请求
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");//保存的用户信息的token
        String phone = request.getHeader("phone");//前端用户输入的用户的登录信息
        System.out.println("获取token");
        System.out.println(token);
        System.out.println("获取phone");
        System.out.println(phone);
        ResponseData responseData = ResponseData.ok();
        //token不存在
        if(token!= null) {
            User user = JwtUtils.unsign(token, User.class);//解密
            if(user != null && phone !=null) {
                    return true;
                }
            else{
                System.out.println("用户，手机号为空");
                responseData = ResponseData.forbidden();
                responseMessage(response, response.getWriter(), responseData);
                return false;
            }
        }
        else {
            System.out.println("token为空");
            responseData = ResponseData.forbidden();
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }

    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
        responseData = ResponseData.forbidden();
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }
}