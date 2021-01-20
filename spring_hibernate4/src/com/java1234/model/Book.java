package com.java1234.model;

import java.sql.Blob;
import java.util.Date;

public class Book {

	private int id;
	private String bookName; // ͼ������
	private float price; // ͼ��۸�
	private boolean specialPrice; // �Ƿ����ؼ�
	private Date publishDate; // ��������
	private String author; // ����
	private String introduction; // ���
	private Blob bookPic; // ͼ��ͼƬ
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(boolean specialPrice) {
		this.specialPrice = specialPrice;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Blob getBookPic() {
		return bookPic;
	}
	public void setBookPic(Blob bookPic) {
		this.bookPic = bookPic;
	}
	
	
	
}
