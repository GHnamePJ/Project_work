package org.example.XML_demo;

public class Use {
    EmployeeInfo employeeInfo;
    public void setEmployeeInfo(EmployeeInfo employeeInfo){
        this.employeeInfo=employeeInfo;
    }
    public void print(){
        System.out.println("用户名："+employeeInfo.getUserName()+"\n所属部门："+employeeInfo.departmentInfo.getDepartmentName());
    }
}
