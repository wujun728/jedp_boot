package com.wuzy.sys.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户 实体类
 * Created by wuzy
 * on 2016-12-31 12:33.
 */
public class SysUser implements Serializable{

    private Long id;
    /** 登陆账户 */
    private String account;
    /** 用户名称 */
    private String nickName;
    /** 用户密码 */
    private String password;
    /** 用户状态 */
    private Short status;
    /** 创建时间 */
    private Date createTime;
    /** 创建人(userID) */
    private Long createUser;
    /** 出生日期 */
    private Date birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String niceName) {
        this.nickName = niceName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
