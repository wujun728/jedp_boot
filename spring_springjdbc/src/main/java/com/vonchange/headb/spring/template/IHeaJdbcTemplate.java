package com.vonchange.headb.spring.template;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

public interface IHeaJdbcTemplate {
	public <T> T insert(final String sql, final ResultSetExtractor<T> rse, final Object... params) ;
	public int update(final String sql, final Object... params) ;
	public <T> T query(final String sql, final ResultSetExtractor<T> rse, final Object... params) ;
	public <T> T queryProc(final String sql, final ResultSetExtractor<T> rse, final Object... params) ;
	public <T> int[] batchUpdate(String sql,final BatchPreparedStatementSetter pss);
}
