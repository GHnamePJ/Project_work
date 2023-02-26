package org.example.XML_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Runtest{
    public static void main(String[] args){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        Use use=applicationContext.getBean("User_Bean",Use.class);
        use.print();
    }

}
