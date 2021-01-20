package com.java1234.entity;

public class People {

	private int id;
	private String name;
	private int age;
	
	
	
	public People() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public People(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}




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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	
	
}
