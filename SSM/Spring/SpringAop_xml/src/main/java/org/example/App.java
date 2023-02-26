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
        ISpeech iSpeech1=(ISpeech) apc.getBean("ISpeech_proxy_bean1");
        iSpeech1.takeSpeech();
        System.out.println();
        ISpeech iSpeech2=(ISpeech) apc.getBean("ISpeech_proxy_bean2");
        iSpeech2.printText();
        System.out.println();
        IConcert iConcert=(IConcert)apc.getBean("IConcert_proxy_bean");
        iConcert.playMusic("Die Young");
    }
}
