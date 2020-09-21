package cn.edu.nuc.Spring_jdbc.dao.impl;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import cn.edu.nuc.Spring_jdbc.annotation.Table;
import cn.edu.nuc.Spring_jdbc.dao.infe.BaseDao;
@Repository
public class BaseDaoImpl extends JdbcDaoSupport implements BaseDao {
	@Autowired
	public void setDataSourceBean(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	@Override
	public <T> T findByName(Class<T> clazz, String name) {
		// TODO Auto-generated method stub
		Table table=clazz.getAnnotation(Table.class);
		String tableName="".equals(table.value())?clazz.getSimpleName():table.value();
		String pkName=table.pkName();
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(tableName).append(" where ");
		sql.append(pkName).append(" =?");
		return getJdbcTemplate().queryForObject(sql.toString(),new BeanPropertyRowMapper<T>(clazz),name);
	}

}
