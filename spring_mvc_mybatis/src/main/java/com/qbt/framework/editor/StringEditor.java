/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.editor</p>
 * <p>File: StringEditor.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.qbt.framework.core.DynamicDataSource;

/**<p>Class: StringEditor.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class StringEditor  extends PropertyEditorSupport {

	protected static Logger logger = LogManager.getLogger(StringEditor.class);
	private final static Whitelist user_content_filter = Whitelist.relaxed();
	static {
		user_content_filter.addTags("embed","object","param","span","div");
		user_content_filter.addAttributes(":all", "style", "class", "id", "name");
		user_content_filter.addAttributes("object", "width", "height","classid","codebase");	
		user_content_filter.addAttributes("param", "name", "value");
		user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");
	}
	
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		logger.info(text);
		if (StringUtils.isBlank(text)){
			setValue(null);
		} else {
			text = Jsoup.clean(text, user_content_filter);
			setValue(text);					// 这句话是最重要的，他的目的是通过传入参数的类型来匹配相应的databind
		}
	}
	
	@Override
	public String getAsText() {
		return getValue().toString();
	}
}
