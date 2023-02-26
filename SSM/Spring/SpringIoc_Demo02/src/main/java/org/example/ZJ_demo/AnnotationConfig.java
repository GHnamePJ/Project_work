package org.example.ZJ_demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//代替xml文件
//注意@Value只能配置constructor-arg不能配置property
//constructor-arg必须要写带参构造方法传入属性
@Configuration
@ComponentScan(basePackages = "org.example.ZJ_demo")
public class AnnotationConfig {

}
