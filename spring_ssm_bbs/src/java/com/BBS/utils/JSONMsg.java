package com.BBS.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by dyl on 2018/11/19.
 * 重构JSON对象类的方法
 */

public class JSONMsg {

    public static JSONObject getJSONErrMsg(JSONObject jso ,String Msg) {
        if (jso == null) {
            return null;

        }
        jso.put("success", false);
        jso.put("data", Msg);
        jso.put("code", -1);
        return jso;
    }

    public static JSONObject getJSONOkMsg(JSONObject jso, String Msg) {
        if (jso == null) {
            return null;
        }
        jso.put("success", true);
        jso.put("data", Msg);
        jso.put("code", 1);
        return jso;
    }

}
