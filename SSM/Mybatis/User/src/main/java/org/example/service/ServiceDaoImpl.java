package org.example.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.dao.UserInfoDao;
import org.example.dao.UserInfoIDaompl;
import org.example.pojo.UserInfo;

import java.util.List;

public class ServiceDaoImpl implements ServiceDao{
    private SqlSessionFactory sqlSessionFactory;
    public ServiceDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    @Override
    public List<UserInfo> getUserbyloginName(String loginName){
        UserInfoDao userInfoDao=new UserInfoIDaompl(sqlSessionFactory);
        List<UserInfo> user=userInfoDao.getUserbyloginName(loginName);
        return user;
    }
    @Override
    public UserInfo getUserbyid(Integer id){
        UserInfoDao userInfoDao=new UserInfoIDaompl(this.sqlSessionFactory);
        UserInfo user=userInfoDao.getUserbyid(id);
        return user;
    }
    public void insertUser(UserInfo user){
        UserInfoDao userInfoDao=new UserInfoIDaompl(this.sqlSessionFactory);
        userInfoDao.insertUser(user);
    }
    public void updateUser(UserInfo user){
        UserInfoDao userInfoDao=new UserInfoIDaompl(this.sqlSessionFactory);
        userInfoDao.updateUser(user);

    }
    public void deleteUser(Integer id){
        UserInfoDao userInfoDao=new UserInfoIDaompl(this.sqlSessionFactory);
        userInfoDao.deleteUser(id);
    }
}
