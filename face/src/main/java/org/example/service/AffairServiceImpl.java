package org.example.service;

import org.example.mapper.AffairMapper;
import org.example.mapper.MeetingMapper;
import org.example.pojo.Affair;
import org.example.pojo.Affair_Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffairServiceImpl implements AffairService{
    AffairMapper affairMapper;
    @Autowired
    public void setAffairMapper(AffairMapper affairMapper){
        this.affairMapper = affairMapper;
    }

    @Override
    public int insertAffair(Affair affair) {
        int count = affairMapper.insertAffair(affair);
        return count;
    }

    @Override
    public List<Affair_Meeting> getAffairByPhone(String phone) {
        List<Affair_Meeting> affairList = affairMapper.getAffairByPhone(phone);
        return affairList;
    }
    //更改签到状态
    @Override
    public int updateSignInState(String meeting_num,String phone){
        int rs=affairMapper.updateSignInState(meeting_num,phone);
        return rs;
    }

    //    根据手机号 查找 会议事务表的信息
    @Override
    public List<Affair> selectaffairbyphone(String meeting_num) {
        List<Affair> affairList = affairMapper.selectaffairbyphone(meeting_num);
        return affairList;
    }

}
