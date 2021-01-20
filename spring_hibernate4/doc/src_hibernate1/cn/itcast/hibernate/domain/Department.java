package cn.itcast.hibernate.domain;

import java.util.Set;

public class Department {
	private int id;
	private String name;

	private Set<Employee> emps;

	// private List<Employee> emps;

	// private Map<String, Employee> emps;

	// private Employee[] emps;

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

	public Set<Employee> getEmps() {
		return emps;
	}

	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
}
