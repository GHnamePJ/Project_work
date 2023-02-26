package cn.mybatis.dynamic.annosql.service;

import cn.mybatis.dynamic.annosql.po.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    UserInfo getUserById(int id);
    List<UserInfo> getUserByParams_1(Map<Object, Object> params);
    void insertUser(UserInfo user);
    void updateUser(UserInfo user);
    void deleteUser(int id);
}
