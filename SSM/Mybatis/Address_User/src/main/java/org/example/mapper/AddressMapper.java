package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.AddressInfo;

import java.util.List;

public interface AddressMapper {
    @Select("select * from t_address where userInfo_id=#{userInfo_id}")
    List<AddressInfo> getaddressById(@Param("userInfo_id") int id);
}
