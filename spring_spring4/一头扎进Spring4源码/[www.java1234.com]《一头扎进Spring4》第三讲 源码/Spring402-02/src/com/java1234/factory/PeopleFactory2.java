package com.java1234.factory;

import com.java1234.entity.People;

public class PeopleFactory2 {

	public static People createPeople(){
		People p=new People();
		p.setId(8);
		p.setName("Ð¡°Ë");
		p.setAge(88);
		return p;
	}
}
