package cn.itcast.hibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itcast.hibernate.domain.Department;
import cn.itcast.hibernate.domain.Employee;
import cn.itcast.hibernate.domain.Sales;
import cn.itcast.hibernate.domain.Skiller;

public class Many2One {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Department depart = add();
		//System.out.println("-----------");
		//Employee emp = query(2, false);
//		System.out.println("depart name:" + emp.getDepart().getName());
		// Department d = queryDepart(depart.getId());
		// System.out.println(d.getEmps());

	}

	static Department queryDepart(int departId) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			Department depart = (Department) s.get(Department.class, departId);
			// System.out.println("emps:" + depart.getEmps());
			// System.out.println("emp size:" + depart.getEmps().size());
			// Hibernate.initialize(depart.getEmps());
			tx.commit();
			return depart;
		} finally {
			if (s != null)
				s.close();
		}
	}

	static Employee query(int empId, boolean includeDepart) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			Employee emp = (Employee) s.get(Employee.class, empId);
			// System.out.println(emp.getDepart().getName());
			// System.out.println("depart name:" + emp.getDepart().getName());
			if (includeDepart)
				Hibernate.initialize(emp.getDepart());
			tx.commit();
			return emp;
		} finally {
			if (s != null)
				s.close();
		}
	}

	static Department add() {
		Session s = null;
		Transaction tx = null;
		try {
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
			// Map<String, Employee> emps = new HashMap<String, Employee>();
			// emps.put(emp1.getName(), emp1);
			// emps.put(emp2.getName(), emp2);
			depart.setEmps(emps);
			// depart.getEmps();

			s = HibernateUtil.getSession();
			tx = s.beginTransaction();

			s.save(depart);
			s.save(emp1);
			s.save(emp2);
			s.save(emp3);
			//s.flush();
			System.out.println("----------");
			tx.commit();

			// System.out.println(depart.getEmps().getClass());
			// HashSet hs = (HashSet) depart.getEmps();
			return depart;
		} finally {
			if (s != null)
				s.close();
		}
	}
}
