package com.pj.springbootmybatis.Service;

import com.pj.springbootmybatis.domain.User;
import com.pj.springbootmybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-12 22:32
 **/
@Service
public interface UserService{
    public List<User> FindAll();
}
