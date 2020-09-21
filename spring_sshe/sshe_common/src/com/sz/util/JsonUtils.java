package com.sz.util;

import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * JSON ����Ĺ�����
 * 
 * <b>��Ŀ����: CommonVO </b><br/>
 * <b>������: </b><br/>
 * <b>�� �� ��: </b> zhouxj <br/>
 * <b>����ʱ��: </b> 2013-2-1 ����1:07:58 <br/>
 * <b>�� �� ��: </b><br/>
 * <b>�޸�ʱ��: </b><br/>
 * <b>�޸ı�ע: </b><br/>
 * <b>JDK �汾: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class JsonUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * ��Java����ת��ΪJSON���ݸ�ʽ
	 * 
	 * @param object
	 * @return
	 */
	public static String getJson(Object object) {
		try {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, object);
			String dataJson = strWriter.toString();
			return dataJson;
		} catch (Exception e) {
			// logger.error("[JsonUtils]Fail to getjson", e);
		}

		return null;
	}

	/**
	 * ��JSON���ݸ�ʽת��ΪJAVA����
	 * 
	 * @param <T>
	 * @param jsonData
	 * @param clz
	 * @return
	 */
	public static <T> T readValue(String jsonData, Class<T> clz) {
		try {
			return mapper.readValue(jsonData, clz);
		} catch (Exception e) {
			// logger.error("[JsonUtils]Fail to convert json to object: " +
			// jsonData, e);
			e.printStackTrace();
		}
		return null;
	}

	 
}
