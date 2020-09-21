package zw.cc.cn.service;


import zw.cc.cn.user.module.User;
import zw.cc.cn.user.module.UserRole;

import java.util.List;

/**
 * Created by wiggins on 2016/7/11.
 */
public interface UserService {

    User validUser(User user);

    List<String> queryUserRole(Integer userId);

    List<String> queryUserPermiss(Integer userId);

}
