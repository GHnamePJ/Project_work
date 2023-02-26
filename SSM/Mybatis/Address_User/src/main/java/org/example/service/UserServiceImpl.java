package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.UserMapper;
import org.example.pojo.UserInfo;

import java.util.List;

public class UserServiceImpl implements UserService{
    private SqlSessionFactory sqlSessionFactory;
    public UserServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    public UserInfo getuserById(int id){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        UserInfo userInfo=userMapper.getuserById(id);
        sqlSession.commit();
        sqlSession.close();
        return userInfo;
    }
}
