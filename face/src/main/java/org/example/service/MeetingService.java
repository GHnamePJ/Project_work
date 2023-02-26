package org.example.service;

import org.example.pojo.Affair;
import org.example.pojo.Meeting;

import java.util.List;

public interface MeetingService {
    List<Meeting> getMeetByPhone(String phone);

    List<Meeting> getMeetByNum(String meeting_num);

    int updateMeet(Meeting meeting);

    int deleteMeet(String meeting_num);

    Meeting getMeetingNum(String meetingNum);

    int insertMeeting(Meeting meeting);

}
