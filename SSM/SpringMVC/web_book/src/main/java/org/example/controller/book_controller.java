package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.po.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class book_controller {
    @ResponseBody//表示返回的是字符串
    @RequestMapping(value = "/demo/{uname}")
//    如果参数类型不是类对象类型，那么返回一个json字符串时要在前端的回调函数里把参数转为json对象
    public List<String> getOrderByName(@PathVariable("uname") String uname) throws JsonProcessingException {
        System.out.println(uname);
        ObjectMapper objectMapper=new ObjectMapper();
        String str = null;
        String str1 = null;
        List<String> list = new ArrayList<String>();
        if(uname.equals("胖橘")){
            Orders order = new Orders();
            order.setOnum("30");
            order.setUnum("001");
            order.setDate("2022");
            str=objectMapper.writeValueAsString(order);
            Orders order1 = new Orders();
            order1.setOnum("33");
            order1.setUnum("002");
            order1.setDate("2022");
            str1=objectMapper.writeValueAsString(order1);
            list.add(str);
            list.add(str1);
            return list;
        }
        else{
            str=objectMapper.writeValueAsString("未查询到订单信息");
            list.add(str);
            System.out.println(list);
            return list;
        }
    }
    @ResponseBody//表示返回的是字符串
    @RequestMapping( "/demo1")
//     如果参数类型是类对象类型，那么返回一个json字符串时在前端的回调函数里直接调用其对象
    public String checkHandler( @RequestBody Orders order) throws JsonProcessingException {
        String str=null;
        ObjectMapper objectMapper=new ObjectMapper();
        if(order.getOnum()!=null&&order.getUnum()!=null&&order.getDate()!=null){
            str=objectMapper.writeValueAsString(order);
            return str;
        }else{
            str=objectMapper.writeValueAsString("请输入订单信息！");
            return str;
        }

    }
}
