package org.example;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectAdvice {
    @Before(value = "execution(* org.example.ISpeech.printText(..))")
    public void before(){
        System.out.println("准备演讲");
    }
    @AfterReturning(value = "execution(* org.example.ISpeech.takeSpeak(..))")
    public void after(){
        System.out.println("演讲结束");
    }
}
