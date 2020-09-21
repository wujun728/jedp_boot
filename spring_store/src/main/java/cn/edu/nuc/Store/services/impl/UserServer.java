package cn.edu.nuc.Store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nuc.Store.entity.UserMapper;
import cn.edu.nuc.Store.model.User;
import cn.edu.nuc.Store.services.interfaces.UserInf;
@Service
public class UserServer implements UserInf{
	@Autowired
	private UserMapper	userMapper;
	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(user);
	}

	@Override
	public User select(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectpassword(username);
	}
	
}
