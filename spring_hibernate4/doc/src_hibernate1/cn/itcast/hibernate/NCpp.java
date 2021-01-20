package cn.itcast.hibernate;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itcast.hibernate.domain.Name;
import cn.itcast.hibernate.domain.User;

public class NCpp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		addUser();
		System.out.println("------");
		iterator();
	}

	static void iterator() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from User ");
		Iterator<User> users = q.iterate();
		while (users.hasNext()) {
			System.out.println(users.next().getName().getFirstName());
		}
		s.close();
	}

	static void addUser() {
		Session s = null;
		Transaction tx = null;
		for (int i = 0; i < 10; i++) {
			try {
				s = HibernateUtil.getSession();
				tx = s.beginTransaction();
				User user = new User();
				Name n = new Name();
				n.setFirstName("firstName");
				n.setLastName("lastName");
				user.setName(n);
				user.setBirthday(new Date());

				user.getName().setFirstName("firstName" + i);
				s.save(user);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				throw e;
			} finally {
				if (s != null)
					s.close();
			}
		}
	}
}
