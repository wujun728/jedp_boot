package com.ssm.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ssm.commons.JacksonUtils;
import com.ssm.manager.UserService;
import com.ssm.manager.dao.RedisDao;
import com.ssm.manager.mapper.UserMapper;
import com.ssm.manager.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	private RedisDao redisDao;

	@Override
	public List<User> getUsers() {
		String userJson = redisDao.get("user_");
		List<User> users = new ArrayList<>();
		if (StringUtils.isEmpty(userJson)) {
			users = userMapper.getUsers();
			if (users != null)
				redisDao.set("user_", JacksonUtils.objectToJson(users));
		} else {
			users = JacksonUtils.jsonToList(userJson, User.class);

		}
		return users;
	}

	@Override
	public User getUserById(String id) {
		String userJson = redisDao.get("user_" + id);
		User user = null;
		if (StringUtils.isEmpty(userJson)) {
			user = userMapper.getUserById(id);
			if (user != null)
				redisDao.set("user_" + id, JacksonUtils.objectToJson(user));
		} else {
			user = JacksonUtils.jsonToPojo(userJson, User.class);
		}
		return user;

	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
		String userJson = redisDao.get("user_" + user.getId());
		if (StringUtils.isEmpty(userJson)) {
			redisDao.set("user_" + user.getId(), JacksonUtils.objectToJson(user));
		}
	}

	@Override
	public void deleteUser(Integer id) {
		if (id == null)
			return;
		userMapper.deleteUser(id);
		String userJson = redisDao.get("user_" + id);
		if (!StringUtils.isEmpty(userJson)) {	
			redisDao.del("user_" + id);
			redisDao.del("user_");
		}
	}

}
