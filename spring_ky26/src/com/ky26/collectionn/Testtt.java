package com.ky26.collectionn;

public class Testtt {
	public static void main(String[] args) {
		Personn t1=Factory.createPersonn(Factory.TYPE_T);
		t1.desc();
	}
}
abstract class Personn{
	public abstract void desc();
}
class Worker extends Personn{
	@Override
	public void desc() {
		// TODO Auto-generated method stub
		System.out.println("���˱�������");
	}
	public void show(){
		System.out.println("������з���");
	}
}
class Teacher extends Personn {
	@Override
	public void desc() {
		// TODO Auto-generated method stub
		System.out.println("��ʦ��������");
	}
}

class Factory{
	public static final int TYPE_W=1;
	public static final int TYPE_T=2;
	public static Personn createPersonn(int type){
		switch(type){
			case TYPE_W:
				return new Worker();
			case TYPE_T:
				return new Teacher();
		}
		return null;
	}
}
