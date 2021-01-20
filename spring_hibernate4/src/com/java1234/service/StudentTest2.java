package com.java1234.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest2 {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Student s=new Student();
	    s.setName("张三");
	    session.save(s);
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	}
}
