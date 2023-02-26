package org.example.service;

import org.example.pojo.UserInfo;

import java.util.List;

public interface ServiceDao {
    List<UserInfo> getUserbyloginName(String loginName);
    UserInfo getUserbyid(Integer id);
    void insertUser(UserInfo user);
    void updateUser(UserInfo user);
    void deleteUser(Integer id);
}
