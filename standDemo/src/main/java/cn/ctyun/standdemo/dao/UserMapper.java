package cn.ctyun.standdemo.dao;

import cn.ctyun.core.base.bo.PageBO;
import cn.ctyun.core.base.dao.annotation.MybatisMapperEx;
import cn.ctyun.core.base.dao.executor.pagination.PageParam;
import cn.ctyun.core.base.dao.executor.pagination.QueryPage;
import cn.ctyun.standdemo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userMapper")
@MybatisMapperEx
public interface UserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> getAll2(String userid);

    List<User> selectUserRole();

    //通过参数查询不同的用户组
    @QueryPage
    public PageBO<User> selectByParam(PageParam page, @Param(value = "id")String id);
}