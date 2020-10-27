package com.youmeek.ssm.pojo;

/**
 * 消防员实体类
 * @author frank.fang
 * date 16/4/20 15:08
 */
public class Fireman extends AbstractMybatisEntity {

    private static final long serialVersionUID = 1L;

    private Integer firemanId;
    private String name;
    private String type;
    private String factValue;
    private String macVaule;
    private String avatar;
    private String team;

    public Integer getFiremanId() {
        return firemanId;
    }

    public void setFiremanId(Integer firemanId) {
        this.firemanId = firemanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFactValue() {
        return factValue;
    }

    public void setFactValue(String factValue) {
        this.factValue = factValue == null ? null : factValue.trim();
    }

    public String getMacVaule() {
        return macVaule;
    }

    public void setMacVaule(String macVaule) {
        this.macVaule = macVaule == null ? null : macVaule.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team == null ? null : team.trim();
    }
}