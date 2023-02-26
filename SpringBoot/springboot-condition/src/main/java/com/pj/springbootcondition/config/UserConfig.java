package com.pj.springbootcondition.config;

import com.pj.springbootcondition.condition.ConditionOnClass;
import com.pj.springbootcondition.domain.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {//配置类，用来创建Bean（注解开发）
//    @Bean
//    @Conditional(ClassCondition.class)//条件：满足该条件才能进行下一步
//    public User user(){
//        return new User();
//    }
//    @Bean
//    @ConditionOnClass("com.alibaba.fastjson.JSON")//自定义注解完成与@Conditional一样的功能
//    public User user(){
//        return new User();
//    }
    @Bean
    @ConditionalOnProperty(name = "name",havingValue = "Pj")
    public User user(){
        return new User();
    }

}
