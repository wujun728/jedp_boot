/**<p>Project: </p>
 * <p>Package:	com.qbt.adpter.impl</p>
 * <p>File: QiNiuFileUploadAdpter.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015-10-31-下午12:12:22</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.adpter.impl;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.qbt.framework.adpter.FileUploadAdpter;
import com.qbt.framework.constant.Constant;
import com.qiniu.util.Auth;

/**<p>Class: QiNiuFileUploadAdpter.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015-10-31 下午12:12:22
 * @version 1.0.0
 */
public class QiNiuFileUploadAdpter implements FileUploadAdpter{
	
	private Auth auth;
	
	private String bucket;
	
	private Resource location; 
	
	private Properties properties;
	
	

	public void setLocation(Resource location) throws IOException {
		this.location = location;
		Properties properties = new Properties();
		PropertiesLoaderUtils.fillProperties(properties, new EncodedResource(this.location, Constant.UTF8));
		loadProperties(properties);
	}
	
	protected void loadProperties(Properties properties){
		this.properties = properties;
		String accessKey = properties.getProperty("accessKey");
		String secretKey = properties.getProperty("secretKey");
		bucket = properties.getProperty("bucket");
		auth = Auth.create(accessKey, secretKey);
	}
	

	@Override
	public String getToken() {
		if(auth == null){
			throw new NullPointerException("please init qiniu.properties ");
		}
		return auth.uploadToken(bucket);
	}

	@Override
	public String getPath() {
		return properties.getProperty("path");
	}

	
	
	
}
