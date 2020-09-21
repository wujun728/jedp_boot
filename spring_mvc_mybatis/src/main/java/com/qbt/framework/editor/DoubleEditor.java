/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.editor</p>
 * <p>File: DoubleEditor.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;

/**<p>Class: DoubleEditor.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class DoubleEditor extends PropertyEditorSupport {
	
	@Override
	public String getAsText() {
		Double value = (Double)getValue();
		if(value == null){
			return null;
		}
		return value.toString();
	}
	
	@Override
	public void setAsText(String arg0) throws IllegalArgumentException {
		if(StringUtils.isBlank(arg0)){
			setValue(null);
		}else{
			try{
				setValue(Double.valueOf(arg0));
			}catch(Exception e){
				setValue(null);
			}
		}
	}
}
