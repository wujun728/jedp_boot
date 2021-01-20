package cn.itcast.hibernate.domain;

public class Employee {
	private int id;
	private String name;
	private Department depart;

	@Override
	public String toString() {
		return "id=" + this.id + " name=" + this.name;
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

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}
}
