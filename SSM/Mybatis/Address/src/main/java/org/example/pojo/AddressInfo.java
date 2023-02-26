package org.example.pojo;

public class AddressInfo {
    int id;
    String streetName;
    String zipcode;
    int userInfoId;
    UserInfo user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
//                "id=" + id +
                ", streetName='" + streetName + '\'' +
//                ", zipcode='" + zipcode + '\'' +
                ", userInfoId=" + userInfoId +
                ", user=" + user +
                '}'+"\n";
    }
}
