package com.youmeek.ssm.model;

import java.util.Map;

public class TableModel {

	private int pageSize;//每页显示的记录数
	private int pageNumber;//当前第几页




    private int start;  //从第几条开始
    private String sort;//排序列
    private String order;//排序规则(asc,desc)
    private Map<String,String> params;//查询条件

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getStart() {
		return (pageNumber-1)*pageSize;
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
