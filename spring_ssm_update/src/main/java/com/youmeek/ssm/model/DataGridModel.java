package com.youmeek.ssm.model;

import java.util.Map;

public class DataGridModel {


	private int rows;//每页显示的记录数 
    private int page;//当前第几页  
    private int start;  //从第几条开始
    private String sort;//排序列
    private String order;//排序规则(asc,desc)
    private Map<String,String> params;//查询条件

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStart() {
		return (page-1)*rows;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
}
