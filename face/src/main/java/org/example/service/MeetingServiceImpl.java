package org.example.service;

import org.example.mapper.MeetingMapper;
import org.example.pojo.Affair;
import org.example.pojo.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MeetingServiceImpl implements MeetingService{
    MeetingMapper meetingMapper;
    @Autowired
    public void setMeetingMapper(MeetingMapper meetingMapper){
        this.meetingMapper = meetingMapper;
    }
    @Override
    public List<Meeting> getMeetByPhone(String phone) {
        List<Meeting> meetingList = meetingMapper.getMeetByPhone(phone);
        return meetingList;
    }

    @Override
    public List<Meeting> getMeetByNum(String meeting_num) {
        List<Meeting> meetingList = meetingMapper.getMeetByNum(meeting_num);
        return meetingList;
    }

    @Override
    public int updateMeet(Meeting meeting) {
        int count = meetingMapper.updateMeet(meeting);
        return count;
    }

    @Override
    public int deleteMeet(String meeting_num) {
        int count = meetingMapper.deleteMeet(meeting_num);
        return count;
    }



    @Override
    public Meeting getMeetingNum(String meetingNum) {
        Meeting meeting=meetingMapper.getMeetingNum(meetingNum);
        return meeting;
    }

    @Override
    public int insertMeeting(Meeting meeting) {
        int rs=meetingMapper.insertMeeting(meeting);
        return rs;
    }
}
