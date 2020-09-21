package cn.edu.nuc.Store.entity;

import cn.edu.nuc.Store.model.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminno);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminno);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

	Admin selectByAdminName(String adminname);
}