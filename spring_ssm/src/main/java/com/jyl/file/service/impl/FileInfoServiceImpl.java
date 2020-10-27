/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.file.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.jyl.file.dao.FileInfoMapper;
import com.jyl.file.model.FileInfo;
import com.jyl.file.service.FileInfoService;
import com.jyl.file.util.FileManager;
import com.jyl.file.util.FileManagerException;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月1日 下午5:10:54
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private FileInfoMapper fileInfoMapper;

	@Override
	public String processUpload(HttpServletRequest request, String type, 
			String clazz, Long objectId) throws FileManagerException {
		
		String responseStr;
		List<FileInfo> files = null;
		if (fileManager.isMultipartContent(request)) {
			files = fileManager.uploadFile(request);
			if (files != null && files.size() > 0) {
				for (FileInfo file : files) {
					file.setType(type);
					file.setObjectclass(clazz);
					file.setObjectid(objectId);
					fileInfoMapper.insertSelective(file);
				}
			}
		}
		if (files != null && files.size() > 0) {
			String baseurl = "/download/";
			String message = "";
			for (FileInfo file : files) {
				message += baseurl + file.getId() + ",";
			}
			message = message.substring(0, message.length() - 1);
			responseStr = "{ \"error\" : 0, \"success\" : true,   \"url\" : \"" + message + "\"}";

		} else {
			responseStr = "{ \"error\" : 1, \"success\" : false,   \"message\" : \"no file uploaded\"}";
		}
		return responseStr;
	}

	@Override
	public FileInfo findOne(Long id) {
		Assert.notNull(id, "param id can not be null.");
		return fileInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public InputStream readFileStream(String path) {
		return fileManager.readFileStream(path);
	}

	@Override
	public int deleteOne(Long id) {
		Assert.notNull(id, "param id can not be null.");
		return fileInfoMapper.deleteByPrimaryKey(id);
	}

}
