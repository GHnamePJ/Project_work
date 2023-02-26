package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.AddressInfo;

import java.util.List;

public interface AddressMapper {
    List<AddressInfo> getaddressList_choose(@Param("streetName")String streetName, @Param("zipcode")String zipcode);
    int insertAddress(AddressInfo addressInfo);
}
