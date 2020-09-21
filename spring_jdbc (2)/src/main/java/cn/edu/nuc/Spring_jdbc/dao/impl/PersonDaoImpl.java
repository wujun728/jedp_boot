package cn.edu.nuc.Spring_jdbc.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.edu.nuc.Spring_jdbc.dao.infe.PersonDao;
import cn.edu.nuc.Spring_jdbc.model.Person;
@Repository
public class PersonDaoImpl extends BaseDaoImpl implements PersonDao {

	
	@Override
	public Person find(Person person) {
		// TODO Auto-generated method stub
		String sql="select  * from person where name=?";
		return getJdbcTemplate().queryForObject(sql,new BeanPropertyRowMapper<Person>(Person.class),person.getName());
	}
	public List<Person> findlist(Person person) {
		// TODO Auto-generated method stub
		String sql="select  * from person";
		return getJdbcTemplate().query(sql,new BeanPropertyRowMapper<Person>(Person.class));
	}
	@Override
	public int insert(Person person) {
		// TODO Auto-generated method stub
		String sql="insert into person(name,password,email) values(?,?,?)";
		return getJdbcTemplate().update(sql, person.getName(),person.getPassword(),person.getEmail());
	}
	@Override
	public int delete(Person person) {
		// TODO Auto-generated method stub
		String sql="delete from person where name=? and password=?";
		return getJdbcTemplate().update(sql,person.getName(),person.getPassword());
	}
	@Override
	public int update(Person person) {
		// TODO Auto-generated method stub
		String sql="update person set email=? where name=? and password=?";
		return getJdbcTemplate().update(sql, person.getEmail(),person.getName(),person.getPassword());
	}
}
