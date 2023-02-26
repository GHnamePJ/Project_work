package com.pj.springbootcondition.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

//@Target({ElementType.TYPE, ElementType.METHOD})//说明我们的注解可以加到什么地方上
//@Retention(RetentionPolicy.RUNTIME)//说明我们的注解在什么时候生效
//@Documented//是元注解。注解被@Documented标注，那么被注解修饰的类，生成文档时，会显示注解名
//@Conditional(ClassCondition.class)//条件：满足该条件才能进行下一步
//public @interface ConditionOnClass {
//    //字符串数组
//    String[] value();
//
//}
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ClassCondition.class)
public @interface ConditionOnClass {
    String[] value();
}
