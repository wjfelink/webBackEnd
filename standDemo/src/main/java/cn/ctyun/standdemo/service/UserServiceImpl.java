package cn.ctyun.standdemo.service;

import cn.ctyun.core.base.bo.PageBO;
import cn.ctyun.core.base.dao.executor.pagination.PageParam;
import cn.ctyun.core.base.util.UUIDUtils;
import cn.ctyun.standdemo.dao.UserRoleMapper;
import cn.ctyun.standdemo.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ctyun.standdemo.dao.UserMapper;
import cn.ctyun.standdemo.model.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.File;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private UserMapper userMapper;

    public UserRoleMapper getUserRoleMapper() {
        return userRoleMapper;
    }

    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    private UserRoleMapper userRoleMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED)
    public void userTransfer(){
        User user=new User();
        user.setUserid(UUIDUtils.getUUID());
        user.setAge(10);

        userMapper.insert(user);
       // logger.info()
        UserRole userRole=new UserRole();
        userRole.setUserId(UUIDUtils.getUUID());
        //userRole.setUserid(user.getId());
        //throw new RuntimeException();
        userRoleMapper.insert(userRole);
    }


	@Override
	public User getUserById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

    @Override
    public PageBO<User> getAll() {
        PageParam pageParam=new PageParam();
        pageParam.setPageNo(1);
        pageParam.setPageSize(2);
        return userMapper.selectByParam(pageParam,"1");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> getAll2() {
        return userMapper.getAll2("1");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> getAll3() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> selectUserRole(){
        return userMapper.selectUserRole();
    }
}
