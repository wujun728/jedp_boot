package com.java1234.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java1234.entity.People;

public class T {

	private ApplicationContext ac;

	@Before
	public void setUp() throws Exception {
		ac=new ClassPathXmlApplicationContext("beans.xml");
	}

	// ��������ֵ
	@Test
	public void test1() {
		People people=(People)ac.getBean("people1");
		System.out.println(people);
	}
	
	// ע��bean
	@Test
	public void test2() {
		People people=(People)ac.getBean("people2");
		System.out.println(people);
	}
	
	
	// �ڲ�bean
	@Test
	public void test3() {
		People people=(People)ac.getBean("people3");
		System.out.println(people);
	}
	
	// ע��null
	@Test
	public void test4() {
		People people=(People)ac.getBean("people4");
		System.out.println(people);
	}
	
	// ��������
	@Test
	public void test5() {
		People people=(People)ac.getBean("people5");
		System.out.println(people);
	}
	
	// ע�뼯��
	@Test
	public void test6() {
		People people=(People)ac.getBean("people6");
		System.out.println(people);
	}
}
