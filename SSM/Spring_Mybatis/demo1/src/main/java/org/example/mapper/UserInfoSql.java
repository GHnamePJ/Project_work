package org.example.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.example.po.UserInfo;
//Sql构造器代替mapper.xml的sql语句
//写完后加到接口里面去
public class UserInfoSql {

    public String getUserById(final int id){
        return new SQL(){
            {
                SELECT("id,loginName,password,realName");
                FROM("t_user");
                if(id>0){
                    WHERE("id=#{id}");
                }

            }
        }.toString();
    }
    public String insertUser(final UserInfo userInfo){
        return new SQL(){
            {
               INSERT_INTO("t_user");

               if(userInfo.getLoginName()!=null && userInfo.getLoginName()!=""){
                    VALUES("loginName","#{loginName}");
               }
                if(userInfo.getRealName()!=null&&userInfo.getRealName()!=""){
                    VALUES("realName","#{realName}");
                }
                if(userInfo.getPassword()!=null&&userInfo.getPassword()!=""){
                    VALUES("password","#{password}");
                }
            }
        }.toString();
    }
    public String register(final String loginName,final String password){
        return new SQL(){
            {
//              select * from t_user where loginName='lisi333' and password='123'
                SELECT("*");
                FROM("t_user");
                if(loginName!=null && loginName!=""){
                    WHERE("loginName=#{arg0}");
                }
                if(password!=null && password!=""){
                    WHERE("password=#{arg1}");
                }
            }
        }.toString();
    }
}
