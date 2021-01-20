package com.java1234.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory=buildSessionFactory();

	private static SessionFactory buildSessionFactory(){
		Configuration configuration=new Configuration().configure(); // ʵ���������ļ�
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); // ʵ��������Ǽ�
	    return configuration.buildSessionFactory(serviceRegistry); // ��ȡSession����
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
