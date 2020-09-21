package cn.edu.nuc.Spring_jdbc.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nuc.Spring_jdbc.dao.infe.AccountDao;
import cn.edu.nuc.Spring_jdbc.service.dao.BankService;
@Repository
public class BankServiceImpl implements BankService{
	@Autowired
	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	@Transactional(
			propagation=Propagation.REQUIRED,//事务传播行为
			noRollbackFor={NullPointerException.class},//不回滚事务的异常
			rollbackFor={Exception.class},//回滚事务的异常
			timeout=20,//超时，当次失误耗时达20秒时结束事物
			isolation=Isolation.READ_COMMITTED//设置隔离级别，未设置时以数据库为准
			)
	public boolean transfer(String a,String a_pass, String b, int money) {
		// TODO Auto-generated method stub
			if(!a_pass.equals(accountDao.getpass(a))){
				System.out.println("抱歉，转账失败，密码不正确");
				return false;
			}else{
				System.out.println("密码验证成功！");
				accountDao.transfer(b, money);
				accountDao.transfer(a, -money);
				System.out.println("帐号" + a + "转帐给" + b + "金额:" + money);
				return true;
			}
	}
}
