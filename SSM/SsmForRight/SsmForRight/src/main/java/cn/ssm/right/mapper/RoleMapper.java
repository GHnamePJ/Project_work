package cn.ssm.right.mapper;

import cn.ssm.right.po.Role;
import org.apache.ibatis.annotations.SelectProvider;

public interface RoleMapper {
    @SelectProvider(type = RoleSql.class, method = "getRoleById")
    Role getRoleById(int id);
}
