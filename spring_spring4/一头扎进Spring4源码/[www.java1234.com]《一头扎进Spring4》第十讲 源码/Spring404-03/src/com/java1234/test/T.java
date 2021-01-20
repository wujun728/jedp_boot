package com.java1234.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java1234.model.Student;
import com.java1234.service.StudentService;


public class T {

	private ApplicationContext ac;

	@Before
	public void setUp() throws Exception {
		ac=new ClassPathXmlApplicationContext("beans.xml");
	}

	@Test
	public void addStudent() {
		StudentService studentService=(StudentService)ac.getBean("studentService");
		int addNums=studentService.addStudent(new Student("王五", 1));
		if(addNums==1){
			System.out.println("添加成功");
		}
	}
	
	@Test
	public void updateStudent() {
		StudentService studentService=(StudentService)ac.getBean("studentService");
		int updateNums=studentService.updateStudent(new Student(11,"王五2", 2));
		if(updateNums==1){
			System.out.println("更新成功");
		}
	}
	
	@Test
	public void deleteStudent() {
		StudentService studentService=(StudentService)ac.getBean("studentService");
		int deleteNums=studentService.deleteStudent(11);
		if(deleteNums==1){
			System.out.println("删除成功");
		}
	}
	
	@Test
	public void findStudents() {
		StudentService studentService=(StudentService)ac.getBean("studentService");
		List<Student> studentList=studentService.findStudents();
		for(Student student:studentList){
			System.out.println(student);
		}
	}
	
	

}
