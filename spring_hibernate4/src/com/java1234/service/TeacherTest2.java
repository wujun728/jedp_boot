package com.java1234.service;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.java1234.model.Teacher;
import com.java1234.util.HibernateUtil;

public class TeacherTest2 {
	
	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	
	@Test
	public void testAdd() {
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Teacher s=new Teacher();
	    s.setName("张三");
	    session.save(s);
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	}

	@Test
	public void testDelete() {
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Teacher Teacher=(Teacher)session.get(Teacher.class, Long.valueOf(2));
	    session.delete(Teacher);
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	}

	@Test
	public void testUpdate() {
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Teacher Teacher=(Teacher)session.get(Teacher.class, Long.valueOf(3));
	    Teacher.setName("张三2");
	    session.save(Teacher);
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	}

	@Test
	public void testGetAllTeacher() {
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    String hql="from Teacher";
	    Query query=session.createQuery(hql);
	    List<Teacher> TeacherList=query.list();
	    for(Teacher Teacher:TeacherList){
	    	System.out.println(Teacher);
	    }
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	}

}
