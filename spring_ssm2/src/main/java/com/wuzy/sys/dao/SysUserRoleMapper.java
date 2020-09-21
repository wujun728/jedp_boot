package com.wuzy.sys.dao;

import com.wuzy.sys.model.SysUserRole;

import java.util.List;

/**
 * 用户角色关联 mapper
 * Created by wuzy
 * on 2016-12-31 16:19.
 */
public interface SysUserRoleMapper {
    List<Long> getRolesByUserId(Long userId);
    List<Long> getUserIdsByRoleId(Long roleId);
    Integer add(SysUserRole sysUserRole);
    Integer deleteByUserId(Long userId);
    Integer deleteByRole(Long roleId);


}
