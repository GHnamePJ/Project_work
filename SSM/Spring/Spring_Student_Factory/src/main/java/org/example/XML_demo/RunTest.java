package org.example.XML_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {
    public static void main(String[] args){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        bean实例化工厂，工厂类配置好所有信息然后返回一个学生类类型的信息用student保存
        Student student=applicationContext.getBean("studentFactory_bean",Student.class);
        System.out.println("姓名："+student.getName()+"\n性别："+student.getSex());
    }
}
