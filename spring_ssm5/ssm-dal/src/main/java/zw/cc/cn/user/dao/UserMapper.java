package zw.cc.cn.user.dao;

import org.springframework.stereotype.Repository;
import zw.cc.cn.user.module.User;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUmAndPwd(User user);
}