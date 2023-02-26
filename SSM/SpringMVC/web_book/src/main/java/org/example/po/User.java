package org.example.po;

public class User {
    private String Unum;
    private String Uname;
    private String Rname;
    private String password;

    public String getUnum() {
        return Unum;
    }

    public void setUnum(String unum) {
        Unum = unum;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getRname() {
        return Rname;
    }

    public void setRname(String rname) {
        Rname = rname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Unum='" + Unum + '\'' +
                ", Uname='" + Uname + '\'' +
                ", Rname='" + Rname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
