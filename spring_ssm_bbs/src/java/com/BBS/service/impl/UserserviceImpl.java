package com.BBS.service.impl;

import com.BBS.dao.UserDao;
import com.BBS.pojo.User;
import com.BBS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dyl on 2018/7/19.
 */
@Service
public class UserserviceImpl implements UserService {

    @Autowired
    UserDao userDao;


    public User getUser(String id) {
        return userDao.queryById(id);
    }


    public String deleteUser(String id) {
        return userDao.deleteByid(id);
    }


    public String updateUser(String id, String oldPass, String newPass) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            map.put("oldPass", oldPass);
            map.put("newPass", newPass);
            boolean bool = userDao.findByidAndLoginpass(map);
            if (!bool) {
                throw new UserException("原密码错误");
            }
            userDao.updateLoginpass(map);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (UserException e) {
            e.printStackTrace();
        }
        return userDao.updateById(id);
    }


    public void insertUser(User user) {
        userDao.insertByUser(user);
    }

    public User login(User user) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("loginname", user.getUsername());
            map.put("loginpass", user.getPassword());
            return userDao.findByLoginnameAndLoginpass(map);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLoginName(String loginname) {
        return userDao.findByLoginName(loginname);
    }

    @Override
    public String findByusername(String username) {
        return userDao.findByusername(username);
    }

//    @Override
//    public String findByloginname(String loginname) {
//        return userDao.finfByloginname(loginname);
//    }

    @Override
    public List<User> FinUserList() {
        return userDao.userlist();
    }

    @Override
    public Page<User> findByParams(Map<String, Object> queryParams) {
        return userDao.findByParams(queryParams);
    }

    @Override
    public ArrayList<Map<String, Object>> personal(String userid) {
        return userDao.personal(userid);
    }

    @Override
    public ArrayList<Map<String, Object>> Recently_answered(String replyauthor) {
        return userDao.Recently_answered(replyauthor);
    }


}
