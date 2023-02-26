package cn.mybatis.example.dao.serviceImpl;

import cn.mybatis.example.dao.daoImpl.UserDao;
import cn.mybatis.example.dao.daoImpl.UserDaoImpl;
import cn.mybatis.example.dao.po.UserInfo;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService{
    private SqlSessionFactory sqlSessionFactory;

    public UserServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public UserInfo getUserById(int id) {
        UserDao userDao = new UserDaoImpl(this.sqlSessionFactory);
        UserInfo user = userDao.getUserById(id);
        return user;
    }

    @Override
    public List<UserInfo> getUserByName(String realName) {
        UserDao userDao = new UserDaoImpl(this.sqlSessionFactory);
        List<UserInfo> userList = userDao.getUserByName(realName);
        return userList;
    }

    @Override
    public void insertUser(UserInfo user) {
        UserDao userDao = new UserDaoImpl(this.sqlSessionFactory);
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(UserInfo user) {
        UserDao userDao = new UserDaoImpl(this.sqlSessionFactory);
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        UserDao userDao = new UserDaoImpl(this.sqlSessionFactory);
        userDao.deleteUser(id);
    }
}
