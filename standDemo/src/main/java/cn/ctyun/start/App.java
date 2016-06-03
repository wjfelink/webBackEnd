package cn.ctyun.start;

import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.ctyun.standdemo.model.User;
import cn.ctyun.standdemo.service.UserServiceI;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 16-5-19
 * Time: 下午7:32
 * To change this template use File | Settings | File Templates.
 */
public class App {

    public static void main(String args[]) throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
        UserServiceI userService = (UserServiceI) ac.getBean("userService");
        User u = userService.getUserById("1");
        System.out.print(JSON.toJSONString(u));
    }



}
