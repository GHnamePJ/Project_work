package com.pj.springbootvue.mapper;

import com.pj.springbootvue.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-13 19:37
 **/
@Mapper
public interface UserMapper {
    //查询角色
    @Select("select * from t_user")
    public List<User> FindAll();
    
}
