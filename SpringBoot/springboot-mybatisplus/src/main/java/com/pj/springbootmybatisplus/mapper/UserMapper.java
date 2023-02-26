package com.pj.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pj.springbootmybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper//表明这是一个mapper
//继承BeanMapper接口后，不用写mapper.xml文件即可获得CRUD功能
public interface UserMapper extends BaseMapper<User> {
//    自定义的方法需要自己写sql语句（可用注解）
}
