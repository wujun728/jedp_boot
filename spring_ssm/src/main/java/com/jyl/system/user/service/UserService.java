package com.jyl.system.user.service;

import java.util.List;

import com.jyl.common.Result;
import com.jyl.system.user.model.User;

public interface UserService {

	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	User getUserById(Long id);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	/**
	 * 查询用户信息列表
	 * @return
	 */
	List<User> getUserList();
	
	/**
	 * 新增用户
	 * @param user
	 */
	void insertOne(User user);
	
	/**
	 * 编辑用户
	 * @param userId
	 * @param user
	 */
	void editOne(User user);
	
	/**
	 * 密码修改
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @param confirmPassword
	 */
	Result pwdModify(Long userId, String oldPassword, String newPassword, String confirmPassword);
}
