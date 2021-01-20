package com.java1234.service;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.model.Class;
import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest52 {

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
	public void testSaveClassAndStudent() {
		Class c=new Class();
	    c.setName("08计本");
	   
	    Student s1=new Student();
	    s1.setName("张三");
	    s1.setC(c);
	    
	    Student s2=new Student();
	    s2.setName("李四");
	    s2.setC(c);
	   
	    session.save(s1);
	    session.save(s2);
	    
	}
	
	@Test
	public void testLoadClass(){
		// Class c=(Class)session.load(Class.class, Long.valueOf(2));
		Class c=(Class)session.load(Class.class, Long.valueOf(1));
		System.out.println(c.getStudents());
	}
	
	@Test
	public void testGetClass(){
		// Class c=(Class)session.get(Class.class, Long.valueOf(2));
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		System.out.println(c.getStudents());
	}
	
	@Test
	public void testUpdateClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // 提交事务
		session1.close();
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		c.setName("08计算机本科2");
		session2.update(c);
		session2.getTransaction().commit(); // 提交事务
		session2.close();
	}
	
	@Test
	public void testSaveOrUpdateClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // 提交事务
		session1.close();
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		c.setName("08计算机本科3");
		
		Class c2=new Class();
		c2.setName("09计算机本科3");
		session2.saveOrUpdate(c);
		session2.saveOrUpdate(c2);
		session2.getTransaction().commit(); // 提交事务
		session2.close();
	}
	
	@Test
	public void testMergeClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // 提交事务
		session1.close();
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		
		Class c2=(Class)session2.get(Class.class, Long.valueOf(1));
		c.setName("08计算机本科4");
	
		session2.merge(c);

		session2.getTransaction().commit(); // 提交事务
		session2.close();
	}
	
	@Test
	public void testDeleteStudent(){
		Student student=(Student)session.load(Student.class, Long.valueOf(1));
		session.delete(student);
	}
}
