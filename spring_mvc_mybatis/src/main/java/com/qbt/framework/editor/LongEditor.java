/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.editor</p>
 * <p>File: LongEditor.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.editor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

/**<p>Class: LongEditor.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class LongEditor extends PropertiesEditor {

	@Override
	public String getAsText() {
		Long value = (Long)getValue();
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
				setValue(Long.parseLong(arg0));
			}catch(Exception e){
				setValue(null);
			}
		}
	}
}
