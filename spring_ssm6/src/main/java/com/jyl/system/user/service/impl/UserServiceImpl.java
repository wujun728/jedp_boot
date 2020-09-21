package com.jyl.system.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.jyl.common.Result;
import com.jyl.system.user.dao.UserMapper;
import com.jyl.system.user.model.User;
import com.jyl.system.user.service.UserService;

/**
 * 
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年5月30日 下午2:43:53
 */
@Service
public class UserServiceImpl implements UserService {
	
	Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserById(Long id) {
		
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User getUserByUsername(String username) {
		
		return userMapper.selectByUsername(username);
	}

	@Override
	public List<User> getUserList() {
		
		return userMapper.selectAllUser();
	}

	@Override
	public void insertOne(User user) {
		
		Assert.notNull(user, "param user cannot be null");
		
		if(!StringUtils.hasText(user.getUsername())){
			log.error("param username cannot be null or empty.");
			return;
		}
		
		if(!StringUtils.hasText(user.getPassword())){
			log.error("param password cannot be null or empty.");
			return;
		}
		
		if(user.getAge() == null || user.getAge()<=0){
			log.error("param age cannot be null or <=0.");
			return;
		}
		
		userMapper.insert(user);
	}

	@Override
	public void editOne(User user) {
		
		Assert.notNull(user, "param userId cannot be null");
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public Result pwdModify(Long userId, String oldPassword, String newPassword, String confirmPassword) {
		
		Result result = new Result();
		if(!newPassword.equals(confirmPassword)){
			log.error("oldPassword and confirmPassword can not match.");
			result.setCode(1001);
			result.setMsg("新密码与旧密码不可以一样");
			return result;
		}
		
		User user = userMapper.selectByPrimaryKey(userId);
		if(user == null){
			log.error("user[userId="+userId+"] not found.");
			result.setCode(1002);
			result.setMsg("非法请求：当前用户不存在");
			return result;
		}
		
		if(!oldPassword.equals(user.getPassword())){
			log.error("oldPassword error.");
			result.setCode(1003);
			result.setMsg("旧密码不正确");
			return result;
		}
		
		user.setPassword(newPassword);
		userMapper.modifyPassword(user);
		return result;
	}

}
