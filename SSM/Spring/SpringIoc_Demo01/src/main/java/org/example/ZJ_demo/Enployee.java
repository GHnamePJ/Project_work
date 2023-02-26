package org.example.ZJ_demo;

public class Enployee {
    private String username;
    private String realname;
    private String password;
    private String email;
    Department department;

    public Enployee(String username, String realname, String password, String email, Department department) {
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.email = email;
        this.department = department;
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
