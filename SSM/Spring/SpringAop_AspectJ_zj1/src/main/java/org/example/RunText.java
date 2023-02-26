package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunText {
    public static void main(String[] args){
        ApplicationContext apc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Theater theater=(Theater)apc.getBean(Theater.class);
        theater.doActivite();
    }
}
