package org.example.ZJ_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration代替xml文件
//注意@Value只能配置constructor-arg(必须要写带参构造方法传入属性)不能配置property(就是没有set方法)
@Configuration
public class AnnotationConfig {
    @Value("2022")
    String department_num;
    @Value("前端")
    String department_name;

    @Bean
    public Department department(){
        return new Department(department_num,department_name);
    }

    @Value("P J")
    String username;
    @Value("胖橘")
    String realname;
    @Value("123456")
    String password;
    @Value("30954@qq.com")
    String email;

    @Bean
    public Enployee enployee(Department department){
        return new Enployee(username,realname,password,email,department);
    }

    @Bean
    public Use use(Enployee enployee){
        return new Use(enployee);
    }
}
