package com.pj.springbootmybatis.mapper;

import com.pj.springbootmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserXmlMapper {
    public List<User> FindAll();
}
