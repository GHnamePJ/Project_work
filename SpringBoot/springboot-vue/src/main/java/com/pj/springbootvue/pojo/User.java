package com.pj.springbootvue.pojo;

/**
 * @author : Pj-Xk
 * @date : 2022-10-13 19:36
 **/
public class User {
    private int id;
    private String username;
    private String password;
    private String newpassword;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
