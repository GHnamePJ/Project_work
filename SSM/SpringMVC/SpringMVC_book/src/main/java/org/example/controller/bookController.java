package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pojo.order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class bookController {
    @ResponseBody//表示返回的是一个主体
    @RequestMapping(value = "/demo/{uname}")
    public String getOrderInfo(@PathVariable("uname")String uname) throws JsonProcessingException {
        System.out.println(uname);
        String str = null;
        if(uname.equals("小王子")){
            order order1 = new order();
            order1.setDate("40");
            order1.setOnum("bName");
            ObjectMapper objectMapper=new ObjectMapper();
            str=objectMapper.writeValueAsString(order1);

        }
        else{

        }
        return "Order";
    }
}
