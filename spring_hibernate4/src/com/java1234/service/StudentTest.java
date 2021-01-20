package com.java1234.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.java1234.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		
		Configuration configuration=new Configuration().configure(); // ʵ���������ļ�
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); // ʵ��������Ǽ�
	    SessionFactory sessionFactory=configuration.buildSessionFactory(serviceRegistry); // ��ȡSession����
	    Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Student s=new Student();
	    s.setName("����");
	    session.save(s);
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	    sessionFactory.close(); // �ر�session����
	}
}
