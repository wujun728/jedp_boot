/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.bean</p>
 * <p>File: Sort.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015-10-22-上午9:40:37</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.bean;


/**<p>Class: Sort.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015-10-22 上午9:40:37
 * @version 1.0.0
 */
public class Sort {
	
	
	private String filed;
	private String order;
	private String filedPrefix;
	
	public Sort(){}
	
	public Sort(String filed, SortType type){
		this.filed = filed;
		setOrder(type);
	}
	
	public Sort(String filed, String order){
		this.filed = filed;
		setOrder(order);
	}
	
	public enum SortType{
		DESC, 
		ASC
	}
	
	
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(SortType type) {
		String o = type.toString();
		if( o.equalsIgnoreCase("desc")){
			this.order = "desc";
		} else if( o.equalsIgnoreCase("asc")){
			this.order = "asc";
		}
	}
	
	public void setOrder(String order) {
		if( SortType.DESC.toString().equalsIgnoreCase(order)){
			this.order = "desc";
		} else if( SortType.ASC.toString().equalsIgnoreCase(order)){
			this.order = "asc";
		} else {				//默认情况
			this.order = "asc";
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(  filed + " " + order);
		return sb.toString();
	}

	public String getFiledPrefix() {
		return filedPrefix;
	}

	public void setFiledPrefix(String filedPrefix) {
		this.filedPrefix = filedPrefix;
	}
	

}
