package cn.mybatis.example.dao.daoImpl;

import cn.mybatis.example.dao.po.UserInfo;

import java.util.List;

public interface UserDao {
    UserInfo getUserById(int id);
    List<UserInfo> getUserByName(String realName);
    void insertUser(UserInfo user);
    void updateUser(UserInfo user);
    void deleteUser(int id);
}
