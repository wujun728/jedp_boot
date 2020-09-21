package com.wuzy.sys.service;

import java.util.Set;

/**
 * 系统权限
 * Created by wuzy
 * on 2017-01-01 22:06.
 */
public interface SysPermissionService {
    /**
     * 通过用户id查询用户所拥有的权限
     * @param userId
     * @return
     */
    Set<String> getPermCodeByUid(Long userId);
}
