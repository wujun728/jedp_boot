package com.wuzy.sys.dao;

import com.wuzy.sys.model.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 用户mapper
 * Created by wuzy
 * on 2016-12-31 12:49.
 */
public interface SysUserMapper{
    /**
     * 登陆
     * @param account
     * @param password
     * @return
     */
    SysUser login(@Param("account") String account, @Param("password") String password);

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
