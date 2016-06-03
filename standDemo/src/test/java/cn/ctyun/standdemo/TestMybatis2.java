package cn.ctyun.standdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.ctyun.standdemo.model.User;
import cn.ctyun.standdemo.service.UserServiceI;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 16-5-19
 * Time: 下午5:22
 * To change this template use File | Settings | File Templates.
 */
public class TestMybatis2 {

    @Test
    public void test1(){
        ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
        UserServiceI userService=(UserServiceI)ac.getBean("userService");
        User u=userService.getUserById("1");
        System.out.print(u);
    }
}
