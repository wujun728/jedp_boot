/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.json;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON相关转换工具类
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年5月30日 下午2:48:52
 */
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();
    
	public static String bean2Json(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        mapper.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass)
            throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(jsonStr, objClass);
    }
    
    /*public static JSONObject toJSONObject(Object o,
			String dateFormat) {
		try {
			JsonConfig config = new JsonConfig();
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			config.registerJsonValueProcessor(Date.class,
					new DateJsonValueProcessor(dateFormat));
			config.registerJsonValueProcessor(java.sql.Date.class,
					new DateJsonValueProcessor(dateFormat));
			// 注意不支持子类
			config.registerJsonValueProcessor(Timestamp.class,
					new DateJsonValueProcessor(dateFormat));
			return JSONObject.fromObject(o, config);
		} catch (Exception e) {
			LogUtil.error(e);
			return new JSONObject();
		}
	}*/
	
}
