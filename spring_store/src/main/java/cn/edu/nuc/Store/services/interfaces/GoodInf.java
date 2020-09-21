package cn.edu.nuc.Store.services.interfaces;

import cn.edu.nuc.Store.model.User;

public interface GoodInf {
	public int insert(User user);
	public User select(String username);
}
