package cn.mybatis.dynamic.proxy.service;

import cn.mybatis.dynamic.proxy.mapper.EmployeeMapper;
import cn.mybatis.dynamic.proxy.mapper.UserInfoMapper;
import cn.mybatis.dynamic.proxy.po.Employees;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class EmployeeServiceImpl implements EmployeeService{
    private SqlSessionFactory sqlSessionFactory;

    public EmployeeServiceImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }


    @Override
    public void updateEmployee(Employees employees) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        employeeMapper.updateEmployee(employees);
        sqlSession.commit();
        sqlSession.close();
    }
}
