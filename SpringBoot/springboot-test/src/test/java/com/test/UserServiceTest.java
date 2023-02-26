package com.test;

import com.pj.SpringbootTestApplication;
import com.pj.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//UserService的测试类
@SpringBootTest(classes = SpringbootTestApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void addTest(){
        userService.add();
    }
}
