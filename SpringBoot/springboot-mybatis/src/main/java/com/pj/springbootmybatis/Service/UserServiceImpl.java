package com.pj.springbootmybatis.Service;

import com.pj.springbootmybatis.domain.User;
import com.pj.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-12 22:40
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
