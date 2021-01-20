package com.java1234.service;

import java.util.List;

import com.java1234.model.Student;

public interface StudentService {

	public int addStudent(Student student);
	
	public int updateStudent(Student student);
	
	public int deleteStudent(int id);
	
	public List<Student> findStudents();
}
