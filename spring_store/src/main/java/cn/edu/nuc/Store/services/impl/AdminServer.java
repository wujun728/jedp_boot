package cn.edu.nuc.Store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nuc.Store.entity.AdminMapper;
import cn.edu.nuc.Store.model.Admin;
import cn.edu.nuc.Store.services.interfaces.AdminInf;
@Service
public class AdminServer implements AdminInf{
	@Autowired
	public AdminMapper adminMapper;
	@Override
	public Admin select(String adminname) {
		// TODO Auto-generated method stub
		return adminMapper.selectByAdminName(adminname);
	}

}
