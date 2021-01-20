package com.java1234.dao;

import java.util.List;

import com.java1234.model.Student;

public interface StudentDao {

	public int addStudent(Student student);
	
	public int updateStudent(Student student);
	
	public int deleteStudent(int id);
	
	public List<Student> findStudents();
}
