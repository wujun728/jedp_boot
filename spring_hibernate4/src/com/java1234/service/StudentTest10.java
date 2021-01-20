package com.java1234.service;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest10 {

	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // 提交事务
		 session.close(); // 关闭session
	}

	@Test
	public void testSQLQuery() {
		String sql="select * from t_student";
		Query query=session.createSQLQuery(sql).addEntity(Student.class);
		List studentList=query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testSQLQuery2() {
		String sql="select * from t_student where stuName like :stuName and stuAge=:stuAge";
		Query query=session.createSQLQuery(sql).addEntity(Student.class);
		query.setString("stuName", "张%");
		query.setInteger("stuAge", 10);
		List studentList=query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testHQLQuery() {
		String hql="from Student";
		Query query=session.createQuery(hql);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testHQLQuery2() {
		String hql="from Student where name like :stuName and age=:stuAge";
		Query query=session.createQuery(hql);
		query.setString("stuName", "张%");
		query.setInteger("stuAge", 10);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testHQLQuery3() {
		String hql="from Student as s where s.name like :stuName and s.age=:stuAge";
		Query query=session.createQuery(hql);
		query.setString("stuName", "张%");
		query.setInteger("stuAge", 10);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testHQLQuery4() {
		String hql="from Student order by age desc";
		Query query=session.createQuery(hql);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testHQLQuery5() {
		String hql="from Student";
		Query query=session.createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(2);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testHQLQuery6() {
		String hql="from Student";
		Query query=session.createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(1);
		Student student=(Student)query.uniqueResult();
		System.out.println(student);	
	}
	
	
	@Test
	public void testHQLQuery7() {
		String hql="from Student as s where s.name like :stuName and s.age=:stuAge";
		Query query=session.createQuery(hql);
		List<Student> studentList=(List<Student>)query
				.setString("stuName", "张%")
				.setInteger("stuAge", 10)
				.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
}
