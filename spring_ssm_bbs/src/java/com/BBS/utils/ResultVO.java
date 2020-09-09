package com.BBS.utils;

/**
 * Created by zlq on 2019/1/21.
 */
public class ResultVO {
    public final static String SUCCESS = "success";

    public final static String FAIL = "fail";

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 正确编码
     */
    private String successCode;

    /**
     * 错误描述
     */
    private String errorMsg;

    /**
     * 正确描述
     */
    private String successMsg;


    /**
     * 返回结果
     */
    private Object result;


    public ResultVO(){

    }

    /**
     * 构造函数
     * @param errorCode
     * @param errorMsg
     */

    public ResultVO(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 构造函数
     * @param errorCode
     * @param errorMsg
     * @param result
     */

    public ResultVO(String errorCode, String errorMsg, Object result) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.result = result;
    }

    /**
     * 返回 result 的值
     * @return result
     */

    public Object getResult() {
        return result;
    }

    /**
     * 设置 result 的值
     * @param result
     */

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 返回 errorCode 的值
     * @return errorCode
     */

    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置 errorCode 的值
     * @param errorCode
     */

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 返回 errorMsg 的值
     * @return errorMsg
     */

    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 设置 errorMsg 的值
     * @param errorMsg
     */

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 返回 successCode 的值
     * @return successCode
     */
    public String getSuccessCode() {
        return successCode;
    }

    /**
     * 设置 successCode 的值
     * @param successCode
     */
    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    /**
     * 返回 successMsg 的值
     * @return successMsg
     */
    public String getSuccessMsg() {
        return successMsg;
    }

    /**
     * 设置 successMsg 的值
     * @param successMsg
     */
    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
}
