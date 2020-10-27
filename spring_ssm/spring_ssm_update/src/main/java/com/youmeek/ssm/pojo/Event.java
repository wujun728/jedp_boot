package com.youmeek.ssm.pojo;

/**
 * 事件实体类
 * @author frank.fang
 * date 16/4/20 15:08
 */
public class Event extends AbstractMybatisEntity {

    private static final long serialVersionUID = 1L;

    private Integer eventId;
    private String eventName;
    private String eventNumber;
    private String eventRealName;
    private String eventAddress;
    private String eventDetail;
    private String eventcreatetime;
    private String eventendtime;
    private String syned;


    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public String getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(String eventNumber) {
        this.eventNumber = eventNumber == null ? null : eventNumber.trim();
    }

    public String getEventRealName() {
        return eventRealName;
    }

    public void setEventRealName(String eventRealName) {
        this.eventRealName = eventRealName == null ? null : eventRealName.trim();
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress == null ? null : eventAddress.trim();
    }

    public String getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail == null ? null : eventDetail.trim();
    }

    public String getEventcreatetime() {
        return eventcreatetime;
    }

    public void setEventcreatetime(String eventcreatetime) {
        this.eventcreatetime = eventcreatetime == null ? null : eventcreatetime.trim();
    }

    public String getEventendtime() {
        return eventendtime;
    }

    public void setEventendtime(String eventendtime) {
        this.eventendtime = eventendtime == null ? null : eventendtime.trim();
    }

    public String getSyned() {
        return syned;
    }

    public void setSyned(String syned) {
        this.syned = syned == null ? null : syned.trim();
    }
}