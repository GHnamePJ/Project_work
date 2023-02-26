package org.example.ZJ_demo;

public class Department {
    private String department_num;
    private String department_name;
    public Department(String department_num,String department_name){
        this.department_num=department_num;
        this.department_name=department_name;
    }

    public String getDepartment_num() {
        return department_num;
    }

    public String getDepartment_name() {
        return department_name;
    }
}
