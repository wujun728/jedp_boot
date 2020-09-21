package com.youmeek.ssm.pojo;

/**
 * 接收信息实体类
 * @author frank.fang
 * date 16/4/20 15:09
 */
public class ReceiveEvent extends AbstractMybatisEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String devId;
    private String leftAir;
    private String leftTime;
    private String createTime;
    private String eventId;
    private Byte evacuateStatus;
    private Byte blue;
    private Byte power;
    private Byte hand;
    private Integer arivemini;
    private Integer arrivesec;
    private String arivepa;
    private String eventType;
    private String endTime;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId == null ? null : devId.trim();
    }

    public String getLeftAir() {
        return leftAir;
    }

    public void setLeftAir(String leftAir) {
        this.leftAir = leftAir == null ? null : leftAir.trim();
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime == null ? null : leftTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    public Byte getEvacuateStatus() {
        return evacuateStatus;
    }

    public void setEvacuateStatus(Byte evacuateStatus) {
        this.evacuateStatus = evacuateStatus;
    }

    public Byte getBlue() {
        return blue;
    }

    public void setBlue(Byte blue) {
        this.blue = blue;
    }

    public Byte getPower() {
        return power;
    }

    public void setPower(Byte power) {
        this.power = power;
    }

    public Byte getHand() {
        return hand;
    }

    public void setHand(Byte hand) {
        this.hand = hand;
    }

    public Integer getArivemini() {
        return arivemini;
    }

    public void setArivemini(Integer arivemini) {
        this.arivemini = arivemini;
    }

    public Integer getArrivesec() {
        return arrivesec;
    }

    public void setArrivesec(Integer arrivesec) {
        this.arrivesec = arrivesec;
    }

    public String getArivepa() {
        return arivepa;
    }

    public void setArivepa(String arivepa) {
        this.arivepa = arivepa == null ? null : arivepa.trim();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}