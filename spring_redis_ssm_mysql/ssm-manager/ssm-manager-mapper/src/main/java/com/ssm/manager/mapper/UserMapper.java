package com.ssm.manager.mapper;

import java.util.List;

import com.ssm.manager.pojo.User;

public interface UserMapper {
	public List<User> getUsers();

	public User getUserById(String id);
	
	public void insertUser(User user);
	
	public void deleteUser(Integer id);
}
