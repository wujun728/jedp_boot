package com.java1234.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java1234.model.Class;
import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest5 {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Class c1=new Class(); // 临时对象1
	    c1.setName("08计本");
	    
	    Class c2=new Class();  // 临时对象2
	    c2.setName("09计本");
	    
	    session.save(c1); // 持久化对象
	    session.save(c2); // 持久化对象
	    
	    session.delete(c2); // 删除对象
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	    
	    System.out.println(c1.getName()); // 游离对象
	    System.out.println(c2.getName()); // 删除对象
	}
}
