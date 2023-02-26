package org.example.controller;

import org.example.http.Results;
import org.example.mapper.AffairMapper;
import org.example.pojo.Affair;
import org.example.pojo.Affair_Meeting;
import org.example.service.AffairService;
import org.example.service.MeetingService;
import org.example.utils.ExcelListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller//声明当前类为一个bean
@RequestMapping("/Affair")
@ResponseBody
public class AffairController {
    AffairService affairService;

    @Autowired
    public void setAffairService(AffairService affairService) {
        this.affairService=affairService;
    }

    @RequestMapping("/insertAffair")
    @ResponseBody
    public Results insertAffair(HttpServletRequest request, String meeting_num,String name){
//    public Results insertAffair(HttpServletRequest request,String meeting_num,String phone,String name){
        Affair affair = new Affair();
        String phone = request.getHeader("phone");
        affair.setPhone(phone);
//        String name = request.getHeader("name");
//        System.out.println(name + "测试");
        affair.setName(name);
        affair.setRole(0);
        System.out.println("g====ukkk"+meeting_num);
        affair.setMeeting_num(meeting_num);


        int count = affairService.insertAffair(affair);
        if (count == 1){
            return new Results(true,"插入成功",affair);
        }else {
            return new Results(false,"插入失败");
        }
    }

    @RequestMapping("/lookaffair")
    @ResponseBody
    public Results getAffairByPhone(HttpServletRequest request){
        String phone = request.getHeader("phone");
        System.out.println("headers=="+phone);
        List<Affair_Meeting> affairList = affairService.getAffairByPhone(phone);
        System.out.println("affairList="+affairList);
        if (affairList!=null){
            return new Results(true,"查到数据",affairList);
        }else {
            return new Results(false,"无数据");
        }

    }
    //更改签到状态
    @RequestMapping("/signInState")
    @ResponseBody
    public Results signIn(HttpServletRequest request,String meeting_num){
        System.out.println(meeting_num);
        String phone=request.getHeader("phone");
        if(meeting_num!=null&&phone!=null){
            int rs=affairService.updateSignInState(meeting_num,phone);
            if(rs>0){
                return new Results(true,"更改签到状态成功");
            }else {
                return new Results(false,"更改签到状态失败");
            }
        }else{
            return new Results(false,"无数据");
        }
    }
    //在face_affair里插入一条role为1的数据
    @RequestMapping("/insertByApply")
    @ResponseBody
    public Results insertByApply(HttpServletRequest request, String meeting_num,String name){
        Affair affair = new Affair();
        String phone = request.getHeader("phone");
        affair.setPhone(phone);
        affair.setName(name);
        affair.setRole(1);
        System.out.println("申请的会议号"+meeting_num);
        affair.setMeeting_num(meeting_num);
        int count = affairService.insertAffair(affair);
        if (count == 1){
            return new Results(true,"插入成功",affair);
        }else {
            return new Results(false,"插入失败");
        }
    }

    //    查看 affir 信息
    @RequestMapping("selectaffairbyphone")
    public Results selectaffairbyphone( String meeting_num) {
        List<Affair> affairList = affairService.selectaffairbyphone(meeting_num);
        for (Affair a: affairList) {
            if (a.getSign_in_state().equals("0")){
                a.setSign_in_state("未签到");
            }
            else if(a.getSign_in_state().equals("1")){
                a.setSign_in_state("已签到");
            }
        }
        try {
            if (affairList != null){
                String filepath = ExcelListUtil.ExcelListUtil(affairList);
                return new Results<>(true,"查找成功",filepath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Results<>(false,"查找失败");
    }
}
