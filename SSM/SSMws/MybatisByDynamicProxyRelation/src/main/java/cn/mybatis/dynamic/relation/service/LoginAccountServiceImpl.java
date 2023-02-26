package cn.mybatis.dynamic.relation.service;

import java.util.List;

import cn.mybatis.dynamic.relation.mapper.LoginAccountMapper;
import cn.mybatis.dynamic.relation.po.LoginAccount;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;



public class LoginAccountServiceImpl implements LoginAccountService{
	private SqlSessionFactory sqlSessionFactory;
	
	public LoginAccountServiceImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<LoginAccount> getAccount() {
		SqlSession session = sqlSessionFactory.openSession();
		LoginAccountMapper loginAccountMapper = session.getMapper(LoginAccountMapper.class);
		List<LoginAccount> loginAccount = loginAccountMapper.getAccount();
		session.close();
		return loginAccount;
	}
}
