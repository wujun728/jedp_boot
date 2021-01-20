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
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Teacher s=new Teacher();
	    s.setName("����");
	    session.save(s);
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}

	@Test
	public void testDelete() {
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Teacher Teacher=(Teacher)session.get(Teacher.class, Long.valueOf(2));
	    session.delete(Teacher);
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}

	@Test
	public void testUpdate() {
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Teacher Teacher=(Teacher)session.get(Teacher.class, Long.valueOf(3));
	    Teacher.setName("����2");
	    session.save(Teacher);
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}

	@Test
	public void testGetAllTeacher() {
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    String hql="from Teacher";
	    Query query=session.createQuery(hql);
	    List<Teacher> TeacherList=query.list();
	    for(Teacher Teacher:TeacherList){
	    	System.out.println(Teacher);
	    }
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}

}
