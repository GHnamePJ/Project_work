package cn.mybatis.dynamic.relation.service;

import cn.mybatis.dynamic.relation.mapper.OrderMapper;
import cn.mybatis.dynamic.relation.po.Orders;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


public class OrdersServiceImpl implements OrdersService{
	private SqlSessionFactory sqlSessionFactory;
	
	public OrdersServiceImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Orders> getOrder(int id) {
		SqlSession session = sqlSessionFactory.openSession();
		OrderMapper userMapper = session.getMapper(OrderMapper.class);
		List<Orders> orders = userMapper.getOrderById(id);
		session.close();
		return orders;
	}

}
