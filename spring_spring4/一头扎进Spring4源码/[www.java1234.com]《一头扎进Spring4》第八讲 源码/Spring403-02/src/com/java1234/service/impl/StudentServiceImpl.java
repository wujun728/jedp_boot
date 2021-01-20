package com.java1234.service.impl;

import com.java1234.service.StudentService;

public class StudentServiceImpl implements StudentService{

	@Override
	public void addStudent(String name) {
		// System.out.println("开始添加学生"+name);
		System.out.println("添加学生"+name);
		System.out.println(1/0);
		// System.out.println("完成学生"+name+"的添加");
	}

}
