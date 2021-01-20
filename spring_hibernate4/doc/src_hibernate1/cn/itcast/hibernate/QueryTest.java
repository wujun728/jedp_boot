package cn.itcast.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.itcast.hibernate.domain.Name;
import cn.itcast.hibernate.domain.User;

public class QueryTest {

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
		HibernateUtil.add(user);

		query(user.getName().getFirstName());
	}

	static void query(String name) {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			String hql = "from User as user where user.name=:n";// from Object
			Query query = s.createQuery(hql);
			// query.setString(0, name);
			query.setString("n", name);

			query.setFirstResult(200);
			query.setMaxResults(10);

			List<User> list = query.list();// executQuery();

			User u = (User) query.uniqueResult();
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
