package com.java1234.service.impl;


import org.springframework.transaction.annotation.Transactional;

import com.java1234.dao.BankDao;
import com.java1234.service.BankService;

@Transactional
public class BankServiceImpl implements BankService{

	private BankDao bankDao;
	
	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}
	

	@Override
	public void transferAccounts(int count, int userIdA, int userIdB) {
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub
		bankDao.outMoney(count, userIdA);
		bankDao.inMoney(count, userIdB);				
	}

}
