package service;

import com.baomidou.mybatisplus.extension.service.IService;
import pojo.User;

import java.util.List;


public interface UserService extends IService<User> {
    //IService包括了BaseMapper的所有操作，只是方法名不同罢了，IService的方法比BaseMapper多
    //自定义的方法需要在xml中配置
    List<User> getUsers();
}
