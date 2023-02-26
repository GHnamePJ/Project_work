package org.example.service;

import org.example.pojo.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> getUser_address(int id);
}
