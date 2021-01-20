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
		session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // �ύ����
		 session.close(); // �ر�session
	}

	@Test
	public void testSaveMenu() {
		Node node=new Node();
		node.setName("���ڵ�");
		
		Node subNode1=new Node();
		subNode1.setName("�ӽڵ�1");
		
		Node subNode2=new Node();
		subNode2.setName("�ӽڵ�2");
	    
		subNode1.setParentNode(node);
		subNode2.setParentNode(node);
		
		session.save(subNode1);
		session.save(subNode2);
	}
	
	
}
