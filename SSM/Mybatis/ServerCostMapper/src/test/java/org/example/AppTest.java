package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.pojo.ServerCost;
import org.example.service.ServerCostService;
import org.example.service.ServerCostServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        InputStream in= Resources.getResourceAsStream("mybaties-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sqlSessionFactory=sqlSessionFactoryBuilder.build(in);
    }
    @Test
    public void testgetServerCostByName(){
        ServerCostService serverCostService=new ServerCostServiceImpl(this.sqlSessionFactory);
        List<ServerCost> serverCost=serverCostService.getServerCostByName();
        for(ServerCost i:serverCost){
            System.out.println(i);
        }
    }


}