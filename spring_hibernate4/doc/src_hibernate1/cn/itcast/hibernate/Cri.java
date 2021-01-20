package cn.itcast.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cn.itcast.hibernate.domain.User;

public class Cri {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		cri("name");
	}

	static void cri(String name) {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(User.class);
			c.add(Restrictions.eq("name", name));
			c.add(Restrictions.lt("birthday", new Date()));

			c.setFirstResult(100);
			c.setMaxResults(10);
			
			List<User> list = c.list();// executQuery();

			User u = (User) c.uniqueResult();
			System.out.print(u);

			for (User user : list) {
				System.out.println(user.getName());
			}
		} finally {
			if (s != null)
				s.close();
		}
	}
}
