package cn.mybatis.dynamic.annosql;

import static org.junit.Assert.assertTrue;

import cn.mybatis.dynamic.annosql.po.Employees;
import cn.mybatis.dynamic.annosql.po.UserInfo;
import cn.mybatis.dynamic.annosql.service.EmployeeService;
import cn.mybatis.dynamic.annosql.service.EmployeeServiceImpl;
import cn.mybatis.dynamic.annosql.service.UserInfoService;
import cn.mybatis.dynamic.annosql.service.UserInfoServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private SqlSessionFactory sqlSessionFactory;

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
    public void getUserById() {
        Integer id = 2;
        UserInfoService userInfoService = new UserInfoServiceImpl(this.sqlSessionFactory);
        UserInfo user = userInfoService.getUserById(id);
        System.out.println(user);
    }

    @Test
    public void getUserByParams_1() {
        Map<Object, Object> params = new HashMap<Object, Object>();
        params.put("id", 3);
        params.put("realName", "王");
        UserInfoService userInfoService = new UserInfoServiceImpl(this.sqlSessionFactory);
        List<UserInfo> userList = userInfoService.getUserByParams_1(params);
        System.out.println(userList.get(0));
    }

    @Test
    public void insertUser() {
        UserInfoService userInfoService = new UserInfoServiceImpl(this.sqlSessionFactory);
        UserInfo user = new UserInfo();
        user.setLoginName("chenshi");
        user.setRealName("陈十");
        user.setPassword("123123");
        userInfoService.insertUser(user);
        System.out.println("insert success");
    }

    @Test
    public void updateUser() {
        UserInfoService userInfoService = new UserInfoServiceImpl(this.sqlSessionFactory);
        UserInfo user = new UserInfo();
        user.setId(72);
        user.setLoginName("zhoujiujiu");
        user.setRealName("周九九");
        userInfoService.updateUser(user);
        System.out.println("update success");
    }

    @Test
    public void deleteUser() {
        Integer id = 71;
        UserInfoService userInfoService = new UserInfoServiceImpl(this.sqlSessionFactory);
        userInfoService.deleteUser(id);
        System.out.println("delete success");
    }

    @Test
    public void updateEmployee() {
        EmployeeService employeeService = new EmployeeServiceImpl(this.sqlSessionFactory);
        Employees employees = new Employees();
        employees.setId(12);
        employees.setEmpName("johnaaa");
        employeeService.updateEmployee(employees);
        System.out.println("update success");
    }
}
