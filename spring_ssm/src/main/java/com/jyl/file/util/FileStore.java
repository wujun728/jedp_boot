/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.file.util;

import java.io.File;
import java.io.InputStream;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月2日 上午9:19:58
 */
public interface FileStore {
	
	/**
	 * 
	 * @param name 文件名
	 * @param file 文件实例
	 * @return 路径
	 */
	public String createFile(String name,File file);
	/**
	 * 
	 * @param name 文件名
	 * @param is inputstream
	 * @return 路径
	 */
	public FInfo createFile(String name,InputStream is);
    /**
     * 
     * @param path 路径
     * @return 输出文件
     */

	public InputStream readFileStream(String path);

	/**
	 * 
	 * @param path 路径
	 * @return 是否删除成功，不返回原因
	 */
	public boolean deleteFile(String path);


	public class FInfo{
		private String path;
		private long size;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public long getSize() {
			return size;
		}
		public void setSize(long size) {
			this.size = size;
		}
	}

}
