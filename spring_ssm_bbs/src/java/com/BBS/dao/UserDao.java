package com.BBS.dao;

import com.BBS.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dyl on 2018/7/19.
 */
@Repository
public interface UserDao {
    //根据传入用户ID查询，信息
    User queryById(String id);

    /*
    更新用户信息
     */
    String updateById(String id);

    /*
    删除用户信息
     */
    String deleteByid(String id);

    /*
    找用户名
     */
    User findByLoginName(String loginname);

    /*
    插入用户数据，根据ID来查询
    */
    void insertByUser(User user);

//    void add(User user) throws SQLException;

//    void findByuser(User user) throws SQLException;

    //找到用户名跟密码
    User findByLoginnameAndLoginpass(Map<String, String> map) throws SQLException;

    //更新用户密码
    void updateLoginpass(Map<String, String> map) throws SQLException;

    boolean findByidAndLoginpass(Map<String, String> map) throws SQLException;

    String findByusername(String username);

    //更新用户信息
    void updateuser(User user);

//        String finfByloginname(String loginname);

    //获取所有用户信息
    List<User> userlist();

    //获取所有用户信息
    Page<User> findByParams(Map<String, Object> queryParams);

    //获取用户个人详情信息
    ArrayList<Map<String, Object>> personal(String userid);

    //获取用户最近回答数据
    ArrayList<Map<String, Object>> Recently_answered(String replyauthor);
}
