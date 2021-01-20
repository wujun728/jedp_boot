package com.java1234.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class StudentServiceAspect {

	public void doBefore(JoinPoint jp){
		System.out.println("����:"+jp.getTarget().getClass().getName());
		System.out.println("��������"+jp.getSignature().getName());
		System.out.println("��ʼ���ѧ����"+jp.getArgs()[0]);
	}
	
	public void doAfter(JoinPoint jp){
		System.out.println("����:"+jp.getTarget().getClass().getName());
		System.out.println("��������"+jp.getSignature().getName());
		System.out.println("ѧ�������ɣ�"+jp.getArgs()[0]);
	}
	
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("���ѧ��ǰ");
		Object retVal=pjp.proceed();
		System.out.println(retVal);
		System.out.println("���ѧ����");
		return retVal;
	}
	
	public void doAfterReturning(JoinPoint jp){
		System.out.println("����֪ͨ");
	}
	
	public void doAfterThrowing(JoinPoint jp,Throwable ex){
		System.out.println("�쳣֪ͨ");
		System.out.println("�쳣��Ϣ��"+ex.getMessage());
	}
}
