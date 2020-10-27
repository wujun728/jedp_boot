/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.file.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jyl.file.model.FileInfo;
import com.jyl.file.service.FileInfoService;
import com.jyl.file.util.FileManagerException;
import com.jyl.util.servlet.HttpServletUtil;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月1日 下午5:08:26
 */
@Controller
@RequestMapping("/file")
public class FileInfoController {
	
	Logger log = Logger.getLogger(FileInfoController.class);
	
	@Autowired
	private FileInfoService fileInfoService;
	
	/**
	 * 文件上传
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param id
	 * @param type
	 * @throws FileManagerException
	 * @throws IOException
	 */
	@RequestMapping(value="/{id}/{type}/upload", method=RequestMethod.POST, consumes="multipart/form-data")
	public void upload(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Long id, @PathVariable String type) 
			throws FileManagerException, IOException{
		
		String responseStr = fileInfoService.processUpload(request, type, FileInfo.class.getName(), id);
		HttpServletUtil.writeObjectJSON2Response(response, responseStr);
	}
	
	/**
	 * 文件下载
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value="/download/{id}", method=RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long id) throws IOException{
		
		FileInfo fileInfo = fileInfoService.findOne(id);
		if(fileInfo == null){
			log.error("file[id="+id+"] is not exists.");
			return;
		}
		
		String fileName = fileInfo.getName();
		try {
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException", e);
			e.printStackTrace();
		}

		InputStream is = fileInfoService.readFileStream(fileInfo.getPath());
		if (is == null) {
			log.error("file[id="+id+"] is not exists.");
			return;
		}
		byte[] data = IOUtils.toByteArray(is);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType(fileInfo.getContenttype());
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(data);
	    outputStream.flush();
	    outputStream.close();  
	}
	
	/**
	 * 获取文件（图片）
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public void findOne(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long id) throws IOException{
		
		FileInfo fileInfo = fileInfoService.findOne(id);
		if(fileInfo == null){
			log.error("file[id="+id+"] is not exists.");
			return;
		}
		
		String fileName = fileInfo.getName();
		try {
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException", e);
			e.printStackTrace();
		}

		InputStream is = fileInfoService.readFileStream(fileInfo.getPath());
		if (is == null) {
			log.error("file[id="+id+"] is not exists.");
			return;
		}
		byte[] data = IOUtils.toByteArray(is);
		response.reset();
		response.setContentType(fileInfo.getContenttype());
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(data);
	    outputStream.flush();
	    outputStream.close();  
	}
	
	/**
	 * 删除文件信息
	 * @param request
	 * @param response
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long id) throws IOException{
		
		fileInfoService.deleteOne(id);
	}

}
