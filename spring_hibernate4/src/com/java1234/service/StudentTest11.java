package com.java1234.service;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.java1234.model.Class;
import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest11 {

	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	private Session session;
	
	/*@Before
	public void setUp() throws Exception {
		session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // 提交事务
		 session.close(); // 关闭session
	}*/

	@Test
	public void testCache1() {
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		System.out.println(c.getName());
		Class c2=(Class)session.get(Class.class, Long.valueOf(1));
		System.out.println(c2.getName());
		System.out.println(c==c2);
	}
	
	@Test
	public void testCache2(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		System.out.println(c.getName());
		session1.getTransaction().commit();
		session1.close();
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		Class c2=(Class)session2.get(Class.class, Long.valueOf(1));
		System.out.println(c2.getName());
		session2.getTransaction().commit();
		session2.close();
		
		System.out.println(c==c2);
	}
	

}
