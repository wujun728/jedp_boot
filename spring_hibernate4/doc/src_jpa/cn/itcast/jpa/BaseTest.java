package cn.itcast.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import cn.itcast.jpa.domain.Department;
import cn.itcast.jpa.domain.Employee;
import cn.itcast.jpa.domain.Sales;
import cn.itcast.jpa.domain.Skiller;
import cn.itcast.jpa.domain.User;

public class BaseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.setBirthday(new Date());
		user.setName("name");

		addUser(user);
		query(user.getId());
		// addDepart();
	}

	static void query(int id) {
		EntityManager em = JpaUtil.getEntityManager();
		String jpaQl= "select user from User user";
		Query q = em.createQuery(jpaQl);
		
		User user = em.find(User.class, id);
		System.out.println(user.getName());
		em.close();
	}

	static void addDepart() {
		EntityManager em = null;
		try {
			em = JpaUtil.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();

			Department depart = new Department();
			depart.setName("depart name");

			Employee emp1 = new Employee();
			emp1.setDepart(depart);// 对象模型：建立两个对象的关联
			emp1.setName("emp name1");

			Skiller emp2 = new Skiller();
			emp2.setDepart(depart);// 对象模型：建立两个对象的关联
			emp2.setName("emp name2");
			emp2.setSkill("skill");

			Sales emp3 = new Sales();
			emp3.setDepart(depart);
			emp3.setName("emp name3");
			emp3.setSell(100);

			Set<Employee> emps = new HashSet<Employee>();
			emps.add(emp2);
			emps.add(emp1);
			emps.add(emp3);

			depart.setEmps(emps);

			em.persist(depart);
			tx.commit();
		} finally {
			if (em != null)
				em.close();
		}
	}

	static void addUser(User user) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = JpaUtil.getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(user);
			tx.commit();
		} finally {
			if (em != null)
				em.close();
		}
	}
}
