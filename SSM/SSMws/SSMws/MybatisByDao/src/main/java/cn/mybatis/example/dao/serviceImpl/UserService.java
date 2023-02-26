package cn.mybatis.example.dao.serviceImpl;

import cn.mybatis.example.dao.po.UserInfo;

import java.util.List;

public interface UserService {
    UserInfo getUserById(int id);
    List<UserInfo> getUserByName(String realName);
    void insertUser(UserInfo user);
    void updateUser(UserInfo user);
    void deleteUser(int id);
}
