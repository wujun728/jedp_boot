package com.java1234.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentTest22 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("���ʼ��ǰ����...");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("���ʼ�������...");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("�ڲ��Է���ǰ����...");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("�ڲ��Է��������...");
	}

	@Test
	public void test() {
		System.out.println("���Է���");
	}

}
