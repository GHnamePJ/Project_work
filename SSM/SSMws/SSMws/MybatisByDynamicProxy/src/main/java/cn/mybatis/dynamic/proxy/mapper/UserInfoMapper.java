package cn.mybatis.dynamic.proxy.mapper;

import cn.mybatis.dynamic.proxy.po.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    UserInfo getUserById(int id);
    List<UserInfo> getUserByName(String realName);
    List<UserInfo> getUserByids(List<Integer> ids);
    void insertUser(UserInfo user);
    void updateUser(UserInfo user);
    void deleteUser(int id);
}
