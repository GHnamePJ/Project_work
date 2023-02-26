package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Timestamp;

public class Affair {
    private String meeting_num;
    private String meeting_name;
    private String phone;
    private String name;
    private String sign_in_way;//签到方式
    private Timestamp sign_in_time;//签到时间
    private String sign_in_state;//签到状态
    private int role;

    public String getMeeting_num() {
        return meeting_num;
    }

    public void setMeeting_num(String meeting_num) {
        this.meeting_num = meeting_num;
    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign_in_way() {
        return sign_in_way;
    }

    public void setSign_in_way(String sign_in_way) {
        this.sign_in_way = sign_in_way;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "GMT+8")
    public Timestamp getSign_in_time() {
        return sign_in_time;
    }

    public void setSign_in_time(Timestamp sign_in_time) {
        this.sign_in_time = sign_in_time;
    }

    public String getSign_in_state() {
        return sign_in_state;
    }

    public void setSign_in_state(String sign_in_state) {
        this.sign_in_state = sign_in_state;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
