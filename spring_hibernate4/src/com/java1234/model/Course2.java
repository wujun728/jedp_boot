package com.java1234.model;

import java.util.HashSet;
import java.util.Set;

public class Course2 {

	private int id;
	private String name;
	private Set<Student2> students=new HashSet<Student2>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Student2> getStudents() {
		return students;
	}
	public void setStudents(Set<Student2> students) {
		this.students = students;
	}
	
	
	
	
}
