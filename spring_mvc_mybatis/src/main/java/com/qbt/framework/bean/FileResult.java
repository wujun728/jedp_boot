/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.interceptor</p>
 * <p>File: FileResult.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.bean;

import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;

import com.qbt.framework.bean.Multipart.FileType;

/**<p>Class: FileResult.java</p>
 * <p>Description: 文件上传后返回的结果对象</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class FileResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5064212143390282808L;

	private String uri;					//文件上传路径
	
	private String fileName; 			//文件名
	
	private FileType fileType;			//文件类型
	
	
	
	

	public FileResult(String uri, String fileName, 
			FileType fileType) {
		super();
		this.uri = uri;
		this.fileName = fileName;
		this.fileType = fileType;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 
	 * <p>描述：文件后缀名</p>
	 * <pre>
	 *    
	 * </pre>
	 * @return
	 */
	public String getSuffixName() {
		return FilenameUtils.getExtension(this.fileName);
	}


	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	
	
}
