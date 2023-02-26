package org.example;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterAspectAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("打印之后");
    }
}
