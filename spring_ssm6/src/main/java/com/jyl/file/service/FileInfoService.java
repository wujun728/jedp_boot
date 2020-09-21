/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.file.service;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.jyl.file.model.FileInfo;
import com.jyl.file.util.FileManagerException;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月1日 下午5:09:52
 */
public interface FileInfoService {

	/**
	 * 上传文件
	 * @param request
	 * @param type
	 * @param clazz
	 * @param objectId
	 * @return
	 * @throws FileManagerException
	 */
	String processUpload(HttpServletRequest request, String type, 
			String clazz, Long objectId) throws FileManagerException;
	
	/**
	 * 根据ID获取文件信息
	 * @param id
	 * @return
	 */
	FileInfo findOne(Long id);
	
	/**
	 * 根据路径读取文件流
	 * @param path
	 * @return
	 */
	InputStream readFileStream(String path);
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	int deleteOne(Long id);
	
}
