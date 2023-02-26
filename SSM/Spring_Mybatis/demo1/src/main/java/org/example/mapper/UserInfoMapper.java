package org.example.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.example.po.UserInfo;

public interface UserInfoMapper {
    @SelectProvider(type=UserInfoSql.class,method ="getUserById")
    public UserInfo getUserById(int id);
    @InsertProvider(type = UserInfoSql.class,method = "insertUser")
    public int insertUser(UserInfo userInfo);
    @SelectProvider(type = UserInfoSql.class,method = "register")
    public UserInfo register(String loginName,String password);
}
