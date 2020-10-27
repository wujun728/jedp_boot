package com.wuzy.sys.model;

import java.io.Serializable;

/**
 * 用户角色关联 实体类
 * Created by wuzy
 * on 2016-12-31 16:17.
 */
public class SysUserRole implements Serializable{
    private Long id;
    private Long userId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
