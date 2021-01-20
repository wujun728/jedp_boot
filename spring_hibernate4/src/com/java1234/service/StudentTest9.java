package com.java1234.service;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.model.Class;
import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest9 {

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
	public void testLazy1() {
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		Set<Student> studentList=(Set<Student>)c.getStudents();
		System.out.println(studentList.size());
		// studentList.iterator();
	}
	
	
	@Test
	public void testLazy2() {
		Student student=(Student)session.get(Student.class, Long.valueOf(1));
		student.getC().getName();
	}
	
	@Test
	public void testBatch1(){
		List<Class> classList=session.createQuery("from Class").list();
		Iterator it=classList.iterator();
		Class c1=(Class)it.next();
		Class c2=(Class)it.next();
		Class c3=(Class)it.next();
		c1.getStudents().iterator();
		c2.getStudents().iterator();
		c3.getStudents().iterator();
	}
	
	@Test
	public void testBatch2(){
		List<Class> classList=session.createQuery("from Class").list();
		
	}
	
	
	@Test
	public void testFetch1(){
		List<Class> classList=session.createQuery("from Class").list();
		Iterator it=classList.iterator();
		Class c1=(Class)it.next();
		Class c2=(Class)it.next();
		Class c3=(Class)it.next();
		c1.getStudents().iterator();
		c2.getStudents().iterator();
		c3.getStudents().iterator();
	}
	
	@Test
	public void testFetch2(){
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
	}
}
