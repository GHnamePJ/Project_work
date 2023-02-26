package cn.mybatis.dynamic.annosql.mapper;


import cn.mybatis.dynamic.annosql.po.Employees;
import cn.mybatis.dynamic.annosql.po.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    @SelectProvider(type = EmployeeSql.class, method = "getEmployeeByIdSql")
    Employees getEmployeeById(int id);

    @SelectProvider(type = EmployeeSql.class, method = "getEmployeesByParamsSql_1")
    List<Employees> getEmployeesByParams_1(Map<Object, Object> params);

    @InsertProvider(type = EmployeeSql.class, method = "insertEmployeesSql")
    void insertEmployees(Employees employees);

    @UpdateProvider(type= EmployeeSql.class, method = "updateEmployeesSql")
    void updateEmployee(Employees employees);

    @DeleteProvider(type = EmployeeSql.class, method = "deleteEmployeesSql")
    void deleteEmployees(int id);

}
