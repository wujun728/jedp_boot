package zw.cc.cn.user.dao;

import org.springframework.stereotype.Repository;
import zw.cc.cn.user.module.User;
import zw.cc.cn.user.module.UserRole;

import java.util.List;

@Repository
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<String> queryUserRoleCodeByUserId(Integer userId);

    List<String> queryUserPermissByUserId(Integer userId);
}