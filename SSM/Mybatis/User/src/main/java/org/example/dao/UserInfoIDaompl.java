package org.example.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.pojo.UserInfo;

import java.util.List;

public class UserInfoIDaompl implements UserInfoDao{
    private SqlSessionFactory sqlSessionFactory;
    public UserInfoIDaompl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
 }
    @Override
    public List<UserInfo> getUserbyloginName(String loginName){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        List<UserInfo> user=sqlSession.selectList("getUserbyloginName",loginName);
        sqlSession.close();
        return user;
    }
    @Override
    public UserInfo getUserbyid(Integer id){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        UserInfo user=sqlSession.selectOne("getUserbyid",id);
        sqlSession.close();
        return user;
    }
    @Override
    public void insertUser(UserInfo user){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        sqlSession.selectOne("insertUser",user);
        sqlSession.commit();
        sqlSession.close();
    }
    @Override
    public void updateUser(UserInfo user){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        sqlSession.selectOne("updateUser",user);
        sqlSession.commit();
        sqlSession.close();
    }
    @Override
    public void deleteUser(Integer id){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        sqlSession.selectOne("deleteUser",id);
        sqlSession.commit();
        sqlSession.close();
    }
}
