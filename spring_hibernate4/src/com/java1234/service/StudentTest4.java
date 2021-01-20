package com.java1234.service;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.model.Class;
import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest4 {

	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // �ύ����
		 session.close(); // �ر�session
	}

	@Test
	public void testSaveClassAndStudent() {
		Class c=new Class();
	    c.setName("08�Ʊ�");
	    
	    Student s1=new Student();
	    s1.setName("����");
	    
	    Student s2=new Student();
	    s2.setName("����");
	    
	    c.getStudents().add(s1);
	    c.getStudents().add(s2);
	    
	    session.save(c);
	    
	}
	
	@Test
	public void getStudentsByClass(){
		Class c=(Class)session.get(Class.class, Long.valueOf(2));
		Set<Student> students=c.getStudents();
		Iterator it=students.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}
	}
	
	@Test
	public void testAdd(){
		Class c=new Class();
	    c.setName("09�Ʊ�");
	    
	    Student s1=new Student();
	    s1.setName("����");
	    
	    session.save(c);
	    session.save(s1);
	}
	
	@Test
	public void testInverse(){
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		Student s=(Student)session.get(Student.class, Long.valueOf(1));
		
		s.setC(c);
		c.getStudents().add(s);
	}

	@Test
	public void testDeleteClassCascade(){
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		session.delete(c);
	}
}
