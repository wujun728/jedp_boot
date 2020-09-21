package com.wuzy.sys.service;

import com.wuzy.sys.model.SysUser;

/**
 * 系统用户 Service
 * Created by wuzy
 * on 2017-01-01 21:50.
 */

public interface SysUserService {
    /**
     * 登陆
     * @param account
     * @param password
     * @return
     */
    SysUser login(String account, String password);

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    Integer add(SysUser sysUser);

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    Integer updateById(SysUser sysUser);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 通过账号查询用户
     * @param account
     * @return
     */
    SysUser getByAccount(String account);

}
