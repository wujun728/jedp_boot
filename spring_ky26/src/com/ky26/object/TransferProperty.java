package com.ky26.object;

public class TransferProperty {
	int i=47;
	public void call(){
		System.out.println("����call��������");
		for(int i=0;i<3;i++){
			System.out.print(i+"");
			if(i==2){
				System.out.print("\n");
			}
		}
	}
	public TransferProperty(){
		
	}
	public static void main(String[] args){
		TransferProperty t1=new TransferProperty();
		TransferProperty t2=new TransferProperty();
		t2.i=60;
		System.out.println("��һ��ʵ��������ñ���i�Ľ����"+t1.i++);
		t1.call();
		System.out.println("�ڶ���ʵ��������ñ���i�Ľ����"+t2.i);
		t2.call();
	}
}
