package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String []args){
        ApplicationContext apc=new ClassPathXmlApplicationContext("applicationContext.xml");
        Theatre theatre=apc.getBean(Theatre.class);
        theatre.doActivite();
    }
}
