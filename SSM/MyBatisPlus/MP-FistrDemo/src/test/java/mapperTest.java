import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;
import service.UserService;
import service.UserServiceImpl;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2023-02-19 14:38
 **/
public class mapperTest {
    @Test
    public void MapperTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContent.xml");
        UserService userService  = applicationContext.getBean(UserServiceImpl.class);
        List<User> users = userService.getUsers();
        System.out.println(users);
    }
}
