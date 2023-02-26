package com.pj.springbootvue.service;

import com.pj.springbootvue.mapper.UserMapper;
import com.pj.springbootvue.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-13 19:41
 **/
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> FindAll() {
        List<User> user = userMapper.FindAll();
        return user;
    }
}
