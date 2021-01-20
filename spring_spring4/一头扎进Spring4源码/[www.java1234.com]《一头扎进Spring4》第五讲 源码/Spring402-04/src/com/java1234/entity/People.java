package com.java1234.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class People {

	private int id;
	private String name;
	private int age;
	private Dog dog;
	
	
	
	public People() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public People(Dog dog) {
		super();
		System.out.println("constructor");
		this.dog = dog;
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
	public Dog getDog() {
		return dog;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}
	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", age=" + age
				+ ", dog=" + dog.getName() + "]";
	}
	
	
	
	
	



}
