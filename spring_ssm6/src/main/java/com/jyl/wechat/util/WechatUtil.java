/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.util;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月3日 上午11:10:47
 */
public class WechatUtil {
	
	/**
	 * 排序
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String sort(String token, String timestamp, String nonce) {
		String[] strArray = {token, timestamp, nonce};
		Arrays.sort(strArray);
		StringBuilder sb = new StringBuilder();
		for (String str : strArray) {
			sb.append(str);
		}
		 
		return sb.toString();
	}
	
	/**
	 * sha1加密
	 * @param str
	 * @return
	 */
	public static String sha1(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 解析微信传送过来的xml消息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		
         Map<String, String> map = new HashMap<String, String>();
         // 从request中取得输入流
         InputStream inputStream = request.getInputStream();
         // 读取输入流
         SAXReader reader = new SAXReader();
         Document document = reader.read(inputStream);
         // 得到xml根元素
         Element root = document.getRootElement();
         // 得到根元素的所有子节点
         List<Element> elementList = root.elements();
 
         // 遍历所有子节点
         for (Element e : elementList) {
             //System.out.println(e.getName() + "|" + e.getText());
             map.put(e.getName(), e.getText());
         }
 
         inputStream.close();
         inputStream = null;
         return map;
	}

}
