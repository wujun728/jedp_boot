/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.bean</p>
 * <p>File: OfficeDocument.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.bean;

import java.io.IOException;
import java.io.OutputStream;

/**<p>Class: OfficeDocument.java</p>
 * <p>Description: 工作文档的总接口</p>
 * <pre>
 *       业务中的异常类
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public interface OfficeDocument {

	/**
	 * 
	 * <p>描述：工作文档写入到out流中</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param out 输出流
	 * @throws IOException
	 */
	void write(OutputStream out) throws IOException;
}
