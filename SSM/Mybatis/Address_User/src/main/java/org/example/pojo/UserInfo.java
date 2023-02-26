package org.example.pojo;

import java.util.List;

public class UserInfo {
    int id;
    String loginName;
    String password;
    String realName;
//    在一里面创建一个多的类集合对象
    List<AddressInfo> addressInfoList;

    public List<AddressInfo> getAddressInfoList() {
        return addressInfoList;
    }

    public void setAddressInfoList(List<AddressInfo> addressInfoList) {
        this.addressInfoList = addressInfoList;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", addressInfoList='" + addressInfoList + '\'' +
                '}';
    }
}
