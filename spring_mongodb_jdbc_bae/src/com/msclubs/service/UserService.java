package com.msclubs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msclubs.dao.LoginLogDao;
import com.msclubs.dao.UserDao;
import com.msclubs.domain.LoginLog;
import com.msclubs.domain.User;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("UserService")
@Transactional
public class UserService {

    @Resource(name="userDao")
	private UserDao userDao;
	
//	@Autowired
    @Resource(name="loginLogDao")
	private LoginLogDao loginLogDao;

	public boolean hasMatchUser(String userName, String password) {
		int matchCount =userDao.getMatchCount(userName, password);
		return matchCount > 0;
	}
	
	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}
	
	public void loginSuccess(User user) {
		user.setCredits( 5 + user.getCredits());
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
	}	

}
