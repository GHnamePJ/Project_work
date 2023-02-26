package cn.ssm.right.mapper;

import cn.ssm.right.po.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSql {
    public String getUserByCond(final User user){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_base_user");
                //两行WHERE之间未加OR(),表示AND操作
                if(user.getLoginName() != null){
                    WHERE("login_name=#{loginName}");
                }
                if(user.getPassword() != null) {
                    WHERE("password=#{password}");
                }
            }
        }.toString();
    }
}
