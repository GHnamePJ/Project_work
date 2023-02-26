package org.example.XML_demo;

public class Use {
    Student student;
    public void setStudent(Student student){
        this.student=student;
    }
    public void print(){
        System.out.println("姓名："+student.getName()+"\n所在地："+student.getAddress());
    }
}
