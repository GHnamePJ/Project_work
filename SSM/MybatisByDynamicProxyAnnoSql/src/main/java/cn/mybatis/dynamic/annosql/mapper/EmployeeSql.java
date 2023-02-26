package cn.mybatis.dynamic.annosql.mapper;

import cn.mybatis.dynamic.annosql.po.Employees;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class EmployeeSql {
    public String getEmployeeByIdSql(final Integer id){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_employee");
                if(id!=null){
                    WHERE("id=#{id}");
                }
            }
        }.toString();
    }

    public String getEmployeesByParamsSql_1(final Map<Object, Object> map){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_employee");
                if(map.get("id")!=null){
                    WHERE("id=#{id}");
                }
                if(map.get("empName")!=null){
                    WHERE("empName=#{empName}");
                }

                WHERE("id=#{id} or empName=#{empName}");
            }
        }.toString();

//        Map<Object, Object> map = new HashMap<Object, Object>();
//        map.put("id", 1);
//        map.pust("empName", "aaaa");
    }

    public String insertEmployeesSql(final Employees employees){
        return  new SQL(){
            {
                INSERT_INTO("t_employee");
                if(employees.getEmpName()!=null && !employees.getEmpName().equals("")){
                    VALUES("empName", "#{empName}");
                }
            }
        }.toString();

    }

    public String updateEmployeesSql(final Employees employees){
        return  new SQL(){
            {
                UPDATE("t_employee");
                if(employees.getEmpName() != null && !employees.getEmpName().equals("")) {
                    SET("empName=#{empName}");
                }
                WHERE("id=#{id}");
            }
        }.toString();

    }

    public String deleteEmployeesSql(final Integer id){
        return new SQL(){
            {
                DELETE_FROM("t_employee");
                if(id!=null) {
                    WHERE("id=#{id}");
                }
            }
        }.toString();
    }
}
