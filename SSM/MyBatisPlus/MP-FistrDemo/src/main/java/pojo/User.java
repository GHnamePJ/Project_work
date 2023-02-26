package pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : Pj-Xk
 * @date : 2023-02-19 14:12
 **/
@Data
@TableName(value = "t_user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String photo;
}
