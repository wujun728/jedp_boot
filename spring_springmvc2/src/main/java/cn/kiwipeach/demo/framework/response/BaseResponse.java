/*
 * Copyright 2017 KiWiPeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.framework.response;

import cn.kiwipeach.demo.enums.business.BusinessEnums;

/**
 * Create Date: 2018/01/17
 * Description: 分页请求参数
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class BaseResponse {

    public static final String DEFAULT_FAIL_CODE_VALUE = "SYS_99999";
    public static final String DEFAULT_FAIL_CODE_MESSAGE = "系统默认异常信息";
    public static final String DEFAULT_SUCCESS_VALUE = "1";
    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";
    public static final int DEFAULT_PAGENO = 1;
    public static final int DEFAULT_PAGESIZE = 10;
    public static final long DEFAULT_TOTALNUM = 0;

    /**
     * 是否为成功报文
     */
    private boolean isSuccess = false;
    /**
     * 错误码
     */
    private String code = DEFAULT_FAIL_CODE_VALUE;
    /**
     * 错误消息
     */
    private String message = DEFAULT_FAIL_CODE_MESSAGE;

    /**
     * 默认
     */
    public BaseResponse() {
    }

    /**
     * 成功或者失败构造
     *
     * @param isSuccess 状态
     */
    public BaseResponse(boolean isSuccess) {
        if (isSuccess) {
            this.isSuccess = isSuccess;
            this.code = DEFAULT_SUCCESS_VALUE;
            this.message = DEFAULT_SUCCESS_MESSAGE;
        } else {
            //use default code and message
        }
    }

    /**
     * 失败构造1
     *
     * @param code
     * @param message
     */
    public BaseResponse(String code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    /**
     * 失败构造2
     *
     * @param isSuccess
     * @param codeEnum
     */
    public BaseResponse(boolean isSuccess, BusinessEnums codeEnum) {
        this();
        setCode(codeEnum.getCode());
        setMessage(codeEnum.getMessage());
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
