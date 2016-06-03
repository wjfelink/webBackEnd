package cn.ctyun.standdemo.service;

import cn.ctyun.core.base.bo.PageBO;
import cn.ctyun.standdemo.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 16-5-19
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public interface UserServiceI {


    public User getUserById(String id);

    PageBO<User> getAll();

    List<User> getAll2();

    List<User> getAll3();

    void userTransfer();

    List<User> selectUserRole();

}
