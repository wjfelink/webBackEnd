package cn.ctyun.standdemo;

import cn.ctyun.core.base.bo.PageBO;
import cn.ctyun.standdemo.service.UserServiceImpl;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.ctyun.standdemo.model.User;
import cn.ctyun.standdemo.service.UserServiceI;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 16-5-19
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class TestMybatis {

    private static final Logger logger = Logger.getLogger(TestMybatis.class);

    @Resource(name = "userService")
    private UserServiceI userService;

    @Test
    public void test1() {
        User u = userService.getUserById("1");
        logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testTransaction(){
        PageBO<User> users=userService.getAll();

        //User user=userService.getUserById("1");
        System.out.println(JSON.toJSONString(users));
    }

    @Test
    public void testUserRole(){
        List<User> users=userService.selectUserRole();

        //User user=userService.getUserById("1");
        System.out.println(JSON.toJSONString(users));
    }
}
