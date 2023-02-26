package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.UserInfo;

import java.util.List;

public interface UserMapper {
    @Select("select * from t_user where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "loginName",column = "loginName"),
            @Result(property = "password",column = "password"),
            @Result(property = "realName", column = "realName"),
            @Result(property = "addressInfoList",column = "id",
            many = @Many(select = "org.example.mapper.AddressMapper.getaddressById"))
    })
//    一用类类型就可以l
    UserInfo getuserById(@Param("id") int id);
}
