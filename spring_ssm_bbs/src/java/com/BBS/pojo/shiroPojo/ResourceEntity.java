package com.BBS.pojo.shiroPojo;

import com.BBS.pojo.common.commonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhanglq
 * @version 1.0
 * @date 2019/11/4 21:47
 * @idfo 角色资源权限表
 */
@Entity
@Table(name = "shiro_resource")
public class ResourceEntity extends commonEntity {


    private String resName;  //资源权限名称

    @Column(name = "res_name",length = 25)
    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }
}
