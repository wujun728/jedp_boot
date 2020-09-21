package com.wuzy.sys.dao;

import java.util.Set;

/**
 * 用户权限 mapper
 * Created by wuzy
 * on 2016-12-31 20:46.
 */
public interface SysPermissionMapper {
    /**
     * 通过用户id查询用户所拥有的权限
     * @param userId
     * @return
     */
    Set<String> getPermCodeByUid(Long userId);

}
