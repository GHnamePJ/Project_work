package org.example.service;

import org.example.pojo.User;

public interface UserService {
    User getUserByPhone(String phone);
    User checkUser(User user);
    int insertUser(String name,String phone,String password,String face_photo);//插入用户
    int updatePassword(String phone,String password);//用户存在时根据手机号来修改用户密码
    int updateNameByPhone(String phone, String name);//修改用户名字
    int updatePhoneByPassword(String phone1,String phone2);//修改用户手机号
    int updateHead_photo(String phone,String head_photo);//修改头像
    int updateFace_photo(String phone,String face_photo);//修改人脸头像

}
