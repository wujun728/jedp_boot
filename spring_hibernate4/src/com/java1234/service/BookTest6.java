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
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Book book=new Book();
	    book.setBookName("java���˼��");
	    book.setPrice(100);
	    book.setSpecialPrice(true);
	    book.setPublishDate(new SimpleDateFormat("yyyy-MM-dd").parse("2013-1-1"));
	    book.setAuthor("���˶�");
	    book.setIntroduction("���...");
	    
	    LobHelper lobHelper=session.getLobHelper();
	    InputStream in=new FileInputStream("c://java���˼��.jpg");
	    Blob bookPic=lobHelper.createBlob(in, in.available());
	    book.setBookPic(bookPic);
	   
	    session.save(book);
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	 
	}
}
