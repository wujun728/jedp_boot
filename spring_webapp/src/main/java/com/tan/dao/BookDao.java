package com.tan.dao;

import org.springframework.stereotype.Component;

import com.tan.model.Book;
@Component
public class BookDao {

	//模拟数据库操作
	public void add(Book book){
		System.out.print("Add");
	}
	public void update(Book book){
		System.out.print("Update");
	}
}
