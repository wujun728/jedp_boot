/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.base</p>
 * <p>File: BaseController.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.qbt.framework.editor.BooleanEditor;
import com.qbt.framework.editor.ByteEditor;
import com.qbt.framework.editor.CharacterEditor;
import com.qbt.framework.editor.DoubleEditor;
import com.qbt.framework.editor.FloatEditor;
import com.qbt.framework.editor.IntegerEditor;
import com.qbt.framework.editor.LongEditor;
import com.qbt.framework.editor.ShortEditor;
import com.qbt.framework.editor.StringEditor;



/**<p>Class: BaseController.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public abstract class BaseController {
	
	/**
	 * 开启防XSS攻击
	 * <pre></pre>
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
	     binder.registerCustomEditor(Boolean.class, new BooleanEditor());
	     binder.registerCustomEditor(String.class, new StringEditor());
	     binder.registerCustomEditor(Byte.class, new ByteEditor());
	     binder.registerCustomEditor(Character.class, new CharacterEditor());
	     binder.registerCustomEditor(Double.class, new DoubleEditor());
	     binder.registerCustomEditor(Float.class, new FloatEditor());
	     binder.registerCustomEditor(Integer.class, new IntegerEditor());
	     binder.registerCustomEditor(Long.class, new LongEditor());
	     binder.registerCustomEditor(Short.class, new ShortEditor());
	     
	}
	
	
	
	

	
}
