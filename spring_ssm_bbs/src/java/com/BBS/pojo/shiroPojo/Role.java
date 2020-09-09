package com.BBS.pojo.shiroPojo;

import com.BBS.pojo.common.commonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhanglq
 * @version 1.0
 * @date 2019/11/4 22:07
 * @info 用户角色表
 */
@Entity
@Table(name = "role")
public class Role extends commonEntity {


    private String roleName;  // 角色名称


    @Column(name = "role_name",length = 10)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
