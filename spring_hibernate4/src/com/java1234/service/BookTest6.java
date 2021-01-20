package com.java1234.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;

import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java1234.model.Book;
import com.java1234.util.HibernateUtil;

public class BookTest6 {
	
	public static void main(String[] args) throws Exception{
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    
	    Book book=new Book();
	    book.setBookName("java编程思想");
	    book.setPrice(100);
	    book.setSpecialPrice(true);
	    book.setPublishDate(new SimpleDateFormat("yyyy-MM-dd").parse("2013-1-1"));
	    book.setAuthor("埃克尔");
	    book.setIntroduction("简介...");
	    
	    LobHelper lobHelper=session.getLobHelper();
	    InputStream in=new FileInputStream("c://java编程思想.jpg");
	    Blob bookPic=lobHelper.createBlob(in, in.available());
	    book.setBookPic(bookPic);
	   
	    session.save(book);
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	 
	}
}
