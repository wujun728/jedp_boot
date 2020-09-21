package cn.edu.nuc.Store.model;

public class OrderList {
    private Integer orderid;

    private Integer goodid;

    private Integer carid;

    private Integer buynum;

    private Integer buystate;

    private String buydesc;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }

    public Integer getBuystate() {
        return buystate;
    }

    public void setBuystate(Integer buystate) {
        this.buystate = buystate;
    }

    public String getBuydesc() {
        return buydesc;
    }

    public void setBuydesc(String buydesc) {
        this.buydesc = buydesc;
    }
}