package cn.edu.nuc.Spring_jdbc.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.edu.nuc.Spring_jdbc.dao.infe.AccountDao;
import cn.edu.nuc.Spring_jdbc.model.Person;
@Repository
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {

	@Override
	public int transfer(String name, int money) {
		// TODO Auto-generated method stub
		String sql="update person set count=count+? where name=?";
		return getJdbcTemplate().update(sql,money,name);
	}

	@Override
	public String getpass(String name) {
		// TODO Auto-generated method stub
		String sql="select password from person where name=?";
		Person person=getJdbcTemplate().queryForObject(sql,new BeanPropertyRowMapper<Person>(Person.class),name);
		return person.getPassword();
	}

}
