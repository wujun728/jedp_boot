package cn.edu.nuc.Store.model;

public class Good {
    private Integer goodid;

    private String goodname;

    private Integer goodno;

    private Float goodprice;

    private Integer goodnum;
    
    private String goodimg;

    
    public String getGoodimg() {
		return goodimg;
	}

	public void setGoodimg(String goodimg) {
		this.goodimg = goodimg;
	}

	public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public Integer getGoodno() {
        return goodno;
    }

    public void setGoodno(Integer goodno) {
        this.goodno = goodno;
    }

    public Float getGoodprice() {
        return goodprice;
    }

    public void setGoodprice(Float goodprice) {
        this.goodprice = goodprice;
    }

    public Integer getGoodnum() {
        return goodnum;
    }

    public void setGoodnum(Integer goodnum) {
        this.goodnum = goodnum;
    }
}