/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.interceptor</p>
 * <p>File: JsonMessage.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.bean;

import java.io.Serializable;
import java.util.List;

/**<p>Class: JsonMessage.java</p>
 * <p>Description: json返回值对象</p>
 * <pre>
 *      用于与其他客户端交互的参数模型对象
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class JsonMessage implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3785403797067610151L;

	//参数编号
	private Integer code;
	
	//信息
	private String message;
	
	//每页记录数
	private Integer pageSize ;
	
	//总几页
	private Integer pageCount;
	
	//总记录数
	private Long total;
	
	//当前页
	private Integer pageNum;
	
	//其他参数集
	private Object obj;
	
	//记录参数集
	private List<?> rows;
	
	//是否有下一页
	private Boolean hasNext;


	public JsonMessage(){}
	
	
	public JsonMessage(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public Integer getPageCount() {
		return pageCount;
	}


	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}


	public Long getTotal() {
		return total;
	}


	public void setTotal(Long total) {
		this.total = total;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<?> getRows() {
		return rows;
	}


	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}


	public Boolean getHasNext() {
		if(getPageNum() == null || getPageCount() == null){
			return Boolean.FALSE;
		}
		if( getPageNum().intValue() < getPageCount().intValue() ){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}


	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}


	
	
	
}
