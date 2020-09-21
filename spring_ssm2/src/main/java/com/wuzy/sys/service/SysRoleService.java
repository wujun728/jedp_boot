package com.wuzy.sys.service;

import java.util.Set;

/**
 * 系统角色Service
 * Created by wuzy
 * on 2017-01-01 22:01.
 */
public interface SysRoleService {
    /**
     * 通过userId查询该用户拥有的角色set
     * @param userId
     * @return
     */
    Set<String> getRoleListByUid(Long userId);
}
