package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.pojo.AddressInfo;
import org.example.pojo.UserInfo;
import org.example.service.AddressService;
import org.example.service.AddressServiceImpl;
import org.example.service.UserInfoServiceImpl;
import org.example.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AppTest
{
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sqlSessionFactory=sqlSessionFactoryBuilder.build(in);
    }
    @Test
    public void testgetaddressList_choose(){
        String streetName="南";
        String zipcode="2";
        AddressService addressService=new AddressServiceImpl(this.sqlSessionFactory);
        List<AddressInfo> addressInfo=addressService.getaddressList_choose(streetName,zipcode);
        for (AddressInfo addressInfo1 :addressInfo){
            System.out.println(addressInfo1);
        }
    }
    @Test
    public void testinsertAddress(){
        AddressInfo addressInfo=new AddressInfo();
        addressInfo.setStreetName("广东轻工职业技术学院");
        addressInfo.setZipcode("338900");
        AddressService addressService=new AddressServiceImpl(this.sqlSessionFactory);
        int rs=addressService.insertAddress(addressInfo);
        System.out.println(rs);
    }
    @Test
    public void testgetUser_address(){
        int id=1;
        UserService userService=new UserInfoServiceImpl(this.sqlSessionFactory);
        List<UserInfo> userInfo=userService.getUser_address(id);
                System.out.println(userInfo);
    }
}
