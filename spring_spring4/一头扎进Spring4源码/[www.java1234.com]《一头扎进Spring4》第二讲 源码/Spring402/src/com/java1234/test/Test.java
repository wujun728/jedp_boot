package com.java1234.test;

import com.java1234.service.JavaWork;
import com.java1234.service.Lisi;

public class Test {

	/**
	 * Ö÷¹ÜÖ´ĞĞÃüÁî
	 * @param args
	 */
	public static void main(String[] args) {
		JavaWork javaWork=new JavaWork();
		// javaWork.setTester(new ZhangSan());
		javaWork.setTester(new Lisi());
		javaWork.doTest();
	}
}
