package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.AddressInfo;

import java.util.List;

public interface AddressService {
    List<AddressInfo> getaddressList_choose(@Param("streetName")String streetName, @Param("zipcode")String zipcode);
    int insertAddress(AddressInfo addressInfo);
}
