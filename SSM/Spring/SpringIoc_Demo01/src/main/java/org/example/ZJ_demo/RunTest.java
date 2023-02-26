package org.example.ZJ_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunTest {
    public static void main(String[] args){
        ApplicationContext apc=new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Use use=apc.getBean(Use.class);
        use.printInfo();
    }
}
