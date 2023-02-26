package org.example.controller;

import org.example.http.Results;
import org.example.pojo.Affair;
import org.example.pojo.Meeting;
import org.example.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
    MeetingService meetingService;

    @Autowired
    public void setMeetingService(MeetingService meetingService) {
        this.meetingService=meetingService;
    }


    @RequestMapping("/lookmeet")
    @ResponseBody
    public Results getMeetByPhone(HttpServletRequest request){
        String phone = request.getHeader("phone");
        System.out.println("headers=="+phone);

        List<Meeting> list = meetingService.getMeetByPhone(phone);
        System.out.println("list="+list);
        if (list!=null){
            return new Results(true,"查到数据",list);
        }else {
            return new Results(false,"无数据");
        }

    }

    @RequestMapping("/meetdetails")
    @ResponseBody
    public Results getMeetByNum(String meeting_num){
        List<Meeting> list = meetingService.getMeetByNum(meeting_num);
        if (list!=null){
            return new Results(true,"查到数据",list);
        }else {
            return new Results(false,"无数据");
        }
    }

    @RequestMapping("/updatemeet")
    @ResponseBody
    public Results updateMeet(@RequestBody Meeting meeting){
        int count = meetingService.updateMeet(meeting);
        if (count == 1){
            return new Results(true,"修改成功",meeting);
        }else {
            return new Results(false,"修改失败");
        }
    }

    @RequestMapping("/deleteMeet")
    @ResponseBody
    public Results deleteMeet(String meeting_num){
        int count = meetingService.deleteMeet(meeting_num);
        if (count == 1){
            return new Results(true,"删除成功");
        }else {
            return new Results(false,"删除失败");
        }
    }

    @RequestMapping("/apply")
    @ResponseBody
    public Results apply(@RequestBody Meeting meeting){
        System.out.println(meeting);
        try {
            int rs=meetingService.insertMeeting(meeting);
            if(rs>0){
                return new Results(true,"添加了一条会议");
            }else{
                return new Results(false,"会议插入失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Results(false,"遇到异常");
        }
    }
}
