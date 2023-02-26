package org.example;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectAdvice {
    @Before("execution(* org.example.IConcert.playMusic(..))&& args(musicName)")
    public void before(String musicName){
        System.out.println("演奏前");
    }
    @AfterReturning("execution(* org.example.IConcert.playMusic(..))&& args(musicName)")
    public void after(String musicName){
        System.out.println("演奏后");
    }
}
