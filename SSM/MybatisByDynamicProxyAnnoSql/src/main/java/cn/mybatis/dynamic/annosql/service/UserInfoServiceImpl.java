package cn.mybatis.dynamic.annosql.service;

import cn.mybatis.dynamic.annosql.mapper.UserInfoMapper;
import cn.mybatis.dynamic.annosql.po.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

public class UserInfoServiceImpl implements UserInfoService {
    private SqlSessionFactory sqlSessionFactory;

    public UserInfoServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public UserInfo getUserById(int id) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo user = userInfoMapper.getUserById(id);
        sqlSession.close();
        return user;
    }

    @Override
    public List<UserInfo> getUserByParams_1(Map<Object, Object> params) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        List<UserInfo> userList = userInfoMapper.getUserByParams_1(params);
        sqlSession.close();
        return userList;
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
