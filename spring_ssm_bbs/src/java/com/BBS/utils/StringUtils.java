package com.BBS.utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by dyl on 2018/12/17.
 */
public class StringUtils {
    private static final Logger LOGGER = Logger.getLogger(StringUtils.class);

    /**
     * 前端提交参数转为MAP，参数格式：param1=1&param2=2&page=1&rows=10
     *
     * @param params
     * @return
     */
    public static HashMap paramsToMap(String params) {
        try {
            params = java.net.URLDecoder.decode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage());

        }
        HashMap<String, Object> map = new HashMap<>();

        String[] strings = params.split("&");
        for (String ss : strings) {
            String[] sss = ss.split("=");
            if (sss.length > 1) {
                map.put(sss[0], sss[1]);
            }
        }
        return map;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.equals("");
    }

    public static boolean equals(String s1, String s2) {
        if (isEmpty(s1) && isEmpty(s2)) {
            return true;
        } else {
            return (!isEmpty(s1) && !isEmpty(s2)) && s1.equals(s2);
        }
    }

    //获取UUID
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }

    //获得指定数量的UUID
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
