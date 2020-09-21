package com.youmeek.ssm.pojo;

import java.util.Date;

/**
 * 事件详细是实体类
 * @Author frank.fang
 * @Date 2016/4/26 13:40
 */
public class EventDetail extends AbstractMybatisEntity {

    private static final long serialVersionUID = 1L;

    private Integer detailId;

    private String leftAir;

    private Date detailTime;

    private Integer firemanId;

    private Integer eventId;

    /**
     * 消防員對應姓名
     */
    private String name;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getLeftAir() {
        return leftAir;
    }

    public void setLeftAir(String leftAir) {
        this.leftAir = leftAir == null ? null : leftAir.trim();
    }

    public Date getDetailTime() {
        return detailTime;
    }

    public void setDetailTime(Date detailTime) {
        this.detailTime = detailTime;
    }

    public Integer getFiremanId() {
        return firemanId;
    }

    public void setFiremanId(Integer firemanId) {
        this.firemanId = firemanId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}