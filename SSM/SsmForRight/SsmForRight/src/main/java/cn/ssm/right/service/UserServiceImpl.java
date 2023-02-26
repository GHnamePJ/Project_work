package cn.ssm.right.service;

import cn.ssm.right.mapper.UserMapper;
import cn.ssm.right.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    //依赖注入
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public int checkUser(User user) {
        List<User> userList = userMapper.getUserByCond(user);
        if(userList != null && userList.size() > 0){
            return 1;
        }else{
            return -1;
        }
    }
}
