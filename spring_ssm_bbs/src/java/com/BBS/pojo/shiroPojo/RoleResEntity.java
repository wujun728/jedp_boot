package com.BBS.pojo.shiroPojo;

import com.BBS.pojo.common.commonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhanglq
 * @version 1.0
 * @date 2019/11/4 21:00
 * @info 角色-资源表，记录每个角色所有的资源权限
 */
@Entity
@Table(name = "role_res")
public class RoleResEntity extends commonEntity {


    private String roleId;   //角色id
    private String resId;   //资源权限ID

    @Column(name = "role_id",length = 5)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    @Column(name = "res_id",length = 5)
    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }
}
