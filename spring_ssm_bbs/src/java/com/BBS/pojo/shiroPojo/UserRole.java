package com.BBS.pojo.shiroPojo;

import com.BBS.pojo.common.commonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhanglq
 * @version 1.0
 * @date 2019/11/18 11:08
 * @info 用户角色表，记录每个用户所有的角色信息
 */
@Entity
@Table(name = "user_role")
public class UserRole  extends commonEntity {



    private String userId;  // 用户表ID
    private String roleId; //角色表ID

    @Column(name = "user_id",length = 5)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Column(name = "role_id",length = 5)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
