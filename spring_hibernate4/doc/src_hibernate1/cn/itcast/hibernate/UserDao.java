package cn.itcast.hibernate;

import cn.itcast.hibernate.domain.User;

public class UserDao {

	static void addUser(User user) {
		HibernateUtil.getThreadLocalSession().save(user);
	}
}
