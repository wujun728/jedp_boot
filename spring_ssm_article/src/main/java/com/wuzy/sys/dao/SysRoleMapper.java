package com.wuzy.sys.dao;

import java.util.Set;

/**
 * 角色 mapper
 * Created by wuzy
 * on 2016-12-31 20:38.
 */
public interface SysRoleMapper {
    /**
     * 通过userId查询该用户拥有的角色set
     * @param userId
     * @return
     */
    Set<String> getRoleListByUid(Long userId);

}
