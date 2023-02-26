package com.pj.springbootvue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * @author : Pj-Xk
 * @date : 2022-10-13 19:45
 **/
//Cors：跨域资源共享
//跨域请求配置（Cors全局跨域）
@Configuration//自动加载
public class CorsConfig {
    private CorsConfiguration BuildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //设置允许单个端口
//        corsConfiguration.addAllowedOrigin("http://localhost:8081");
        //设置允许所有端口
        corsConfiguration.addAllowedOriginPattern("*");
        //设置允许的请求头
        corsConfiguration.addAllowedHeader("*");
        //设置允许的请求方法（post，get）
        corsConfiguration.addAllowedMethod("*");

        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //对接口配置跨域设置
        //UrlBasedCorsConfigurationSource获取跨域配置信息
        source.registerCorsConfiguration("/**", BuildConfig());
        return new CorsFilter(source);
    }
}

