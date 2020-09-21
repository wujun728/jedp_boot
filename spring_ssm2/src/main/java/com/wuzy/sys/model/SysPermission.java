package com.wuzy.sys.model;

import java.io.Serializable;

/**
 * 权限表 实体类
 * Created by wuzy
 * on 2016-12-31 20:24.
 */
public class SysPermission implements Serializable {
    private Long id;
    /** 标题 */
    private String title;
    /** 类型（0 菜单；1功能） */
    private Short type;
    /** 状态 */
    private Short status;
    /** 排序 */
    private Short sort;
    /** 权限编码*/
    private String permCode;
    /** 描述 */
    private String memo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
