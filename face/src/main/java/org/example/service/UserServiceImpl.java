package org.example.service;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    //注入依赖
    UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public User getUserByPhone(String phone){
        User u=userMapper.getUserByPhone(phone);
        return u;
    }
    @Override
    public User checkUser(User user) {
        User u=userMapper.getUserByCond(user);
        return u;
    }
    @Override
    public int insertUser(String name,String phone,String password,String face_photo){
        int rs=userMapper.insertUser(name,phone,password,face_photo);
        return rs;
    }
    @Override
    public int updatePassword(String phone,String password){
        int rs=userMapper.updatePassword(phone,password);
        return  rs;
    }
    @Override
    public int updateNameByPhone(String phone, String name) {
        int rs=userMapper.updateNameByPhone(phone,name);
        return rs;
    }

    @Override
    public int updatePhoneByPassword(String phone1,String phone2) {
        int rs = userMapper.updatePhoneByPassword(phone1,phone2);
        return rs;
    }

    @Override
    public int updateHead_photo(String phone,String head_photo) {
        int rs = userMapper.updateHead_photo(phone,head_photo);
        return rs;
    }

    @Override
    public int updateFace_photo(String phone, String face_photo) {
        int rs = userMapper.updateFace_photo(phone,face_photo);
        return rs;
    }

}
