package org.example;

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
        ApplicationContext apc=new ClassPathXmlApplicationContext("applicationContext.xml");
        IUser iUser=(IUser)apc.getBean(UserImpl.class);
        iUser.userInfo();
    }
}
