package com.wuzy.sys.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wuzy
 * on 2016-12-31 15:59.
 */
public class SysRole implements Serializable {

    private Long id;
    /** 角色名称 */
    private String roleName;
    /** 角色代码 */
    private String roleCode;
    /** 备注 */
    private String memo;
    /** 排序 */
    private Short roleSort;
    /** 创建时间 */
    private Date createTime;
    /** 创建人（userId） */
    private Long createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMemo() {
        return memo ;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Short getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Short roleSort) {
        this.roleSort = roleSort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
}
