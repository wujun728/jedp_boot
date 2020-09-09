package com.BBS.service;

import com.BBS.pojo.User;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dyl on 2018/7/19.
 */
public interface UserService {

    User getUser(String id);

    String deleteUser(String id);

    String updateUser(String id, String oldPass, String newPass) throws Exception;

    void insertUser(User user);

    User login(User user);

    //获取用户信息
    User findByLoginName(String loginname);

    //查询用户登陆名
    String findByusername(String username);

    //查询用户密码
//    String findByloginname(String loginname);

    //获取所有用户信息
    List<User> FinUserList();

    Page<User> findByParams(Map<String, Object> queryParams);

    //获取用户个人详情信息
    ArrayList<Map<String, Object>> personal(String userid);

    ArrayList<Map<String, Object>> Recently_answered(String replyauthor);
}
