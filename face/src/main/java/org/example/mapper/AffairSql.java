package org.example.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.example.pojo.Affair;

public class AffairSql {
    public String insertAffair(final Affair affair){
        return new SQL(){
            {
                INSERT_INTO("face_affair");
                if (affair.getMeeting_num() != null && affair.getMeeting_num() !=""){
                    VALUES("meeting_num","#{meeting_num}");
                }
                if (affair.getPhone() != null && affair.getPhone() !=""){
                    VALUES("phone","#{phone}");
                }
                if (affair.getName() != null && affair.getName() !=""){
                    VALUES("name","#{name}");
                }
                if (affair.getRole() >=0){
                    VALUES("role","#{role}");
                }


            }
        }.toString();
    }

    public String getAffairByPhone(final String phone){
        return new SQL(){
            {
                SELECT("a.meeting_num","m.meeting_name,a.role");
                FROM("face_affair a");
                INNER_JOIN("face_meeting m on a.meeting_num = m.meeting_num");
                WHERE("a.role = 0");
                if (phone!=null&&phone!="") {
                    WHERE("a.phone=#{phone}");
                }
            }
        }.toString();
    }
    //更改签到状态
    public String updateSignInState(final String meeting_num,final String phone){
        return new SQL(){
            {
                //UPDATE face_affair SET sign_in_state ="1",sign_in_time = now(),sign_in_way="人脸签到" WHERE meeting_num="58484238" and phone="13923913669"
                UPDATE("face_affair");
                if(meeting_num!=null&&meeting_num!=""&&phone!=null&&phone!=""){
                    SET("sign_in_state=1");
                }
                SET("sign_in_time = now()");
                SET("sign_in_way='人脸签到'");
                WHERE("meeting_num=#{arg0}");
                WHERE( "phone=#{arg1}");
            }
        }.toString();
    }

    //    根据手机号 查找 会议事务表的信息
    public String selectaffairbyphone(final String meeting_num){
        return new SQL(){
            {
                //SELECT * FROM face_affair WHERE meeting_num = "93572407"
                SELECT("*");
                FROM("face_affair");
                if (meeting_num!=null&&meeting_num != "") {
                    WHERE(" meeting_num = #{phone}");
                }
                WHERE(" role = 0");
            }
        }.toString();
    }
}
