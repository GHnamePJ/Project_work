package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;

public class Meeting {
    private int meeting_id;
    private String meeting_num;
    private String meeting_name;
    private String apply_name;
    private Timestamp start_time;//会议开始时间
    private Timestamp end_time;//会议结束时间

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }

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


    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm",timezone = "GMT+8")
    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm",timezone = "GMT+8")
    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }
}
