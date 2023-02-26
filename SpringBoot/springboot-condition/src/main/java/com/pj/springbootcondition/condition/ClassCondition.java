package com.pj.springbootcondition.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import java.util.Map;

public class ClassCondition implements Condition {
    /*
    *参数context：上下文对象。用于获取环境、Ioc容器、CLassLoader
    *参数metadata：注解元对象。用于获取注解定义的属性
    */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        获取注解指定的属性值value，动态的判断字节码文件是否存在，然后决定是否创建Bean
        Map<String, Object> map = metadata.getAnnotationAttributes(ConditionOnClass.class.getName());
//        转换成字符串数组
        String[] value = (String[])map.get("value");
        Boolean flag = true;
        try {
            for (String className : value) {
                System.out.println("This is："+className);
                Class<?> aClass = Class.forName(className);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}



//        判断字节码文件是否存在
//        Boolean flag = true;
//        try {
//            Class<?> aClass = Class.forName("org.springframework.data.redis.core.RedisTemplate");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
