package com.wuzy.sys.utils;

import java.io.Serializable;

/**
 * ajax返回结果集 对象
 * Created by wuzy
 * on 2017-01-12 22:05.
 */
public class Result<T> implements Serializable{
    /** 返回状态 */
    private boolean code;
    /** 返回信息 */
    private String message;
    /** 返回数据 */
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public Result(boolean code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}
