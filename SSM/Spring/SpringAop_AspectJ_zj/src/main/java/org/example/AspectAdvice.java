package org.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Aspect
public class AspectAdvice {
    @Before("execution(* org.example.IUser.userInfo(..))")
    public void before(){
        Scanner scanner=new Scanner(System.in);
        String name=scanner.next();
        if(name.equals("胖橘")){
            System.out.println("权限认证成功！");
        }else{
            System.out.println("权限认证失败");
            System.exit(0);
        }
    }

}
