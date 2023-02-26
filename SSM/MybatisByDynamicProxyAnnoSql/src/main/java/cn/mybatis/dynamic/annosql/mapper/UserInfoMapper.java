package cn.mybatis.dynamic.annosql.mapper;

import cn.mybatis.dynamic.annosql.po.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    //加@ResultMap
    @SelectProvider(type=UserInfoSql.class, method="getUserByIdSql")
    UserInfo getUserById(int id);

    @SelectProvider(type=UserInfoSql.class, method="getUserByParamsSql_1")
    List<UserInfo> getUserByParams_1(Map<Object, Object> params);

    @InsertProvider(type=UserInfoSql.class, method = "insertUserSql")
    void insertUser(UserInfo user);

    @UpdateProvider(type=UserInfoSql.class, method = "updateUserSql")
    void updateUser(UserInfo user);

    @DeleteProvider(type=UserInfoSql.class, method = "deleteUserSql")
    void deleteUser(int id);

}
