package cn.itcast.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.hibernate.domain.Name;
import cn.itcast.hibernate.domain.User;

public class Base {

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
		sql();
		// System.out.println("id: " + user.getId());
		// 222
		// System.out.println("1111111");
		// User u = getUser(user.getId());
		// System.out.println("name: " + u.getName());
		//
		// DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		// String name = request.getParameter("name");
		// if (name != null)
		// dc.add(Restrictions.eq("name", name));
		// int age = request.getParameter("age");
		// if(age > 0)
		// dc.add(Restrictions.eq("age", age));
		// List users = dc(dc);
	}

	static List namedQuery(Date date) {
		Session s = HibernateUtil.getSession();
		Query q = s.getNamedQuery("getUserByBirthday");//"cn.itcast.hibernate.domain.User.getUserByBirthday1"
		q.setDate("birthday", date);
		return q.list();
	}

	static List sql() {
		Session s = HibernateUtil.getSession();
		Query q = s.createSQLQuery("select * from user").addEntity(User.class);
		List<User> rs = q.list();
		for (User r : rs)
			System.out.println(r.getName());

		s.close();
		return rs;
	}

	static List dc(DetachedCriteria dc) {
		Session s = HibernateUtil.getSession();
		Criteria c = dc.getExecutableCriteria(s);
		List rs = c.list();
		s.close();
		return rs;
	}

	static void update() {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery("from User");
			List<User> users = q.list();
			for (User u : users) {
				u.setBirthday(new Date());
				// s.update(u);
			}

			Query q1 = s
					.createQuery("update u set birthday=:bd from User as u");
			q1.executeUpdate();
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}

	static User getUser(int id) {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Class userClass = User.class;
			Query q = s.createQuery("from User where id=:id");
			q.setInteger("id", id);
			q.uniqueResult();

			// User user = (User) s.get(userClass, id);
			User user1 = (User) s.load(userClass, id);
			Hibernate.initialize(user1);
			System.out.println(user1.getClass());
			return user1;
		} finally {
			if (s != null)
				s.close();
		}
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
