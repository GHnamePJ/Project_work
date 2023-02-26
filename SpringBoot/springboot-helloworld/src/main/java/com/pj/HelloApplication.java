package com.pj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//引导类（一般以Application结尾），SpringBoot项目的入口
//
@SpringBootApplication
public class HelloApplication {
    public static void main(String args[]){
        SpringApplication.run(HelloApplication.class,args);
    }
}
