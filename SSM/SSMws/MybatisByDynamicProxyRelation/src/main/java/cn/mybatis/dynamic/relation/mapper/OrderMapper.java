package cn.mybatis.dynamic.relation.mapper;

import cn.mybatis.dynamic.relation.po.Orders;

import java.util.List;

public interface OrderMapper {
	List<Orders> getOrderById(int id);
}
