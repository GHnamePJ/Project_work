package org.example.controller;

import org.example.http.Results;
import org.example.pojo.Meeting;
import org.example.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("/Random")
public class RandomMeetingController {
    MeetingService meetingService;
    @Autowired
    public void setMeetingService(MeetingService meetingService){
        this.meetingService=meetingService;
    }
    //随机生成八位会议码
    @RequestMapping("/Meet")
    @ResponseBody
    public Results RandomMeeting(){
        Random random = new Random();
        String meetingNum="";
        Meeting meeting;
        for(int i=0;i<=7;i++){
            random.nextInt(10);
            meetingNum+=String.valueOf(random.nextInt(10));
        }
        //判断会议号是否唯一
        meeting=meetingService.getMeetingNum(meetingNum);
        while (meeting!=null){
            meetingNum="";
            for(int i=0;i<=7;i++){
                random.nextInt(10);
                meetingNum+=String.valueOf(random.nextInt(10));
            }
            meeting=meetingService.getMeetingNum(meetingNum);
        }
        return new Results(true,"会议号唯一",meetingNum);
    }
}
