package zw.cc.cn.user.module;

import java.util.Date;

public class Permission {
    private Integer id;

    private Integer preCode;

    private String preName;

    private Date createDate;

    private Date updateDate;

    private Integer createUser;

    private Integer updateUser;

    private Integer logicState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPreCode() {
        return preCode;
    }

    public void setPreCode(Integer preCode) {
        this.preCode = preCode;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName == null ? null : preName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getLogicState() {
        return logicState;
    }

    public void setLogicState(Integer logicState) {
        this.logicState = logicState;
    }
}