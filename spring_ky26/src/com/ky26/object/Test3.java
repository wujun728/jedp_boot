package com.ky26.object;



public class Test3 {
	static void show(){
		System.out.println(1);
	}
	void show(int n){
		
	}
}
class TestF extends Test3{
	static void show(){
		System.out.println(2);
	}//�����˸��෽������������д???static���εĳ�Ա���ڷ������У�����ʵ���������ԲŻ������ظ����˵��???
	void show(int n){
		System.out.println(n);
	}
}
