package com.youmeek.ssm.service.impl;

import com.youmeek.ssm.mapper.SysUserMapper;
import com.youmeek.ssm.pojo.SysUser;
import com.youmeek.ssm.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Resource
	private SysUserMapper sysUserMapper;


	@Override
	public SysUser getById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}
}
