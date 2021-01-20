package com.java1234.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.model.Image;
import com.java1234.model.Image2;
import com.java1234.model.Image3;
import com.java1234.model.Student2;
import com.java1234.model.Student3;
import com.java1234.util.HibernateUtil;

public class StudentTest8 {

	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // 提交事务
		 session.close(); // 关闭session
	}

	@Test
	public void testGetAllImages(){
		List<Image> imageList=new ArrayList<Image>();
		int stuId=1;
		List<Image> lifeImageList=(List<Image>)session.createQuery("from LifeImage l where l.student.id="+stuId).list();
		imageList.addAll(lifeImageList);
		List<Image> workImageList=(List<Image>)session.createQuery("from WorkImage w where w.student.id="+stuId).list();
		imageList.addAll(workImageList);
		Iterator it=imageList.iterator();
		while(it.hasNext()){
			Image image=(Image)it.next();
			System.out.println(image.getImageName());
		}
	}
	
	@Test
	public void testGetAllImages2(){
		Student2 student2=(Student2)session.get(Student2.class, 1);
//		Set<Image2> images=student2.getImages();
//		Iterator it=images.iterator();
//		while(it.hasNext()){
//			Image2 image=(Image2)it.next();
//			System.out.println(image.getImageName());
//		}
	}
	
	@Test
	public void testGetAllImages3(){
		Student3 student3=(Student3)session.get(Student3.class, 1);
		Set<Image3> images=student3.getImages();
		Iterator it=images.iterator();
		while(it.hasNext()){
			Image3 image=(Image3)it.next();
			System.out.println(image.getImageName());
		}
	}
	
}
