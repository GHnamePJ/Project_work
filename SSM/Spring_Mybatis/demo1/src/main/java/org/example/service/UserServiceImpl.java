package org.example.service;

import org.example.mapper.UserInfoMapper;
import org.example.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserInfoMapper userInfoMapper;
    @Autowired
    public UserServiceImpl(UserInfoMapper userInfoMapper){
        this.userInfoMapper=userInfoMapper;
    }
    @Override
    public UserInfo getUserById(int id){
        UserInfo userInfo=this.userInfoMapper.getUserById(id);
        return userInfo;
    }
    @Override
    public void insertUser(UserInfo userInfo){
        int rs=this.userInfoMapper.insertUser(userInfo);
        System.out.println(rs);
    }
    @Override
    public UserInfo register(String loginName,String password){
//        UserInfo userInfo=new UserInfo();
        UserInfo userInfo=this.userInfoMapper.register(loginName,password);
//        System.out.println(userInfo);
        if(userInfo.getLoginName().equals(loginName)){
            System.out.println("验证成功");
        }else{
            System.out.println("验证失败");
        }
        return userInfo;
    }

}
