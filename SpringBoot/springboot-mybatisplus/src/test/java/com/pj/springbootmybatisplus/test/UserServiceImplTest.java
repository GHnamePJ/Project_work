package com.pj.springbootmybatisplus.test;

import com.pj.springbootmybatisplus.mapper.UserMapper;
import com.pj.springbootmybatisplus.pojo.User;
//import com.pj.springbootmybatisplus.service.UserServiceImpl;
import com.pj.springbootmybatisplus.service.UserService;
import com.pj.springbootmybatisplus.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2023-02-17 23:19
 **/
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;
    @Test
    public void Test() {
        //查询全部用户，参数是一个Wrapper，条件构造器，先不使用为null
        List<User> users = userService.queryAll();
        System.out.println(users);
    }
}
