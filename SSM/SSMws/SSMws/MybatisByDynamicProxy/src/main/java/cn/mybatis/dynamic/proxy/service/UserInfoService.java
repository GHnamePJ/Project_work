package cn.mybatis.dynamic.proxy.service;

import cn.mybatis.dynamic.proxy.po.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo getUserById(int id);
    List<UserInfo> getUserByName(String realName);
    void insertUser(UserInfo user);
    void updateUser(UserInfo user);
    void deleteUser(int id);

}
