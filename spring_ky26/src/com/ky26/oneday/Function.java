package com.ky26.oneday;

public class Function {
	public static void main(String[] args) {
		
		/*int j=1;
		for(int i=9;i>=1;i--){
			j=(j+1)*2;
			System.out.println(j);
		}*/
		System.out.println(getNum(1));
	}
	
	static int getNum(int n){
		if(n==10){
			return 1;
		}
		else{
			return (getNum(n+1)+1)*2;
		}
	}
}
//i��������j����ʣ�������������ÿ���һ���һ�������һ��ʣһ��->��һ���*2+1����ǰһ��
