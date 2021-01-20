package cn.itcast.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itcast.hibernate.domain.Name;
import cn.itcast.hibernate.domain.User;

public class VersionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.setBirthday(new Date());
		Name n = new Name();
		n.setFirstName("firstName");
		n.setLastName("lastName");
		user.setName(n);
		// 111
		addUser(user);
		// System.out.println("id: " + user.getId());
		// 222
		System.out.println("1111111");
		update(user.getId());
	}

	static void update(int id) {
		Session s1 = null;
		s1 = HibernateUtil.getSession();
		Transaction tx1 = s1.beginTransaction();
		User user1 = (User) s1.get(User.class, id);

		Session s2 = HibernateUtil.getSession();
		Transaction tx2 = s2.beginTransaction();
		User user2 = (User) s2.get(User.class, id);

		user1.getName().setFirstName("new1 firstName");

		user2.getName().setFirstName("new2 firstName");

		tx1.commit();
		tx2.commit();

		

		s1.close();
		s2.close();
	}

	static void addUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.save(user);
			// 333
			// s.persist(user);
			Name n = new Name();
			n.setFirstName("firstName");
			n.setLastName("lastName");
			user.setName(n);
			user.setBirthday(new Date());
			tx.commit();
			user.setBirthday(new Date());
			tx = s.beginTransaction();
			tx.commit();
			// 444
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			if (s != null)
				s.close();
		}
	}

	static void addUser1(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.save(user);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}

}
