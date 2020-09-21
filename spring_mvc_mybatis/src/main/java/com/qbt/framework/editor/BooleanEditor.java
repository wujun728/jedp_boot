/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.editor</p>
 * <p>File: BooleanEditor.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.editor;

import java.beans.PropertyEditorSupport;

/**<p>Class: BooleanEditor.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class BooleanEditor extends PropertyEditorSupport {

	private final static String TURE = "1";
	
	private final static String FALSE = "0";
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		switch (text) {
		case TURE:
			setValue(Boolean.TRUE);
			break;
		case FALSE:
			setValue(Boolean.FALSE);
			break;
		case "false":
			setValue(Boolean.FALSE);
			break;
		case "true":
			setValue(Boolean.TRUE);
			break;
		default:
			setValue(null);
			break;
		}
	}
	
	@Override
	public String getAsText() {
		return getValue().toString();
	}
}
