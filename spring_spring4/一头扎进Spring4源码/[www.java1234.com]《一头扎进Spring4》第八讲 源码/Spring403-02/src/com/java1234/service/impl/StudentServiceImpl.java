package com.java1234.service.impl;

import com.java1234.service.StudentService;

public class StudentServiceImpl implements StudentService{

	@Override
	public void addStudent(String name) {
		// System.out.println("��ʼ���ѧ��"+name);
		System.out.println("���ѧ��"+name);
		System.out.println(1/0);
		// System.out.println("���ѧ��"+name+"�����");
	}

}
