package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.po.UserInfo;

public interface UserService {
    public UserInfo getUserById(int id);
    public void insertUser(UserInfo userInfo);
    public UserInfo register(String loginName,String password);
}
