package com.pj.springbootmybatis;

import com.pj.springbootmybatis.Service.UserService;
import com.pj.springbootmybatis.Service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootMybatisApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootMybatisApplication.class, args);
        UserService userService = context.getBean(UserServiceImpl.class);
        System.out.println("打印："+userService.FindAll());
    }

}
