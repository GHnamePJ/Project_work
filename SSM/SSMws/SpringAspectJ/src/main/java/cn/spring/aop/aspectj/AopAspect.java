package cn.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AopAspect {
//    方法前增加，要求一个参数
    public void beforeMethod(JoinPoint joinPoint){
        System.out.println("准备打印" + joinPoint.toString());
    }

    public void printMessage1(){
        System.out.println("之前");
    }
    public void showMessage1(){
        System.out.println("之后");
    }
    public Object editMessage1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("之前");
        Object result=proceedingJoinPoint.proceed();
        System.out.println("之后");
        return result;
    }
}
