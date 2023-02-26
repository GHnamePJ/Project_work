package org.example;

import org.springframework.stereotype.Component;

@Component
public class UserImpl implements IUser {
    @Override
    public void userInfo() {
        System.out.println("用户名：小李，密码：123456"+"\n用户名：🐟， 密码：3545645343");
    }
}
