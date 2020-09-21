/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: RandomUtil.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月27日-上午8:56:42</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**<p>Class: RandomUtil.java</p>
 * <p>Description: 随机生成工具类</p>
 * <pre>
 * </pre>
 * @author 鲍建明
 * @date 2015年8月27日 上午8:56:42
 * @version 1.0.0
 */
public class RandomUtil {
	
	private static final String[] NO_SHUFFLE = new String[] {"1", "2","3","4","5","6","7", 
			 "8","9"}; 
	private static final String[] ALL_SHUFFLE = new String[] {"1", "2","3","4","5","6","7", 
		 "8","9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "N", "M", "O", "P", "Q", "R", "S","T","U", "V", "W","X", "Y", "Z"}; 

	private RandomUtil(){}
	
	/**
	 * 
	 * <p>描述：随机生成一个32UUID </p>
	 * <pre>
	 *    
	 * </pre>
	 * @return 大写返回
	 */
	public static String buildUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 随机生成指定长度的随机数
	 * <pre></pre>
	 * @return
	 */
	public static String buildCode(boolean isNo, int length) {
		List<String> list = null;
		if(isNo){
			list = Arrays.asList(NO_SHUFFLE);
		} else {
			list = Arrays.asList(ALL_SHUFFLE);
		}
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(list.get(i));
		}
		return sb.toString();
	}
	
	/**
	 * 生成4位验证码
	 * <pre></pre>
	 * @param isNo
	 * @return
	 */
	public static String buildCode(boolean isNo){
		return buildCode(isNo, 4);
	}
}
