/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: EncryptUtil.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**<p>Class: EncryptUtil.java</p>
 * <p>Description: 加密算法类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class EncryptUtil {

	
	
	

	/***************************************不可逆算法******************************************/
	
	/**
	 * 用MD5算法+Hex算法集合进行加密
	 * @param str 需要加密的字符串
	 * @return MD5加密后的结果
	 */
	public static String md5Hex(String str) {
		return DigestUtils.md5Hex(str);
	}
	
	/**
	 * 
	 * <p>描述：MD5加密</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param str
	 * @return
	 */
	public static byte[] md5(String str){
		return DigestUtils.md5(str);
	}

	/**
	 * 用SHA算法进行加密
	 * @param str 需要加密的字符串
	 * @return SHA加密后的结果 256位
	 */
	public static String sha256Hex(String str) {
		return DigestUtils.sha256Hex(str);
	}
	
	/**
	 * 用SHA算法进行加密
	 * @param str 需要加密的字符串
	 * @return SHA加密后的结果 512位
	 */
	public static String sha512Hex(String str) {
		return DigestUtils.sha512Hex(str);
	}
	
	/**
	 * 用SHA算法进行加密
	 * @param str 需要加密的字符串
	 * @return SHA加密后的结果 384位
	 */
	public static String sha384Hex(String str) {
		return DigestUtils.sha384Hex(str);
	}
	
	/**
	 * 自定义的加密，密码加盐值
	 * 自定义的密码加密安全系数会更高点
	 * 采用MD5HEX加密
	 * @param str 字符串
	 * @param salt 盐值
	 * @return
	 */
	public static String customEncrypt(String str, String salt){
		return md5Hex(new StringBuffer(str).append(salt).toString());
	}
	
	/***************************************可逆算法******************************************/

	/**
	 * 用base64算法进行加密
	 * @param str 需要加密的字符串
	 * @return base64加密后的结果
	 */
	public static String encodeBase64(String str) {
		return new String(Base64.encodeBase64String(str.getBytes()));
	}
	
	/**
	 * 用base64算法进行解密
	 * @param str 需要解密的字符串
	 * @return base64解密后的结果
	 * @throws IOException 
	 */
	public static String decodeBase64(String str) {
		return new String(Base64.decodeBase64(str));
	}
	
	/***************************************密码匹配******************************************/
	
	/**
	 * 自定义密码匹配
	 * <pre></pre>
	 * @param str  未加密新密码串
	 * @param salt 盐值
	 * @param oldStr 老密码串
	 * @return
	 */
	public static boolean customMatchPwd(String str, String salt, String oldStr){
		if(StringUtils.isBlank(str)){
			return false;
		}
		return customEncrypt(str, salt).equals(oldStr);
	}
	
	/**
	 * 自定义密码匹配
	 * <pre></pre>
	 * @param str 加密新密码串
	 * @param oldStr 老密码串
	 * @return
	 */
	public static boolean matchPwd(String str, String oldStr){
		if(StringUtils.isBlank(str)){
			return false;
		}
		return md5Hex(str).equals(oldStr);
	}
	
}
