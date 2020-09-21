package cn.edu.nuc.Spring_jdbc.model;

import cn.edu.nuc.Spring_jdbc.annotation.Table;

@Table(pkName="name",value="person")
public class Person {
	private String name;
	private String password;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", password=" + password + ", email=" + email + "]";
	}
	public Person(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	public Person(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public Person() {
		super();
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	
}
