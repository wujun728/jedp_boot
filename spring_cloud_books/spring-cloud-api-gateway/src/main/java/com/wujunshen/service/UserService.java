package com.wujunshen.service;

import com.wujunshen.dao.UserMapper;
import com.wujunshen.entity.User;
import com.wujunshen.entity.UserCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User:frankwoo(吴峻申) <br>
 * Date:2016-11-3 <br>
 * Time:17:41 <br>
 * Mail:frank_wjs@hotmail.com <br>
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findUserInfoByName(String userName) {
        UserCriteria userCriteria = new UserCriteria();
        UserCriteria.Criteria criteria = userCriteria.createCriteria();
        criteria.andNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userCriteria);

        return users != null ? users.size() != 0 ? users.get(0) != null ? users.get(0) : null : null : null;
    }
}