package com.BBS.pojo.common;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanglq
 * @version 1.0
 * @date 2019/11/4 21:30
 * 公用的实体类
 */
@MappedSuperclass
public class commonEntity  {
//    private static final long serialVersionUID = -6916560281850363248L;
//    @Id
    private String id;
    private Date createTime;
    private String createUserId;
    private String createUserName;
    private Date updateTime;
    private String updateUserId;
    private String updateUserName;

    @Id
    @GeneratedValue(
            generator = "paymentableGenerator"
    )
    @GenericGenerator(
            name = "paymentableGenerator",
            strategy = "uuid"
    )
    @Column(
            name = "ID",
            nullable = false,
            length = 32
    )
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(
            name = "create_time",
            length = 32
    )
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(
            name = "create_user_id",
            length = 32
    )
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    @Column(
            name = "create_user_uame",
            length = 32
    )
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    @Column(
            name = "update_time",
            length = 32
    )
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @Column(
            name = "update_user_id",
            length = 32
    )
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    @Column(
            name = "update_user_name",
            length = 32
    )
    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
