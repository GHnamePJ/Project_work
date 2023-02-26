package cn.mybatis.dynamic.proxy.service;

import cn.mybatis.dynamic.proxy.mapper.EmployeeMapper;
import cn.mybatis.dynamic.proxy.mapper.UserInfoMapper;
import cn.mybatis.dynamic.proxy.po.Employees;
import cn.mybatis.dynamic.proxy.po.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserInfoServiceImpl implements UserInfoService{
    private SqlSessionFactory sqlSessionFactory;

    public UserInfoServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public UserInfo getUserById(int id) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo userInfo = userInfoMapper.getUserById(id);
        sqlSession.close();
        return userInfo;
    }

    @Override
    public List<UserInfo> getUserByName(String realName) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        List<UserInfo> userInfoList = userInfoMapper.getUserByName(realName);
        sqlSession.close();
        return userInfoList;
    }

    @Override
    public void insertUser(UserInfo user) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        userInfoMapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUser(UserInfo user) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        userInfoMapper.updateUser(user);
//        Employees employees = new Employees();
//        employees.setId(3);
//        employees.setEmpName("aaaaaaaaaaaaaa");
//        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
//        employeeMapper.updateEmployee(employees);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteUser(int id) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        userInfoMapper.deleteUser(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
