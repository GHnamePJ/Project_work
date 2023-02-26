package org.example.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.example.pojo.User;

public class UserSql {
    //根据手机号和密码查询用户是否存在
    public String getUserByCond(final User user){
        return new SQL(){
            {
                SELECT("*");
                FROM("face_user u");
                if(user.getPhone()!=null&&user.getPhone()!=""){
                    WHERE("phone=#{phone}");
                }
                if(user.getPassword()!=null&&user.getPassword()!=""){
                    WHERE("password=#{password}");
                }
            }
        }.toString();
    }
    //插入一个用户信息
    public String insertUser(final String name,final String phone,final String password,final String face_photo){
        return new SQL(){
            {
                INSERT_INTO("face_user");
                if(name!=null&&name!=""){
                    VALUES("name","#{arg0}");
                }
                if(phone!=null&&phone!=""){
                    VALUES("phone","#{arg1}");
                }
                if(password!=null&&password!=""){
                    VALUES("password","#{arg2}");
                }
                if(face_photo!=null&&face_photo!=""){
                    VALUES("face_photo","#{arg3}");
                }
            }
        }.toString();
    }
    //查询手机号是否已存在
    public String getUserByPhone(final String phone){
        return new SQL(){
            {
                SELECT("*");
                FROM("face_user");
                if(phone!=null&&phone!=""){
                    WHERE("phone=#{phone}");
                }
            }
        }.toString();
    }
    //    根据手机号，修改密码
    public String updatePassword(final String phone,final String password){
        return new SQL(){
            {
                UPDATE("face_user");
                if(phone!=null&&phone!=""&&password!=null&&password!=""){
                    SET("password=#{arg1}");
                }
                WHERE("phone=#{arg0}");
            }
        }.toString();
    }
    //根据手机号,更改用户名字
    public String updateNameByPhone(final String phone,final String name){
        return new SQL(){
            {
                UPDATE("face_user");
                if (phone!=null&&phone!=""&&name!=null&&name!=""){
                    SET("name=#{arg1}");
                }
                WHERE("phone=#{arg0}");
            }

        }.toString();
    }
    //改用户手机号
    public String updatePhoneByPassword(final String phone1 ,final String phone2){
        return new SQL(){
            {
                UPDATE("face_user");
                if(phone1!=null&&phone1!=""){
                    SET("phone=#{arg1}");
                }
                WHERE("phone=#{arg0}");
            }
        }.toString();
    }
    //改头像
    public String updateHead_photo(final String phone ,final String head_photo){
        return new SQL(){
            {
                UPDATE("face_user");
                if(phone!=null&&phone!=""){
                    SET("head_photo=#{arg1}");
                }
                WHERE("phone=#{arg0}");
            }
        }.toString();
    }
    //修改人脸识别图片
    public String updateFace_photo(final String phone ,final String face_photo){
        return new SQL(){
            {
                UPDATE("face_user");
                if(phone!=null&&phone!=""){
                    SET("face_photo=#{arg1}");
                }
                WHERE("phone=#{arg0}");
            }
        }.toString();
    }
}
