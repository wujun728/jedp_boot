package cn.edu.nuc.Store.model;

public class BuyCar {
    private Integer carid;

    private Integer userid;

    private String totalmaney;

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTotalmaney() {
        return totalmaney;
    }

    public void setTotalmaney(String totalmaney) {
        this.totalmaney = totalmaney;
    }
}