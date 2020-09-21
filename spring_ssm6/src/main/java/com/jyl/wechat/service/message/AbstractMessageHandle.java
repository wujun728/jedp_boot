/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.service.message;

import java.util.Map;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月5日 上午9:58:58
 */
public abstract class AbstractMessageHandle {
	
	public abstract String handle(Map<String,String> map);

}
