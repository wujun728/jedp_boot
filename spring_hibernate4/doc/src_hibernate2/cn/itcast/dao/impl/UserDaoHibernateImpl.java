package cn.itcast.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.itcast.dao.HibernateUitl;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

public class UserDaoHibernateImpl implements UserDao {

	public User findUserById(int id) {
		Session s = null;
		try {
			s = HibernateUitl.getSession();
			User user = (User) s.get(User.class, id);
			return user;
		} finally {
			if (s != null)
				s.close();
		}
	}

	public User findUserByName(String name) {
		Session s = null;
		try {
			s = HibernateUitl.getSession();
			Criteria c = s.createCriteria(User.class);
			c.add(Restrictions.eq("name", name));
			User user = (User) c.uniqueResult();
			return user;
		} finally {
			if (s != null)
				s.close();
		}
	}

	public User findUserByName1(String name) {
		Session s = null;
		try {
			s = HibernateUitl.getSession();
			String hql = "from User as user where user.name=:n";
			Query q = s.createQuery(hql);
			q.setString("n", name);
			User user = (User) q.uniqueResult();
			return user;
		} finally {
			if (s != null)
				s.close();
		}
	}

	public void remove(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUitl.getSession();
			tx = s.beginTransaction();
			s.delete(user);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}

	public void saveUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUitl.getSession();
			tx = s.beginTransaction();
			s.save(user);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}

	public void updateUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUitl.getSession();
			tx = s.beginTransaction();
			s.update(user);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}

	}

}
