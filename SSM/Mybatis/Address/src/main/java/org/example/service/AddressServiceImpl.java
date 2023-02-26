package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.AddressMapper;
import org.example.pojo.AddressInfo;

import java.util.List;

public class AddressServiceImpl implements AddressService{
    private SqlSessionFactory sqlSessionFactory;
    public AddressServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    @Override
    public List<AddressInfo> getaddressList_choose(@Param("streetName")String streetName, @Param("zipcode")String zipcode){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        AddressMapper addressMapper=sqlSession.getMapper(AddressMapper.class);
        List<AddressInfo> addressInfo=addressMapper.getaddressList_choose(streetName,zipcode);
        sqlSession.commit();
        sqlSession.close();
        return addressInfo;
    }
    public int insertAddress(AddressInfo addressInfo){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        AddressMapper addressMapper=sqlSession.getMapper(AddressMapper.class);
        int rs=addressMapper.insertAddress(addressInfo);
        sqlSession.commit();
        sqlSession.close();
        return rs;
    }
}
