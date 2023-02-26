package org.example.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.example.pojo.Affair;
import org.example.pojo.Meeting;

import java.util.List;

public interface MeetingMapper {
    @SelectProvider(type = MeetingSql.class,method = "getMeetByPhone")
    List<Meeting> getMeetByPhone(String phone);

    @SelectProvider(type = MeetingSql.class,method = "getMeetByNum")
    List<Meeting> getMeetByNum(String meeting_num);

    @UpdateProvider(type = MeetingSql.class,method = "updateMeet")
    int updateMeet(Meeting meeting);

    @DeleteProvider(type = MeetingSql.class,method = "deleteMeet")
    int deleteMeet(String meeting_num);

    @SelectProvider(type = MeetingSql.class,method = "getMeetingNum")
    Meeting getMeetingNum(String meetingNum);//根据生成的会议号判断会议号是否唯一

    @InsertProvider(type = MeetingSql.class,method = "insertMeeting")
    int insertMeeting(Meeting meeting);//插入一条会议信息
}
