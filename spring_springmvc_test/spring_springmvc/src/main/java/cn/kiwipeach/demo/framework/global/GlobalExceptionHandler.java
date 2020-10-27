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
package cn.kiwipeach.demo.framework.global;

import cn.kiwipeach.demo.framework.exception.BusinessException;
import cn.kiwipeach.demo.framework.response.SimpleResponse;
import cn.kiwipeach.demo.framework.response.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * Create Date: 2018/01/16
 * Description: 全局异常处理器
 *
 * @author kiwipeach [1099501218@qq.com]
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    public static final String DEFAULT_SYSCODE_EXCEPTION_VALUE = "SYS_10000";
    public static final String DEFAULT_BUSCODE_EXCEPTION_VALUE = "BUS_10000";

    /**
     * 业务异常处理器
     *
     * @param req HTTPRequest请求
     * @param ex  异常信息
     * @return 返回统一信息
     * @throws Exception 异常
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public SimpleResponse<Object> businessExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        SimpleResponse<Object> responseResult = null;
        // 1.业务异常,使用业务编码和业务信息进行显示
        BusinessException businessException = (BusinessException) ex;
        String businessCode = businessException.getCode();
        StringBuffer exParams = new StringBuffer();
        Object[] args = businessException.getArgs();
        if (args != null) {
            for (Object arg : args) {
                exParams.append(arg.toString());
            }
        }
        String paramMsg = exParams == null ? "" : exParams.toString();
        String businessMessage = businessException.getMessage() + paramMsg;
        //1.1 业务异常含有编码信息
        if (businessCode != null) {
            responseResult = ResponseUtil.simpleFail(businessCode, businessMessage);
            //1.2 业务异常没有编码信息
        } else {
            responseResult = ResponseUtil.simpleFail(DEFAULT_BUSCODE_EXCEPTION_VALUE, businessMessage);
        }
        logger.error("系统业务BUG异常:", ex);
        //2.业务异常将不会返回数据给前台
        responseResult.setData(new Object());
        return responseResult;
    }

    /**
     * 控制层方法调用参数转换异常TypeMismatchException为参数转换相关的父类异常,主要具体的实现类有：
     * MethodArgumentConversionNotSupportedException、MethodArgumentConversionNotSupportedException
     *
     * @param req HTTPRequest请求
     * @param ex  异常信息
     * @return 返回统一信息
     * @throws Exception 异常
     */
    @ExceptionHandler(value = {TypeMismatchException.class})
    @ResponseBody
    public SimpleResponse<Object> controllerExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        SimpleResponse<Object> responseResult = null;
        StringBuffer paramInfo = new StringBuffer();
        if (ex instanceof MethodArgumentConversionNotSupportedException) {
            MethodArgumentConversionNotSupportedException notSupportedException = (MethodArgumentConversionNotSupportedException) ex;
            paramInfo.append("name:").append(notSupportedException.getName()).append("value:").append(notSupportedException.getValue());
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException methodTypeMismatchException = (MethodArgumentTypeMismatchException) ex;
            paramInfo.append("name:").append(methodTypeMismatchException.getName()).append(" value:").append(methodTypeMismatchException.getValue());
        }
        logger.error("控制层参数转换异常:", ex);
        responseResult = ResponseUtil.simpleFail(DEFAULT_SYSCODE_EXCEPTION_VALUE, "控制层用参数转换异常[" + paramInfo.toString() + "]:" + ex.getMessage());
        responseResult.setData(new Object());
        return responseResult;
    }

    /**
     * 系统异常处理器
     *
     * @param req HTTPRequest请求
     * @param ex  异常信息
     * @return 返回统一信息
     * @throws Exception 异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public SimpleResponse<Exception> systemExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        SimpleResponse<Exception> responseResult = null;
        logger.error("系统内部异常:", ex);
        responseResult = ResponseUtil.simpleFail(DEFAULT_SYSCODE_EXCEPTION_VALUE, "请联系系统管理员解决此问题:" + ex.getMessage());
        responseResult.setData(ex);
        return responseResult;
    }
}


