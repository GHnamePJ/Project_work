package com.pj.springbootmybatis.mapper;

import com.pj.springbootmybatis.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {
    //UserMapper测试
    @Autowired
    private UserMapper userMapper;
    @Test
    public void FindAllTest(){
        List<User> user = userMapper.FindAll();
        System.out.println(user);
    }
//    //UserXmlMapper测试
//    @Autowired
//    private UserXmlMapper userXmlMapper;
//    @Test
//    public void FindAllTest(){
//        List<User> user = userXmlMapper.FindAll();
//        System.out.println(user);
//    }
}
