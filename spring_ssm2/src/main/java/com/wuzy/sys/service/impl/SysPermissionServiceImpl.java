package com.wuzy.sys.service.impl;

import com.wuzy.sys.dao.SysPermissionMapper;
import com.wuzy.sys.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by wuzy
 * on 2017-01-01 22:06.
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    /**
     * 通过用户id查询用户所拥有的权限
     *
     * @param userId
     * @return
     */
    public Set<String> getPermCodeByUid(Long userId) {
        return sysPermissionMapper.getPermCodeByUid(userId);
    }
}
