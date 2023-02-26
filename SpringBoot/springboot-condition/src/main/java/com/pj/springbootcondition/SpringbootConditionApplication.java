package com.pj.springbootcondition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//该注解内含@Configuration注解，所以添加@SpringBootApplication注解，就相当于是个配置类
@SpringBootApplication
public class SpringbootConditionApplication {
    public static void main(String[] args) {
//        启动SpringBoot应用，返回Spring的Ioc容器
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootConditionApplication.class, args);
//        1.获取Bean(要引入Redis对应的依赖),redisTemplate
//        Object redisTemplate = context.getBean("redisTemplate");
//        System.out.println("获取redisTemplate的Bean:"+redisTemplate);
        Object user = context.getBean("user");
        System.out.println("获取user的Bean:"+user);
    }

}
