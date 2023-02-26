package com.pj.springbootvue;

import com.pj.springbootvue.service.UserService;
import com.pj.springbootvue.service.UserServiceImpl;
import com.pj.springbootvue.service.WeatherService;
import com.pj.springbootvue.service.WeatherServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootVueApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootVueApplication.class, args);
        UserService userService = context.getBean(UserServiceImpl.class);
        System.out.println("角色："+userService.FindAll());
        WeatherService weatherService = context.getBean(WeatherServiceImpl.class);
        System.out.println("每日气象："+weatherService.FindAllWeather());
    }

}
