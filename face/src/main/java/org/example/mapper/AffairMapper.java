package org.example.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.example.pojo.Affair;
import org.example.pojo.Affair_Meeting;

import java.util.List;

public interface AffairMapper {
    @InsertProvider(type = AffairSql.class,method = "insertAffair")
    int insertAffair(Affair affair);

//    根据手机号 查找 会议信息
    @SelectProvider(type = AffairSql.class,method = "getAffairByPhone")
    List<Affair_Meeting> getAffairByPhone(String phone);

    //更改签到状态
    @UpdateProvider(type = AffairSql.class,method = "updateSignInState")
    int updateSignInState(String meeting_num,String phone);

//    根据手机号 查找 会议事务表的信息
    @SelectProvider(type = AffairSql.class,method = "selectaffairbyphone")
    List<Affair> selectaffairbyphone(String meeting_num);
}
