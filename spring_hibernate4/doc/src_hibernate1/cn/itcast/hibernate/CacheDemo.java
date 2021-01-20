package cn.itcast.hibernate;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.hibernate.domain.User;

public class CacheDemo {

	static Map cache = new HashMap();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u = getUser(1);	
		
		User u1 = getUser(1);

	}

	public static void update(User user){
		updateDB(user);
		String key = User.class.getName() + user.getId();
		cache.remove(key);
	}
	
	

	public static User getUser(int id) {
		String key = User.class.getName() + id;
		User user = (User) cache.get(key);
		if (user != null)
			return user;
		user = getFromDB();
		cache.put(key, user);
		return user;
	}
	
	private static void updateDB(User user) {
		// TODO Auto-generated method stub
		
	}
	private static User getFromDB() {
		// TODO Auto-generated method stub
		return null;
	}
}
