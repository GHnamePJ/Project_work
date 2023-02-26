package cn.ssm.right.mapper;

import org.apache.ibatis.jdbc.SQL;

public class RoleSql {
    public String getRoleById(final int id){
        return new SQL() {
            {
                SELECT("*");
                FROM("t_base_role");
                if(id > 0) {
                    WHERE("id=#{id}");
                }
            }
        }.toString();
    }
}
