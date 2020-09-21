/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.interceptor</p>
 * <p>File: ExceptionInterceptor.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.interceptor;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qbt.framework.bean.JsonMessage;
import com.qbt.framework.core.DynamicDataSource;
import com.qbt.framework.emun.CommonEmun;
import com.qbt.framework.exception.BusinessException;
import com.qbt.framework.exception.BusinessRuntimeException;
import com.qbt.framework.exception.FileOperateException;


/**<p>Class: ExceptionInterceptor.java</p>
 * <p>Description: 全局错误异常拦截器</p>
 * <pre>
 *       这里没有使用@ExceptionHandler注解实现异常处理    
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class ExceptionInterceptor extends AbstractHandlerExceptionResolver{

	
	protected static Logger logger = LogManager.getLogger(DynamicDataSource.class);
	
	private HttpMessageConverter<Object>	jsonMessageConverter;
	private Integer errorCode = CommonEmun.ERROR.getCode();
	private String errorMessage = CommonEmun.ERROR.getMessage();
	
	/**
	 * 
	 * <p>描述：注入JSONMessage对象</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param jsonMessageConverter
	 */
	public void setJsonMessageConverter(
			HttpMessageConverter<Object> jsonMessageConverter)
	{
		
		this.jsonMessageConverter = jsonMessageConverter;
	}


	/**
	 * 
	 * <p>描述：错误码</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param errorCode
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * <p>描述：错误信息</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
	{
		try
		{
			logger.error(ex.getMessage(), ex);
			if (ex instanceof BindException){									// 控制器参数绑定异常
				
				return handleException(ex, request, response, handler);
			} else if(ex instanceof MethodArgumentNotValidException) {			//spring mvc 参数绑定异常
				
				return handleException(ex, request, response, handler);
			}else if (ex instanceof ConstraintViolationException){				// 约束异常
				
				return handleException(ex, request, response, handler);
			} else if (ex instanceof BusinessException){						// 业务异常
				
				return handleException(ex, request, response, handler);
			} else if(ex instanceof BusinessRuntimeException){							//业务异常
				return handleException(ex, request, response, handler);
			} else if (ex instanceof MaxUploadSizeExceededException) {

				MaxUploadSizeExceededException max = (MaxUploadSizeExceededException) ex;
				long m = max.getMaxUploadSize() / 1024 / 1024;
				return handleException(new FileOperateException("请上传小于" + m + "M的文件"), request, response, handler);
			} else if (ex instanceof SizeLimitExceededException) {
				SizeLimitExceededException size = (SizeLimitExceededException) ex;
				long m = size.getActualSize() / 1024 / 1024;
				return handleException(new FileOperateException("请上传小于" + m + "M的文件"), request, response, handler);
			} else if (ex instanceof HttpMessageNotReadableException) { 		// @RequestBody
				return handleException(new BusinessException(
						CommonEmun.PARAMES_ERROR), request, response, handler);
			} else if( ex instanceof ServletRequestBindingException){
				return handleException(new BusinessException(CommonEmun.PARAMES_ERROR), request, response, handler);
				
			} else if(ex instanceof HttpRequestMethodNotSupportedException){						//请求的方式不正确  404
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);								//返回404状态码
				return new ModelAndView();
			} 
			else { 															// 其它
				ex.printStackTrace();
				return handleException(new BusinessException(errorCode,
						errorMessage), request, response, handler);
			}
		} catch (Exception handlerException) {
			logger.error("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
		}
		return null;
	}

	
	// ===============================================================================================

	
	
	
	/**
	 * 处理异常
	 * @param ex
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws IOException
	 * @author 周光暖
	 */
	private ModelAndView handleException(Exception ex,
			HttpServletRequest request, HttpServletResponse response,
			Object handler) throws IOException
	{
		if (isAjax(ex, request, response, handler))
		{
			return handleAjaxeException(ex, response);
		} 
		else
		{
			return handleCommonException(ex, response);
		}
	}

	/**
	 * 处理ajax请求时的异常
	 * 
	 * @param ex
	 * @param response
	 * @return
	 * @throws IOException
	 * @author 周光暖
	 */
	private ModelAndView handleAjaxeException(Exception ex,
			HttpServletResponse response) throws IOException
	{
		HttpMessageConverter<Object> messageConverter = this.jsonMessageConverter;
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		outputMessage.getHeaders().add("Content-Type", "application/json;charset=UTF-8");		//   text/plain;charset=UTF-8
		messageConverter.write(getExceptionJsonMessage(ex), MediaType.APPLICATION_JSON, outputMessage);
		return new ModelAndView();
	}

	/**
	 * 处理普通请求时的异常
	 * 
	 * @param ex
	 * @param response
	 * @return
	 * @throws IOException
	 * @author 周光暖
	 */
	private ModelAndView handleCommonException(Exception ex,
			HttpServletResponse response) throws IOException
	{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, JSON.toJSONString(getExceptionJsonMessage(ex)));
		return new ModelAndView();
	}
	
	/**
	 * 判断是否为 ajax 请求
	 * 
	 * @param request
	 * @param response
	 * @param handlerMethod
	 * @param ex
	 * @return
	 */
	private boolean isAjax(Exception ex, HttpServletRequest request,
			HttpServletResponse response, Object handler)
	{
		String contentType = request.getContentType();
		if (!StringUtils.isEmpty(contentType)
				&& contentType.startsWith(MediaType.APPLICATION_JSON_VALUE))
		{
			return true;
		}
		if (handler instanceof HandlerMethod)
		{
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if( handlerMethod.getMethodAnnotation(ResponseBody.class) != null){
				return true;
			}
			if( handlerMethod.getBean().getClass().getAnnotation(RestController.class) != null){
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得异常描述信息
	 * 
	 * @return
	 * @author 周光暖
	 * @param ex
	 */
	private JsonMessage getExceptionJsonMessage(Exception ex)
	{
		JsonMessage jsonMessage = new JsonMessage(errorCode, errorMessage);
		if (ex instanceof BindException)
		{
			List<String> errorList = Lists.newArrayList();
			for (ObjectError error : ((BindException) ex).getAllErrors())
			{
				errorList.add(error.getDefaultMessage());
			}
			jsonMessage.setCode(CommonEmun.PARAMES_ERROR.getCode());
			jsonMessage.setMessage(StringEscapeUtils.escapeHtml4(errorList.toString()));
		}
		else if (ex instanceof ConstraintViolationException)
		{
			ConstraintViolationException constraintViolationException = (ConstraintViolationException)ex;
			Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
			List<String> errorList = getMessageList(constraintViolations, false);
			jsonMessage.setCode(CommonEmun.PARAMES_ERROR.getCode());
			jsonMessage.setMessage(StringEscapeUtils.escapeHtml4(errorList.toString()));
		}
		else if(ex instanceof MethodArgumentNotValidException)
		{
			MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
			List<ObjectError> errors = methodArgumentNotValidException.getBindingResult().getAllErrors();
			List<String> errorList = getMessageList(errors, true);
			jsonMessage.setCode(CommonEmun.PARAMES_ERROR.getCode());
			jsonMessage.setMessage(StringEscapeUtils.escapeHtml4(errorList.toString()));
		}
		else if (ex instanceof BusinessException)
		{
			BusinessException businessException = (BusinessException) ex;
			jsonMessage.setCode(businessException.getCode());
			jsonMessage.setMessage(StringEscapeUtils.escapeHtml4(businessException.getMessage()));
		}
		else if( ex instanceof BusinessRuntimeException)
		{
			BusinessException businessException = (BusinessException) ex;
			jsonMessage.setCode(businessException.getCode());
			jsonMessage.setMessage(StringEscapeUtils.escapeHtml4(businessException.getMessage()));
		}
		return jsonMessage;
	}
	
	private static List<String> getMessageList(List<ObjectError> errors, boolean hidAttribute){
		List<String> errorList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(errors)){
			for (ObjectError error : errors) {
				if( hidAttribute ){
					errorList.add(error.getDefaultMessage());
				}else{
					errorList.add(error.getObjectName() + "." + error.getDefaultMessage());
				}
			}
		}
		return errorList;
	}
	
	private static List<String> getMessageList(Set<ConstraintViolation<?>> constraintViolations, boolean hidAttribute)
	{
		List<String> errorList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(constraintViolations))
		{
			for (ConstraintViolation<?> constraintViolation : constraintViolations)
			{
				if (hidAttribute)
				{
					errorList.add(constraintViolation.getMessage());
				}
				else 
				{
					errorList.add(constraintViolation.getRootBeanClass().getSimpleName() + "." + constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
				}
			}
		}
		return errorList;
	}
}
	
