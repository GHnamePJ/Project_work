import org.example.pojo.Affair;
import org.example.utils.ExcelListUtil;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class test {
    @Test
    public void excle() throws Exception {
        List<Affair> affairList = new ArrayList<Affair>();
        Affair affair = new Affair();
        affair.setName("名");
        affair.setMeeting_num("3665");
        affair.setPhone("56");
        affair.setRole(1);
        affair.setSign_in_way("手动");
        affair.setMeeting_name("msdf");
        affairList.add(affair);

        Affair affair1 = new Affair();
        affair1.setName("名");
        affair1.setMeeting_num("35242");
        affair1.setPhone("56");
        affair1.setRole(1);
        affair1.setSign_in_way("手动");
        affair1.setMeeting_name("msdf");
        affairList.add(affair1);

//        调用
        String path = ExcelListUtil.ExcelListUtil(affairList);
        System.out.println(path);
    }
}
