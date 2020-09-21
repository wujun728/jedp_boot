package cn.edu.nuc.Store.services.interfaces;

import cn.edu.nuc.Store.model.User;

public interface UserInf {
	public int insert(User user);
	public User select(String username);
}
