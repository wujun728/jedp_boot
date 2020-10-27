package com.wuzy.sys.service.impl;

import com.wuzy.sys.dao.SysUserMapper;
import com.wuzy.sys.model.SysUser;
import com.wuzy.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuzy
 * on 2017-01-01 21:52.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 登陆
     *
     * @param account
     * @param password
     * @return
     */
    public SysUser login(String account, String password) {
        return sysUserMapper.login(account,password);
    }

    /**
     * 添加用户
     *
     * @param sysUser
     * @return
     */
    public Integer add(SysUser sysUser) {
        return sysUserMapper.add(sysUser);
    }

    /**
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    public Integer updateById(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public Integer deleteById(Long id) {
        return sysUserMapper.deleteById(id);
    }

    /**
     * 通过账号查询用户
     *
     * @param account
     * @return
     */
    public SysUser getByAccount(String account) {
        return sysUserMapper.getByAccount(account);
    }
}
