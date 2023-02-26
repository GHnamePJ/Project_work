package org.example.ZJ_demo;

public class Use{
    Enployee enployee;

    public Use(Enployee enployee) {
        this.enployee = enployee;
    }

    public void printInfo(){
        System.out.println("员工姓名："+enployee.getUsername()+"\n所属部门："+enployee.department.getDepartment_name());
    }

}
