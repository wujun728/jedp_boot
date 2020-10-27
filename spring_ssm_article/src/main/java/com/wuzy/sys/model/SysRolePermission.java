package com.wuzy.sys.model;

import java.io.Serializable;

/**
 * 角色权限 实体类
 * Created by wuzy
 * on 2016-12-31 20:35.
 */
public class SysRolePermission implements Serializable{
    private Long id;
    private Long permissionId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
