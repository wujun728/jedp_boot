package com.java1234.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java1234.model.Class;
import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest5 {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Class c1=new Class(); // ��ʱ����1
	    c1.setName("08�Ʊ�");
	    
	    Class c2=new Class();  // ��ʱ����2
	    c2.setName("09�Ʊ�");
	    
	    session.save(c1); // �־û�����
	    session.save(c2); // �־û�����
	    
	    session.delete(c2); // ɾ������
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	    
	    System.out.println(c1.getName()); // �������
	    System.out.println(c2.getName()); // ɾ������
	}
}
