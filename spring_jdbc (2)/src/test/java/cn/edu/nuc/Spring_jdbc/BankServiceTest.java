package cn.edu.nuc.Spring_jdbc;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.nuc.Spring_jdbc.service.dao.BankService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-jdbc-transactional.xml"})
public class BankServiceTest {
	@Autowired
	private BankService bankService;
	Scanner in=new Scanner(System.in);
	@Test
	public void testTransfer() throws Exception{
		System.out.println("请输入转账用户信息：");
		transfer();
		System.out.println("是否继续转账？(Y/N?)");
		if(in.next().equalsIgnoreCase("Y")){
			transfer();
		}else{
			System.exit(0);
		}
	}
	void transfer(){
		System.out.println("请依次填入您的账户名，密码，转账接收方账号，转账金额(以回车确定输入下一项)");
		try{
			bankService.transfer(in.next(),in.next(),in.next(),in.nextInt());
		}catch(Exception e){
			System.out.println("抱歉，转账失败，余额不足");
		}
	}
}
