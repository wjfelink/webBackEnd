package cn.ctyun.standdemo.dao;

import cn.ctyun.core.base.dao.annotation.MybatisMapperEx;
import cn.ctyun.standdemo.model.Role;
import org.springframework.stereotype.Component;

@Component("roleMapper")
@MybatisMapperEx
public interface RoleMapper {
    int deleteByPrimaryKey(String roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}