package org.example.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.example.pojo.User;

public interface UserMapper {//接口，使用注解方式写sql语句，
    @SelectProvider(type = UserSql.class,method = "getUserByPhone")
    User getUserByPhone(String phone);
    @SelectProvider(type = UserSql.class,method ="getUserByCond")
    User getUserByCond(User user);
    @InsertProvider(type = UserSql.class,method = "insertUser")
    int insertUser(String name,String phone,String password,String face_photo);//插入用户的信息
    @UpdateProvider(type = UserSql.class,method = "updatePassword")
    int updatePassword(String phone,String password); //用户存在时根据手机号来修改用户密码
    @UpdateProvider(type = UserSql.class,method = "updateNameByPhone")
    int updateNameByPhone( String phone,String name);//修改用户名字
    //    updatePhoneByPassword
    @UpdateProvider(type = UserSql.class,method = "updatePhoneByPassword")
    int updatePhoneByPassword(String phone1,String phone2);//修改用户手机号
    @UpdateProvider(type = UserSql.class,method = "updateHead_photo")
    int updateHead_photo(String phone,String head_photo);//修改头像
    @UpdateProvider(type = UserSql.class,method = "updateFace_photo")
    int updateFace_photo(String phone,String face_photo);//修改人脸头像
}
