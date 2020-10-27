package com.youmeek.ssm.pojo;

/**
 * 小组实体类
 * @author frank.fang
 * date 16/4/20 15:10
 */
public class Team extends AbstractMybatisEntity {

    private static final long serialVersionUID = 1L;

    private Integer teamId;
    private String teamName;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }
}