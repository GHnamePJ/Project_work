package org.example.XML_demo;

public class StudentFactory {
    public static Student getStudent(String name,String sex){
        //返回一个实例
        return new Student(name,sex);
    }
}
