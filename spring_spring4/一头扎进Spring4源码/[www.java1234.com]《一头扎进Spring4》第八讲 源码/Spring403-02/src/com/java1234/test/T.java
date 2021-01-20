package com.java1234.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java1234.service.StudentService;


public class T {

	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		StudentService studentService=(StudentService)ac.getBean("studentService");
		studentService.addStudent("уехЩ");
		
	}
	

}
