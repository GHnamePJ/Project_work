package cn.mybatis.dynamic.relation.service;

import cn.mybatis.dynamic.relation.po.Orders;

import java.util.List;


public interface OrdersService {
	List<Orders> getOrder(int id);
}
