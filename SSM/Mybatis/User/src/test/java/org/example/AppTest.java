package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.pojo.UserInfo;
import org.example.service.ServiceDao;
import org.example.service.ServiceDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws Exception {
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sqlSessionFactory=sqlSessionFactoryBuilder.build(in);
    }
    @Test
    public void testgetUserbyloginName(){
        String loginName="l";
        ServiceDao serviceDao=new ServiceDaoImpl(this.sqlSessionFactory);
        List<UserInfo> user=serviceDao.getUserbyloginName(loginName);
        for (UserInfo i:user) {
            System.out.println(i);
        }

    }
    @Test
    public void testgetUserbyid(){
        Integer id=4;
        ServiceDao serviceDao=new ServiceDaoImpl(this.sqlSessionFactory);
        UserInfo user=serviceDao.getUserbyid(id);
        System.out.println(user);
    }
    @Test
    public void testinsertUser(){
        UserInfo user=new UserInfo();
        user.setLoginName("XH");
        user.setPassword("1220090");
        user.setRealName("小H");
        ServiceDao serviceDao=new ServiceDaoImpl(this.sqlSessionFactory);
        serviceDao.insertUser(user);
        System.out.println(user);
    }
    @Test
    public void testupdateUser(){
        UserInfo user=new UserInfo();
        user.setId(72);
        user.setLoginName("xX");
        user.setPassword("1982345");
        user.setRealName("小X");
        ServiceDao serviceDao=new ServiceDaoImpl(this.sqlSessionFactory);
        serviceDao.updateUser(user);
        System.out.println(user);
    }
    @Test
    public void testdeleteUser(){
        Integer id=73;
        ServiceDao serviceDao=new ServiceDaoImpl(this.sqlSessionFactory);
        serviceDao.deleteUser(id);
    }
}
