package org.example.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.example.pojo.Affair;
import org.example.pojo.Meeting;

public class MeetingSql {

    public String getMeetByPhone(final String phone) {
        return new SQL() {
            {
                SELECT("m.meeting_name","m.meeting_num","m.apply_name","m.start_time","m.end_time");
                FROM("face_user u");
                INNER_JOIN("face_meeting m on u.name=m.apply_name");
                WHERE("phone=#{phone}");
                if (phone!=null&&phone!=""){
                    WHERE("phone=#{phone}");
                }
            }
        }.toString();

    }

    public String getMeetByNum(final String meeting_num){
        return new SQL(){
            {
                SELECT("m.meeting_name","m.meeting_num","m.apply_name","m.start_time","m.end_time");
                FROM("face_user u");
                INNER_JOIN("face_meeting m on u.name=m.apply_name");
                WHERE("meeting_num=#{meeting_num}");
                if (meeting_num!=null&&meeting_num!="") {
                    WHERE("meeting_num=#{meeting_num}");
                }
            }
        }.toString();
    }

    public String updateMeet(final Meeting meeting){
        return new SQL(){
            {
                UPDATE("face_meeting");
                if (meeting.getMeeting_name() != null && !meeting.getMeeting_name().equals("")) {
                    SET("meeting_name=#{meeting_name}");
                }
                if (meeting.getStart_time() != null && !meeting.getStart_time().equals("")) {
                    SET("start_time=#{start_time}");
                }
                if (meeting.getEnd_time() != null && !meeting.getEnd_time().equals("")) {
                    SET("end_time=#{end_time}");
                }
                if (meeting.getMeeting_num() != null && meeting.getMeeting_num() !=""){
                    WHERE("meeting_num=#{meeting_num}");
                }
            }
        }.toString();
    }

    public String deleteMeet(final String meeting_num){
        return new SQL(){
            {
                DELETE_FROM("face_meeting");
                if (meeting_num!=null&&meeting_num!="") {
                    WHERE("meeting_num=#{meeting_num}");
                }
            }
        }.toString();
    }


    //判断会议号是否存在
    public String getMeetingNum(final String meetingNum){
        return new SQL(){
            {
                SELECT("*");
                FROM("face_meeting");
                if(meetingNum!=null&&meetingNum!=""){
                    WHERE("meeting_num=#{meeting_num}");
                }
            }
        }.toString();
    }
    //添加会议信息
    public String insertMeeting(final Meeting meeting){
        return new SQL(){
            {
                INSERT_INTO("face_meeting");
                if(meeting.getMeeting_num()!=null&&meeting.getMeeting_num()!=""){
                    VALUES("meeting_num","#{meeting_num}");
                }
                if(meeting.getMeeting_name()!=null&&meeting.getMeeting_name()!=""){
                    VALUES("meeting_name","#{meeting_name}");
                }
                if(meeting.getApply_name()!=null&&meeting.getApply_name()!=""){
                    VALUES("apply_name","#{apply_name}");
                }
                if(meeting.getStart_time()!=null){
                    VALUES("start_time","#{start_time}");
                }
                if(meeting.getEnd_time()!=null){
                    VALUES("end_time","#{end_time}");
                }
            }
        }.toString();
    }
}
