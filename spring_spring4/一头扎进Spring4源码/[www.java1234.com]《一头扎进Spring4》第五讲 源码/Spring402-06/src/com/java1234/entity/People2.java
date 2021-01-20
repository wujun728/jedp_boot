package com.java1234.entity;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;


public class People2 implements MethodReplacer {

	@Override
	public Object reimplement(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		Dog dog=new Dog();
		dog.setName("Tom");
		return dog;
	}
	
	
	
	
	



}
