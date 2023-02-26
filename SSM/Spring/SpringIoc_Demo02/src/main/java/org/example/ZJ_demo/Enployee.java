package org.example.ZJ_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Enployee {
    @Value("P J")
    private String username;
    @Value("胖橘")
    private String realname;
    @Value("12345678")
    private String password;
    @Value("30954@qq.com")
    private String email;
    Department department;
    @Autowired
    public Enployee(Department department){
        this.department=department;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Department getDepartment() {
        return department;
    }
}
