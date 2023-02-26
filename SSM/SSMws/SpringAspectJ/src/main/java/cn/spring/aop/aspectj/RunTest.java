package cn.spring.aop.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {
    public static void main(String[] args) {
        ApplicationContext apc=new ClassPathXmlApplicationContext("applicationContext.xml");
        IPrintInfo iPrintInfo=(IPrintInfo)apc.getBean("PrintInfoImpl_bean") ;
        iPrintInfo.printMessage("胖橘");
        iPrintInfo.showMessage();
        iPrintInfo.editMessage();

    }
}
