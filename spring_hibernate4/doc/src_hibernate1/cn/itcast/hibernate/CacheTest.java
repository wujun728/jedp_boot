package cn.itcast.hibernate;

import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;

import cn.itcast.hibernate.domain.Name;
import cn.itcast.hibernate.domain.User;

public class CacheTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = addUser();
		System.out.println("---------");
		getUser(user.getId());

		Statistics st = HibernateUtil.getSessionFactory().getStatistics();
		System.out.println("put:" + st.getSecondLevelCachePutCount());
		System.out.println("hit:" + st.getSecondLevelCacheHitCount());
		System.out.println("miss:" + st.getSecondLevelCacheMissCount());

	}

	static User getUser(int id) {
		Session s = null;
		User user = null;
		// HibernateUtil.getSessionFactory().evict(User.class);
		try {
			s = HibernateUtil.getSession();
			Class userClass = User.class;
			user = (User) s.get(userClass, id);
			System.out.println(user.getClass());
			// s.evict(user);
			// String hql = "from Object";
			// s.evict(user);
			// s.clear();

			// user = (User) s.get(userClass, id);
			// s.clear();
			// user = (User) s.load(userClass, id);
			// Query q = s.createQuery("from User where age>20");
			// q.setCacheable(true);
			// user = (User) q.uniqueResult();
			// System.out.println(user.getName());
		} finally {
			if (s != null)
				s.close();
		}

		// try {
		// s = HibernateUtil.getSession();
		// Class userClass = User.class;
		// user = (User) s.get(userClass, id);
		// System.out.println(user.getName());
		// } finally {
		// if (s != null)
		// s.close();
		// }

		return user;
	}

	public static User addUser() {
		User user = new User();
		user.setBirthday(new Date());
		Name n = new Name();
		n.setFirstName("firstName");
		n.setLastName("lastName");
		user.setName(n);
		// 111
		HibernateUtil.add(user);
		return user;
	}

}
