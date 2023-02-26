package org.example;

import org.example.po.UserInfo;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext apc=new ClassPathXmlApplicationContext("SpringMybatisContext.xml");
        UserService userService=apc.getBean(UserServiceImpl.class);
//        UserInfo userInfo=userService.getUserById(72);
//        System.out.println(userInfo);
//        userService.insertUser();
        userService.register("lisi333","123");
    }
}
