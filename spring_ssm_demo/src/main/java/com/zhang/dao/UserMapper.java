package com.zhang.dao;

import java.util.List;

import com.zhang.model.User;

public interface UserMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


	List<User> getAll();


	List<User> getAll2();


	List<User> getAll3();
}