package com.java1234.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java1234.model.Teacher;
import com.java1234.util.HibernateUtil;

public class TeacherTest {
	
	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	
	public void add(){
	    Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Teacher s=new Teacher();
	    s.setName("张三");
	    session.save(s);
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	}
	
	public void delete(){
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Teacher Teacher=(Teacher)session.get(Teacher.class, Long.valueOf(1));
	    session.delete(Teacher);
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	}
	
	public void update(){
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Teacher Teacher=(Teacher)session.get(Teacher.class, Long.valueOf(2));
	    Teacher.setName("张三2");
	    session.save(Teacher);
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	}
	
	public void getAllTeacher(){
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

	public static void main(String[] args) {
		TeacherTest TeacherTest=new TeacherTest();
		// TeacherTest.add();
		// TeacherTest.delete();
		// TeacherTest.update();
		TeacherTest.getAllTeacher();
	}
}
