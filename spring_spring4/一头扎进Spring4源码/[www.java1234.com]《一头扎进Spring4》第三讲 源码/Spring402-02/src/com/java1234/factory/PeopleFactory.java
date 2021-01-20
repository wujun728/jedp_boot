package com.java1234.factory;

import com.java1234.entity.People;

public class PeopleFactory {

	public People createPeople(){
		People p=new People();
		p.setId(5);
		p.setName("Ð¡Æß");
		p.setAge(77);
		return p;
	}
}
