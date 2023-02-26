package com.pjxk.springbootenable;

import com.pj.springbootenabletest.config.EnableUser;
import com.pj.springbootenabletest.config.MyImportSelector;
import com.pj.springbootenabletest.config.UserConfig;
import com.pj.springbootenabletest.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/*
    @ComponentScan扫描范围：当前引导类所在的包及其子包
    所以拿不到自定义工程依赖下的bean
    解决方案
    1.在引入自定义工程依赖后，在引导类扫描自定义工程bean所在的包
    2.使用@Import注解加载类，这些类都会被spring容器加载，放到Ioc容器中
    3.在依赖的工程中为创建Bean的配置类写一个注解类（导入配置类），然后直接使用注解，即可获取到bean
 */
//@ComponentScan("com.pj.springbootenabletest.config")
//@Import(UserConfig.class)
//@EnableUser

/*
* @Import的4种用法
* 1.导入Bean(即直接导入类的字节码文件)
* 2.导入配置类(负责创建Bean的配置类可以不写@Configuration)
* 3.导入ImportSelector实现类。一般用于加载配置文件中的类。
* 4.导入ImportBeanDefinitionRegistrar实现类
* */
//@Import(User.class)
//@Import(UserConfig.class)
@Import(MyImportSelector.class)
@SpringBootApplication
public class SpringbootEnableApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootEnableApplication.class, args);
        //直接获取引入的自定义工程依赖下的bean，是无法拿到的
//        Object user = context.getBean("user");
//        System.out.print(user);
        User user = context.getBean(User.class);
        System.out.println(user);
    }
}
