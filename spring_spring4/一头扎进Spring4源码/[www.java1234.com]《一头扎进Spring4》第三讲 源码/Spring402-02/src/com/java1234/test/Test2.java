package com.java1234.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java1234.entity.People;


public class Test2 {

	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		People people=(People)ac.getBean("people");
		System.out.println(people);
		
		// ����ע��
		People people2=(People)ac.getBean("people2");
		System.out.println(people2);
		
		// ���췽��ע��
		People people3=(People)ac.getBean("people3");
		System.out.println(people3);
		
		People people4=(People)ac.getBean("people4");
		System.out.println(people4);
		
		People people5=(People)ac.getBean("people5");
		System.out.println(people5);
		
		// ��������ע��
		People people7=(People)ac.getBean("people7");
		System.out.println(people7);
		
		People people8=(People)ac.getBean("people8");
		System.out.println(people8);
	}
}
