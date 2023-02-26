package org.example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class WrapAspectAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("演奏前");
//        执行原有方法
        invocation.proceed();
        System.out.println("演奏后");
        return null;
    }
}
