package com.java1234.service;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.model.Course;
import com.java1234.model.Course2;
import com.java1234.model.Student;
import com.java1234.model.Student2;
import com.java1234.util.HibernateUtil;

public class StudentTest82 {

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
	public void testSave1(){
		Course course1=new Course();
		course1.setName("����");
		
		Course course2=new Course();
		course2.setName("��ѧ");
		
		Student student1=new Student();
		student1.setName("����");
//		student1.getCourses().add(course1);
//		student1.getCourses().add(course2);
		
		Student student2=new Student();
		student2.setName("����");
//		student2.getCourses().add(course1);
//		student2.getCourses().add(course2);
		
		session.save(student1);
		session.save(student2);
	}
	
	@Test
	public void testLoad1(){
		Student student=(Student)session.get(Student.class, 1);
//		Set<Course> courses=(Set<Course>)student.getCourses();
//		Iterator it=courses.iterator();
//		while(it.hasNext()){
//			Course c=(Course)it.next();
//			System.out.println(c.getName());
//		}
	}
	
	
	@Test
	public void testSave2(){
		Course2 course1=new Course2();
		course1.setName("����");
		
		Course2 course2=new Course2();
		course2.setName("��ѧ");
		
		Student2 student1=new Student2();
		student1.setName("����");
		student1.getCourses().add(course1);
		student1.getCourses().add(course2);
		
		Student2 student2=new Student2();
		student2.setName("����");
		student2.getCourses().add(course1);
		student2.getCourses().add(course2);
		
		session.save(student1);
		session.save(student2);
	}
	
	@Test
	public void testLoad2(){
		Course2 course=(Course2)session.get(Course2.class, 1);
		Set<Student2> students=(Set<Student2>)course.getStudents();
		Iterator it=students.iterator();
		while(it.hasNext()){
			Student2 s=(Student2)it.next();
			System.out.println(s.getName());
		}
		
	}
	
	
}
