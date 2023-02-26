package com.pj.springbootmybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author : Pj-Xk
 * @date : 2023-02-17 11:03
 **/
@Data//组合注解（包含自动添加get、set、toString等方法）
//@Builder//用来生成对象，并且可以为对象链式赋值（即一句语句给对象的属性赋完值）
@TableName(value = "t_user")//将t_user数据库表与User关联
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String photo;
}
