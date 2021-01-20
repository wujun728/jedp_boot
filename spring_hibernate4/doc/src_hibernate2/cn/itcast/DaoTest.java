package cn.itcast;

import java.util.Date;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoHibernateImpl;
import cn.itcast.domain.User;

public class DaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserDao dao = new UserDaoHibernateImpl();
		User user = new User();
		user.setName("name");
		user.setBirthday(new Date());
		System.out.println("1111");
		dao.saveUser(user);

		user.setName("new name");
		System.out.println("2222");
		dao.updateUser(user);

		User u = dao.findUserByName(user.getName());

		System.out.println("3333");
		dao.remove(u);

	}

}
