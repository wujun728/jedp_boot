/**<p>Project: </p>
 * <p>Package:	com.qbt.adpter</p>
 * <p>File: FileUploadAdpter.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015-10-31-下午12:11:49</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.adpter;

/**<p>Class: FileUploadAdpter.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015-10-31 下午12:11:49
 * @version 1.0.0
 */
public interface FileUploadAdpter {

	
	/**
	 * 获取上传文件组件的Token
	 * <pre></pre>
	 * @return
	 */
	public String getToken();
	
	/**
	 *  Url前缀
	 * <pre></pre>
	 * @return
	 */
	public String getPath();
}
