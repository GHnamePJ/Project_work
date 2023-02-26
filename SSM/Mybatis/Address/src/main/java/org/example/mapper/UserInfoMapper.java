package org.example.mapper;

import org.example.pojo.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    List<UserInfo> getUser_address(int id);
}
