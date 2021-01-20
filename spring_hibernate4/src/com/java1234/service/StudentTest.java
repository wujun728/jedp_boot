package com.java1234.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.java1234.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		
		Configuration configuration=new Configuration().configure(); // 实例化配置文件
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); // 实例化服务登记
	    SessionFactory sessionFactory=configuration.buildSessionFactory(serviceRegistry); // 获取Session工厂
	    Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Student s=new Student();
	    s.setName("张三");
	    session.save(s);
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	    sessionFactory.close(); // 关闭session工厂
	}
}
