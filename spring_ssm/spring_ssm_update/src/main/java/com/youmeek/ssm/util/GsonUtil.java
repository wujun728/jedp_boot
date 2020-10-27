package com.youmeek.ssm.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class GsonUtil {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static Gson getGson() {
		return new GsonBuilder().disableHtmlEscaping().setDateFormat(DATE_FORMAT).create();
	}
	
	
	public static String toJson(Object object) {
		return getGson().toJson(object);
	}
	
	public static <T> T toObject(String jsonStr, Class<T> classOfT) {
		return getGson().fromJson(jsonStr, classOfT);
	}
	
    public static <T> List<T> toList(String jsonStr, TypeToken<List<T>> token) {
    	return getGson().fromJson(jsonStr, token.getType());


    }
    
}
