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

	@Test
	public void test1() {
		People zhangsan=(People)ac.getBean("zhangsan");
		System.out.println(zhangsan);
		
		People lisi=(People)ac.getBean("lisi");
		System.out.println(lisi);
	}
	

}
