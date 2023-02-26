package cn.mybatis.dynamic.relation.mapper;

import cn.mybatis.dynamic.relation.po.EmployeeInfo;

public interface EmployeeMapper {
    public EmployeeInfo getEmployeeById(int id);
}
