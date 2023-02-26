package cn.mybatis.dynamic.proxy.mapper;

import cn.mybatis.dynamic.proxy.po.Employees;
import org.apache.ibatis.annotations.Update;

public interface EmployeeMapper {
    void updateEmployee(Employees employees);
}
