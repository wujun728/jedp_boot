/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.file.util;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jyl.file.model.FileInfo;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月2日 上午9:18:13
 */
public interface FileManager {
	
	public List<FileInfo> uploadFile(HttpServletRequest request) throws FileManagerException;

	public InputStream readFileStream(String path);
	
	public boolean deletefile(String path);

	public FileStore getStore();

	public void setStore(FileStore store);
	
	public long getMaxSize();
	
	public void setMaxSize(long maxSize);
	
	public String getWorkDirectory();
	
	public void setWorkDirectory(String dir);

	public boolean isMultipartContent(HttpServletRequest request);

}
