package cn.mybatis.dynamic.relation.mapper;

import cn.mybatis.dynamic.relation.po.LoginAccount;

import java.util.List;

public interface LoginAccountMapper {
	List<LoginAccount> getAccount();
}
