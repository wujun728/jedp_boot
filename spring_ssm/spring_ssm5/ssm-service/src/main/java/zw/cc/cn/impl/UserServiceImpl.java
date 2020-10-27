package zw.cc.cn.impl;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.cc.cn.service.UserService;
import zw.cc.cn.user.dao.UserMapper;
import zw.cc.cn.user.dao.UserRoleMapper;
import zw.cc.cn.user.module.Role;
import zw.cc.cn.user.module.User;
import zw.cc.cn.user.module.UserRole;

import java.util.List;


/**
 * Created by wiggins on 2016/7/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User validUser(User user) {
        user = userMapper.selectByUmAndPwd(user);
        return user;
    }

    @Override
    public List<String> queryUserRole(Integer userId) {
        return userRoleMapper.queryUserRoleCodeByUserId(userId);
    }

    @Override
    public List<String> queryUserPermiss(Integer userId) {
        return userRoleMapper.queryUserPermissByUserId(userId);
    }
}
