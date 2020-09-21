package cn.edu.nuc.Store.entity;

import cn.edu.nuc.Store.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insertUser(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectpassword(String username);
}