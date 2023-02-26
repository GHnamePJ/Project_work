package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.ServerCostMapper;
import org.example.pojo.ServerCost;

import java.util.List;

public class ServerCostServiceImpl implements ServerCostService{
    private SqlSessionFactory sqlSessionFactory;
    public ServerCostServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    @Override
    public List<ServerCost> getServerCostByName(){
        SqlSession sqlSession=this.sqlSessionFactory.openSession();
        ServerCostMapper serverCostMapper=sqlSession.getMapper(ServerCostMapper.class);
        List<ServerCost> serverCost=serverCostMapper.getServerCostByName();
        sqlSession.commit();
        sqlSession.close();
        return serverCost;
    }
}
