package cn.mybatis.dynamic.annosql.mapper;

import cn.mybatis.dynamic.annosql.po.UserInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class UserInfoSql {
    public String getUserByIdSql(final int id){
        return new SQL(){
           {
               SELECT("*");
               FROM("t_user");
               if(id > 0){
                   WHERE("id=#{id}");
               }
           }
       }.toString();
    }

    public String getUserByParamsSql_1(final Map<Object, Object> map){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_user");
                if(map.get("id") != null){
                    WHERE("id=#{id}");
                } else if(map.get("realName")!=null && !map.get("realName").equals("")){
                    WHERE("realName like CONCAT('%', #{realName}, '%')");
                }
            }
        }.toString();
    }

    public String insertUserSql(final UserInfo user){
        return new SQL(){
            {
                INSERT_INTO("t_user");
                if(user.getLoginName() != null && !user.getLoginName().equals("")){
                    VALUES("loginName", "#{loginName}");
                }
                if(user.getRealName() != null && !user.getRealName().equals("")){
                    VALUES("realName", "#{realName}");
                }
                if(user.getPassword() != null && !user.getPassword().equals("")){
                    VALUES("password", "#{password}");
                }
            }
        }.toString();
    }

    public String updateUserSql(final UserInfo user){
        return new SQL(){
            {
                UPDATE("t_user");
                if(user.getLoginName() != null && !user.getLoginName().equals("")){
                    SET("loginName=#{loginName}");
                }
                if(user.getRealName() != null && !user.getRealName().equals("")) {
                    SET("realName=#{realName}");
                }
                if(user.getPassword() != null && !user.getPassword().equals("")) {
                    SET("password=#{password}");
                }
                if(user.getId() != null){
                    WHERE("id=#{id}");
                }
            }
        }.toString();
    }

    public String deleteUserSql(final int id){
        return new SQL(){
            {
                DELETE_FROM("t_user");
                if(id > 0){
                    WHERE("id=#{id}");
                }
            }
        }.toString();
    }
}
