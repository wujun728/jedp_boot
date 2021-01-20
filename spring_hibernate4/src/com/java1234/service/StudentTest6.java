package com.java1234.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.model.Student6;
import com.java1234.model.Student2;
import com.java1234.model.Student3;
import com.java1234.model.Student4;
import com.java1234.util.HibernateUtil;

public class StudentTest6 {

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
	public void testSetSave(){
		Set<String> imageSet=new HashSet<String>();
		imageSet.add("image1.png");
		imageSet.add("image2.png");
		imageSet.add("image3.png");
		imageSet.add("image3.png");
		
		Student6 s1=new Student6();
		s1.setImages(imageSet);
		session.save(s1);
	}
	
	@Test
	public void testSetFetch(){
		Student6 student=(Student6)session.get(Student6.class, Long.valueOf(1));
		Iterator it=student.getImages().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	
	@Test
	public void testListSave(){
		List<String> imageList=new ArrayList<String>();
		imageList.add("image1.png");
		imageList.add("image2.png");
		imageList.add("image3.png");
		imageList.add("image3.png");
		
		Student2 s2=new Student2();
//		s2.setImages(imageList);
		session.save(s2);
	}
	
	@Test
	public void testListFetch(){
		Student2 student2=(Student2)session.get(Student2.class, Long.valueOf(2));
//		Iterator it=student2.getImages().iterator();
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
	}
	
	@Test
	public void testBagSave(){
		List<String> imageList=new ArrayList<String>();
		imageList.add("image1.png");
		imageList.add("image2.png");
		imageList.add("image3.png");
		imageList.add("image3.png");
		
		Student3 s3=new Student3();
//		s3.setImages(imageList);
//		session.save(s3);
	}
	
	@Test
	public void testBagFetch(){
		Student3 student3=(Student3)session.get(Student3.class, Long.valueOf(3));
		Iterator it=student3.getImages().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void testMapSave(){
		Map<String,String> imageMap=new HashMap<String,String>();
		imageMap.put("i1", "image1.png");
		imageMap.put("i2", "image2.png");
		imageMap.put("i3", "image3.png");
		imageMap.put("i4", "image4.png");
		
		Student4 s4=new Student4();
		s4.setImages(imageMap);
		session.save(s4);
	}
	
	@Test
	public void testMapFetch(){
		Student4 student4=(Student4)session.get(Student4.class, Long.valueOf(4));
		Map<String,String> imageMap=student4.getImages();
		Set keys=imageMap.keySet();
		Iterator it=keys.iterator();
		while(it.hasNext()){
			String key=(String)it.next();
			System.out.println(key+":"+imageMap.get(key));
		}
	}

}
