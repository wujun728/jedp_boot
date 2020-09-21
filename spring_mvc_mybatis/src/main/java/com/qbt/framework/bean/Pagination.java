/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.bean</p>
 * <p>File: Pagination.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月27日-下午4:10:20</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.bean;

import org.apache.commons.lang3.StringUtils;

import com.qbt.framework.constant.Constant;

/**<p>Class: Pagination.java</p>
 * <p>Description: 分页对象</p>
 * <pre>
 *      为了配合Mapper 
 * </pre>
 * @author 鲍建明
 * @date 2015年8月27日 下午4:10:20
 * @version 1.0.0
 */
public class Pagination {

	private Integer pageNow;
	private Integer pageSize;
	private String  order;
	private Long lastUpdateTime = 0L;
	private Sort sort;
	
	private Integer limitStart;
	
	private Integer limitEnd;
	
	
	public Pagination(){
		this(Constant.DEFAULT_PAGENOW, Constant.DEFAULT_PAGESIZE);
	}
	
	public Pagination(int pageNow, int pageSize){
		this(pageNow, pageSize, null);
	}
	
	public Pagination(int pageNow, int pageSize, String order){
		setPageNow(pageNow);
		setPageSize(pageSize);
		setOrder(order);
	}
	
	
	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		if( pageNow == null || pageNow <= 0){
			this.pageNow = Constant.DEFAULT_PAGENOW;
		} else {
			this.pageNow = pageNow;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		if(pageSize == null || pageSize <= 0){
			this.pageSize = Constant.DEFAULT_PAGESIZE;
		} else {
			this.pageSize = pageSize;
		}
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
	public String toOrderString(){
		if( StringUtils.isNotBlank(order) ){
			return order;
		}
		if(sort != null){
			return sort.toString();
		}
		return "";
	}
	
	/**
	 * 设置排序字段的前缀
	 * <pre></pre>
	 * @param prefix
	 */
	public void setSortPrefix(String prefix){
		if( sort != null){
			sort.setFiledPrefix(prefix);
		}
	}

	/**
	 * 数据库分页开始
	 * <pre></pre>
	 * @return
	 */
	public Integer getLimitStart() {
		this.limitStart = (this.pageNow -1 ) * this.pageSize;
		return  this.limitStart;
	}

	/**
	 * 数据库分页结束
	 * <pre></pre>
	 * @return
	 */
	public Integer getLimitEnd() {
		this.limitEnd = this.pageSize;
		return this.limitEnd;
	}
	
	
}
