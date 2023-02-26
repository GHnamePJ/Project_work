package org.example.pojo;

public class user{
    private String Unum;
    private String uname;
    private String rname;
    private String password;

    public String getUnum() {
        return Unum;
    }

    public void setUnum(String unum) {
        Unum = unum;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "Unum='" + Unum + '\'' +
                ", uname='" + uname + '\'' +
                ", rname='" + rname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
