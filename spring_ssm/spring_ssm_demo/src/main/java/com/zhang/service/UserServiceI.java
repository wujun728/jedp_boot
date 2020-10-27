package com.zhang.service;

import java.util.List;

import com.zhang.model.User;

public interface UserServiceI {

	public User getUserById(Long id);

	public int insert(User user);
	
	List<User> getAll();

	List<User> getAll2();

	List<User> getAll3();

}
