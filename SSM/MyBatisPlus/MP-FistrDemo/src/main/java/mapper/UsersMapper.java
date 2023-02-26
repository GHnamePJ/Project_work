package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pojo.User;

import java.util.List;

public interface UsersMapper extends BaseMapper<User> {//包含了基础的增删改查等
//    自定义的方法需要在xml中配置
    List<User> getUsers();
}
