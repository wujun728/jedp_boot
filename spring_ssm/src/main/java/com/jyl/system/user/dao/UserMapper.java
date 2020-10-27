package com.jyl.system.user.dao;

import java.util.List;

import com.jyl.system.user.model.User;

public interface UserMapper {
	
	User selectByPrimaryKey(Long id);
    
    User selectByUsername(String username);
    
    List<User> selectAllUser();
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int modifyPassword(User record);
}