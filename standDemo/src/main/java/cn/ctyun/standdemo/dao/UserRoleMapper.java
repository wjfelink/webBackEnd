package cn.ctyun.standdemo.dao;

import cn.ctyun.core.base.dao.annotation.MybatisMapperEx;
import cn.ctyun.standdemo.model.UserRole;
import org.springframework.stereotype.Component;

@Component("userRoleMapper")
@MybatisMapperEx
public interface UserRoleMapper {
    int deleteByPrimaryKey(String userRoleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String userRoleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}