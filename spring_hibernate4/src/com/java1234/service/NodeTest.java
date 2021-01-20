package com.java1234.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.model.Node;
import com.java1234.util.HibernateUtil;

public class NodeTest {

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
	public void testSaveMenu() {
		Node node=new Node();
		node.setName("根节点");
		
		Node subNode1=new Node();
		subNode1.setName("子节点1");
		
		Node subNode2=new Node();
		subNode2.setName("子节点2");
	    
		subNode1.setParentNode(node);
		subNode2.setParentNode(node);
		
		session.save(subNode1);
		session.save(subNode2);
	}
	
	
}
