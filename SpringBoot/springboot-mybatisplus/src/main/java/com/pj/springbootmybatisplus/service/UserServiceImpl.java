package com.pj.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pj.springbootmybatisplus.mapper.UserMapper;
import com.pj.springbootmybatisplus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2023-02-17 15:01
 **/
@Service//表明这是一个Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
//    查询全部
    @Autowired
    UserMapper userMapper;
    public List<User> queryAll() {
            return userMapper.selectList(null);
    }
}
