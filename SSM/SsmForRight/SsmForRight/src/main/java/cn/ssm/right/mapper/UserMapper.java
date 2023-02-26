package cn.ssm.right.mapper;

import cn.ssm.right.po.User;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UserMapper {
    @SelectProvider(type = UserSql.class, method = "getUserByCond")
    List<User> getUserByCond(User user);
}
