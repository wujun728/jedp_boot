package cn.edu.nuc.Spring_jdbc.test;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.nuc.Spring_jdbc.dao.infe.PersonDao;
import cn.edu.nuc.Spring_jdbc.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test-jdbc.xml"})
public class jdbcTest2 {
	@Autowired
	private PersonDao bankdao;
	@Test
	public void test(){
		System.out.println("----------欢迎进入！---------");
		main();
	}
	Scanner in=new Scanner(System.in);
	
	
	void main(){
		System.out.println("------请输入要进行的操作--------");
		System.out.println("--------1、查询单个用户--------");
		System.out.println("--------2、查询所有用户--------");
		System.out.println("--------3、修改一个用户--------");
		System.out.println("--------4、新增一个用户--------");
		System.out.println("--------5、删除一个用户--------");
		System.out.println("--------6、退出系统-----------");
		switch(in.nextInt()){
		case 1:selectperson();break;
		case 2:list();break;
		case 3:update();break;
		case 4:add();break;
		case 5:del();break;
		case 6:System.exit(0);
		}
	}
	
	private void list() {
		// TODO Auto-generated method stub
		System.out.println("------list输出-----");
		for(Person person:getlist()){
			System.out.println(person);
		}
		main();
	}
	private void add() {
		// TODO Auto-generated method stub
		System.out.println("请输入增加人的用户名和密码，以及新的邮箱");
		if(bankdao.insert(new Person(in.next(),in.next(),in.next()))>0){
			System.out.println("-------------新增个人成功----------------");
			for(Person person:getlist()){
				System.out.println(person);
			}
		}else{
			System.out.println("-------------新增个人失败----------------");
		}
		main();
	}
	private void del() {
		// TODO Auto-generated method stub
		System.out.println("请输入要删除的人的姓名和密码");
		if(bankdao.delete(new Person(in.next(),in.next()))>0){
			System.out.println("-------------删除成功----------------");
			for(Person person:getlist()){
				System.out.println(person);
			}
		}else{
			System.out.println("-------------删除失败----------------");
		}
		main();
	}
	private void selectperson() {
		// TODO Auto-generated method stub
		System.out.println("请输入要查找的用户名");
		System.out.println(getperson(new Person(in.next())));
		main();
	}
	List<Person> getlist(){
		return bankdao.findlist(new Person());
	}
	Person getperson(Person person){
		return bankdao.findByName(person.getClass(), person.getName());
	}
	void update(){
		System.out.println("请输入修改人的用户名和密码，以及新的邮箱");
		if(bankdao.update(new Person(in.next(),in.next(),in.next()))>0){
			System.out.println("-------------邮箱修改成功----------------");
			for(Person person:getlist()){
				System.out.println(person);
			}
		}else{
			System.out.println("-------------邮箱修改失败----------------");
		}
		main();
	}
}
