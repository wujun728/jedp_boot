package com.java1234.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java1234.service.JavaWork;

public class Test2 {

	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		JavaWork javaWork=(JavaWork)ac.getBean("javaWork");
		javaWork.doTest();
	}
}
