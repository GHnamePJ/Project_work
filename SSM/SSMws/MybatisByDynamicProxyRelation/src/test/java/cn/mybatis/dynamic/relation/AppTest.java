package cn.mybatis.dynamic.relation;

import static org.junit.Assert.assertTrue;

import cn.mybatis.dynamic.relation.mapper.LoginAccountMapper;
import cn.mybatis.dynamic.relation.po.Course;
import cn.mybatis.dynamic.relation.po.LoginAccount;
import cn.mybatis.dynamic.relation.po.Orders;
import cn.mybatis.dynamic.relation.po.StudentInfo;
import cn.mybatis.dynamic.relation.service.*;
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
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
    }

    @Test
    public void testGetOrderById() {
        OrdersService ordersService= new OrdersServiceImpl(this.sqlSessionFactory);
        List<Orders> orders = ordersService.getOrder(1);
        System.out.println("get order: " + orders);
    }

    @Test
    public void testGetAccount(){
        LoginAccountService loginAccountService = new LoginAccountServiceImpl(this.sqlSessionFactory);
        List<LoginAccount> list = loginAccountService.getAccount();
        System.out.println("get account: " + list.get(0));
    }

    @Test
    public void testGetStudentInfo(){
        StudentInfoService studentInfoService = new StudentInfoServiceImpl(this.sqlSessionFactory);
        List<StudentInfo> slist = studentInfoService.getStudentInfo();
        System.out.println(slist.get(0));
    }
    @Test
    public void testGetCourseInfo(){
        CourseService courseService = new CourseServiceImpl(this.sqlSessionFactory);
        List<Course> clist = courseService.getCourseInfo();
        System.out.println(clist.get(1));
    }
}
