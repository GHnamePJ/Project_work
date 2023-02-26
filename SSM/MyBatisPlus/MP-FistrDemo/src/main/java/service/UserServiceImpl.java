package service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2023-02-20 10:15
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper,User> implements UserService {
    @Autowired
    UsersMapper usersMapper;
    @Override
    public List<User> getUsers() {
        return usersMapper.getUsers();
    }
}
