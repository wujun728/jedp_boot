package com.java1234.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest1 {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Student s1=(Student)session.get(Student.class, Long.valueOf(1));
	    Student s2=(Student)session.get(Student.class, Long.valueOf(2));
	    Student s3=(Student)session.get(Student.class, Long.valueOf(1));
	    System.out.println(s1==s2);
	    System.out.println(s1==s3);
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}
}
