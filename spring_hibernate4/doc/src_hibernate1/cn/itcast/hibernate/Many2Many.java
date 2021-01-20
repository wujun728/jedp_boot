package cn.itcast.hibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itcast.hibernate.domain.Department;
import cn.itcast.hibernate.domain.Employee;
import cn.itcast.hibernate.domain.Student;
import cn.itcast.hibernate.domain.Teacher;

public class Many2Many {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		add();
		query(1);
	}

	static void query(int id) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			Teacher t = (Teacher) s.get(Teacher.class, id);
			System.out.println("students:" + t.getStudents().size());
			// Hibernate.initialize(depart.getEmps());
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}

	static void add() {
		Session s = null;
		Transaction tx = null;
		try {
			Set<Teacher> ts = new HashSet<Teacher>();

			Teacher t1 = new Teacher();
			t1.setName("t1 name");
			ts.add(t1);

			Teacher t2 = new Teacher();
			t2.setName("t2 name");
			ts.add(t2);

			Set<Student> ss = new HashSet<Student>();
			Student s1 = new Student();
			s1.setName("s1");
			ss.add(s1);

			Student s2 = new Student();
			s2.setName("s2");
			ss.add(s2);

			t1.setStudents(ss);
			t2.setStudents(ss);
			
			s1.setTeachers(ts);
			s2.setTeachers(ts);

			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.save(t1);
			s.save(t2);
			s.save(s1);
			s.save(s2);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}
}
