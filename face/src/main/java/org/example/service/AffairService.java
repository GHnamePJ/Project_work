package org.example.service;

import org.example.pojo.Affair;
import org.example.pojo.Affair_Meeting;

import java.util.List;

public interface AffairService {
    int insertAffair(Affair affair);

    List<Affair_Meeting> getAffairByPhone(String phone);
    //更改签到状态
    int updateSignInState(String meeting_num,String phone);

    //    根据手机号 查找 会议事务表的信息
    List<Affair> selectaffairbyphone(String meeting_num);
}
