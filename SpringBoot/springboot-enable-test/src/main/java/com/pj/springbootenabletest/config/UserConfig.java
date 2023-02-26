package com.pj.springbootenabletest.config;

import com.pj.springbootenabletest.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Pj-Xk
 * @date : 2022-10-10 10:34
 **/
//@Configuration
public class UserConfig {
    @Bean
    public User user(){
        return new User();
    }
}
