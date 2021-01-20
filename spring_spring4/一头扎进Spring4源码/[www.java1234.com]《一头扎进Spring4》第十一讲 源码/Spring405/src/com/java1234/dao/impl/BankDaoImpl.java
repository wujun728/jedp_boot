package com.java1234.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.java1234.dao.BankDao;

public class BankDaoImpl implements BankDao{

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public void inMoney(int money, int userId) {
		// TODO Auto-generated method stub
		String sql="update t_count2 set count=count+:money where userId=:userId";
		MapSqlParameterSource sps=new MapSqlParameterSource();
		sps.addValue("money", money);
		sps.addValue("userId", userId);
		namedParameterJdbcTemplate.update(sql,sps);
	}

	@Override
	public void outMoney(int money, int userId) {
		// TODO Auto-generated method stub
		String sql="update t_count set count=count-:money where userId=:userId";
		MapSqlParameterSource sps=new MapSqlParameterSource();
		sps.addValue("money", money);
		sps.addValue("userId", userId);
		namedParameterJdbcTemplate.update(sql,sps);
	}

}
