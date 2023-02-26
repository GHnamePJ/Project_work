package org.example;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.pojo.UserInfo;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AppTest 
{
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sqlSessionFactory=sqlSessionFactoryBuilder.build(in);
    }
    @Test
    public void testgetuserById(){
        int id=4;
        UserService userService=new UserServiceImpl(this.sqlSessionFactory);
        UserInfo userInfo =userService.getuserById(id);
        System.out.println(userInfo);
    }
}
