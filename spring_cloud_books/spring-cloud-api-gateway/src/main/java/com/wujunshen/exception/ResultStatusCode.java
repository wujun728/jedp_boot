package com.wujunshen.exception;


/**
 * User:frankwoo(吴峻申) <br>
 * Date:2016-11-3 <br>
 * Time:13:48 <br>
 * Mail:frank_wjs@hotmail.com <br>
 */
public enum ResultStatusCode {
    OK(200, "成功"),
    PARAMETER_ERROR(108, "参数错误"),
    DATA_QUERY_ERROR(99, "查询数据失败"),
    DATA_UPDATED_ERROR(100, "更新数据失败"),
    DATA_DELETED_ERROR(101, "删除数据失败"),
    DATA_INPUT_ERROR(102, "数据未输入"),
    INVALID_CLIENT_ID(300, "clientID无效"),
    INVALID_USER_NAME(301, "用户名错误"),
    INVALID_PASSWORD(302, "密码错误"),
    INVALID_TOKEN(303, "access_token无效"),
    NO_AUTHORIZATION(304, "无Authorization传入");

    // 成员变量
    private int code;
    private String message;

    /**
     * 构造方法
     *
     * @param code    错误码
     * @param message 错误消息
     */
    ResultStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return 返回成员变量值
     */
    public int getCode() {
        return code;
    }

    /**
     * @return 返回成员变量值
     */
    public String getMessage() {
        return message;
    }
}