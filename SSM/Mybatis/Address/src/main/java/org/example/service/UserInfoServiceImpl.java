package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.UserInfoMapper;
import org.example.pojo.UserInfo;

import java.util.List;

public class UserInfoServiceImpl implements UserService{
    private SqlSessionFactory sqlSessionFactory;
    public UserInfoServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    @Override
    public List<UserInfo> getUser_address(int id){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserInfoMapper userMapper=sqlSession.getMapper(UserInfoMapper.class);
        List<UserInfo> userInfo=userMapper.getUser_address(id);
        sqlSession.commit();
        sqlSession.close();
        return userInfo;
    }
}
