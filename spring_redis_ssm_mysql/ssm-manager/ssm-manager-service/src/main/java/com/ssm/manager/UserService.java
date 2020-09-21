package com.ssm.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssm.manager.pojo.User;

public interface UserService {
	//获取所有用户
	public List<User> getUsers();
	//根据id获取用户
	User getUserById(String id);
	//插入用户
	void insertUser(User user);
	//删除用户
	void deleteUser(Integer id);
	
}
