package com.java1234.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.java1234.dao.StudentDao;
import com.java1234.model.Student;

public class StudentDaoImpl extends JdbcDaoSupport implements StudentDao{

	@Override
	public int addStudent(Student student) {
		String sql="insert into t_student values(null,?,?)";
		Object []params=new Object[]{student.getName(),student.getAge()};
		return this.getJdbcTemplate().update(sql,params);
	}

	@Override
	public int updateStudent(Student student) {
		String sql="update t_student set name=?,age=? where id=?";
		Object []params=new Object[]{student.getName(),student.getAge(),student.getId()};
		return this.getJdbcTemplate().update(sql,params);
	}

	@Override
	public int deleteStudent(int id) {
		String sql="delete from t_student where id=?";
		Object []params=new Object[]{id};
		return this.getJdbcTemplate().update(sql,params);
	}

	@Override
	public List<Student> findStudents() {
		String sql="select * from t_student";
		final List<Student> studentList=new ArrayList<Student>();
		this.getJdbcTemplate().query(sql, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Student student=new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				studentList.add(student);
			}
			
		});
		return studentList;
	}

}
