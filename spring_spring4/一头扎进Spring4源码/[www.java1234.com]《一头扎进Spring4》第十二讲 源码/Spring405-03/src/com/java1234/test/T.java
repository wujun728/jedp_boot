package com.java1234.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java1234.service.BankService;


public class T {

	private ApplicationContext ac;

	@Before
	public void setUp() throws Exception {
		ac=new ClassPathXmlApplicationContext("beans.xml");
	}

	@Test
	public void transferAccounts() {
		BankService bankService=(BankService)ac.getBean("bankService");
		bankService.transferAccounts(50, 1, 2);
	}
	
	

}
