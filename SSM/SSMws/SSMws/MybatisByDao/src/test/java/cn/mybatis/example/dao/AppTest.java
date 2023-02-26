package cn.mybatis.example.dao;

import static org.junit.Assert.assertTrue;

import cn.mybatis.example.dao.po.UserInfo;
import cn.mybatis.example.dao.serviceImpl.UserService;
import cn.mybatis.example.dao.serviceImpl.UserServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private SqlSessionFactory sqlSessionFactory = null;
    @Before
    public void init() throws IOException {
        // 第一步，创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 第二步，创建SqlSessionFactoryBuilder对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 第三步，创建SqlSessionFactory对象
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testGetUserById() {
        UserService userService = new UserServiceImpl(this.sqlSessionFactory);
        UserInfo user = userService.getUserById(1);
        System.out.println("by id:" + user);
    }

    @Test
    public void testGetUserByName() {
        UserService userService = new UserServiceImpl(this.sqlSessionFactory);
        List<UserInfo> ulist = userService.getUserByName("李四");
        System.out.println("by id:" + ulist.get(0));
    }

    @Test
    public void testInsertUser() {
        UserService userService = new UserServiceImpl(this.sqlSessionFactory);
        UserInfo user = new UserInfo();
        user.setLoginName("lisi333");
        user.setRealName("李四");
        user.setPassword("123");
        userService.insertUser(user);
        System.out.println("insert success");
    }

    @Test
    public void testUpdateUser() {
        UserService userService = new UserServiceImpl(this.sqlSessionFactory);
        UserInfo user = new UserInfo();
        //user.setId(97);
        user.setLoginName("linshi");
        user.setPassword("3456789");
        user.setRealName("林十");
        userService.updateUser(user);
        System.out.println("update success");
    }

    @Test
    public void testDeleteUser() {
        UserService userService = new UserServiceImpl(this.sqlSessionFactory);
        userService.deleteUser(17);
        System.out.println("delete success");
    }
}
