package com.pj.springbootvue.service;

import com.pj.springbootvue.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-13 19:39
 **/
@Service
public interface UserService {
    public List<User> FindAll();
}
