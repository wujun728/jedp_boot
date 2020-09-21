/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: ConstraintValidateUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.spi.ValidationProvider;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.validator.HibernateValidator;

import com.google.common.collect.Lists;
import com.qbt.framework.emun.CommonEmun;


/**<p>Class: ConstraintValidateUtils.java</p>
 * <p>Description: 约束校验工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class ConstraintValidateUtils
{
	private static Validator validator = getValidator(HibernateValidator.class);

	private static String SEPARATOR = ":";// 默认分隔符
	
	/**
	 * 描述：验证对象是否违法约束，不通过抛出约束验证异常
	 * <pre>
	 * 异常数据格式：
	 *     code				描述代码：120002
	 *     message			描述信息：参数异常
	 *     detail			详细描述：[年龄不能小于18岁, 姓名不能为空, 性别不存在]
	 * </pre>
	 * @param beValidateBean	被验证的对象
	 * @param groups			验证组
	 * @throws ConstraintViolationException
	 */
	public static <T> void validate(T beValidateBean, Class<?>... groups) throws ConstraintViolationException
	{
		validate(beValidateBean, true, groups);
	}
	
	/**
	 * 描述：验证对象是否违法约束，不通过抛出约束验证异常
	 * <pre>
	 * 异常数据格式：
	 *     code				描述代码：120002
	 *     message			描述信息：参数异常
	 *     detail			详细描述
	 *     	hidAttribute：true		
	 *     		[年龄不能小于18岁, 姓名不能为空, 性别不存在]
	 *     	hidAttribute：false		
	 *     		[age：年龄不能小于18岁, name：姓名不能为空, sex：性别不存在]
	 * </pre>
	 * @param beValidateBean	被验证的对象
	 * @param hidAttribute		是否隐藏属性名称
	 * @param groups			验证组
	 * @throws ConstraintViolationException
	 */
	public static <T> void validate(T beValidateBean, boolean hidAttribute, Class<?>... groups) throws ConstraintViolationException
	{
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(beValidateBean, groups);
		if( CollectionUtils.isNotEmpty(constraintViolations)){
			throw new ConstraintViolationException(CommonEmun.PARAMES_ERROR.getMessage(), constraintViolations);
		}
	/*	List<String> messageList = getMessageListBySet(constraintViolations, hidAttribute);
		if (CollectionUtils.isNotEmpty(messageList))
		{
			throw new ConstraintViolationException(CommonEmun.PARAMES_ERROR.getMessage(), constraintViolations);
		}*/
	}
	
	/**
	 * 描述：验证对象是否违法约束，返回错误信息集
	 * <pre>
	 * 	       验证成功：[]
	 * 	       验证失败：[年龄不能小于18岁, 姓名不能为空, 性别不存在]
	 * </pre>
	 * @param beValidateBean	被验证的对象
	 * @param groups			验证组
	 * @return
	 */
	public static <T> List<String> getMessageListByBean(T beValidateBean, Class<?>... groups)
	{
		return getMessageListByBean(beValidateBean, true, groups);
	}
	
	/**
	 * 描述：验证对象是否违法约束，返回错误信息集
	 * <pre>
	 * 	       验证成功：[]
	 * 	       验证失败：
	 *        hidAttribute：true		
	 *        		[年龄不能小于18岁, 姓名不能为空, 性别不存在]
	 *        hidAttribute：false	
	 *        		[age：年龄不能小于18岁, name：姓名不能为空, sex：性别不存在]
	 * </pre>
	 * @param beValidateBean	被验证的对象
	 * @param hidAttribute		是否隐藏属性名
	 * @param groups			验证组
	 * @return
	 */
	public static <T> List<String> getMessageListByBean(T beValidateBean, boolean hidAttribute, Class<?>... groups)
	{
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(beValidateBean, groups);
		return getMessageListBySet(constraintViolations, hidAttribute);
	}

	/**
	 * 描述：验证对象的某个属性是否违法约束，不通过抛出约束验证异常
	 * <pre>
	 * 异常数据格式：
	 *     code				描述代码：120002
	 *     message			描述信息：参数异常
	 *     detail			详细描述：[年龄不能小于18岁, 姓名不能为空, 性别不存在]
	 * </pre>
	 * @param beValidateBean	被校验的属性所属对象
	 * @param propertyName	被校验的属性
	 * @param hidAttribute		是否隐藏属性名
	 * @param groups
	 * @throws ConstraintViolationException
	 */
	public static <T> void validateProperty(T beValidateBean, String propertyName, Class<?>... groups) throws ConstraintViolationException
	{
		validateProperty(beValidateBean, propertyName, true, groups);
	}
	
	/**
	 * 描述：验证对象的某个属性是否违法约束，不通过抛出约束验证异常
	 * <pre>
	 * 异常数据格式：
	 *     code				描述代码：120002
	 *     message			描述信息：参数异常
	 *     detail			详细描述
	 *     	hidAttribute：true		
	 *     		[年龄不能小于18岁, 姓名不能为空, 性别不存在]
	 *     	hidAttribute：false		
	 *     		[age：年龄不能小于18岁, name：姓名不能为空, sex：性别不存在]
	 * </pre>
	 * @param beValidateBean	被校验的属性所属对象
	 * @param propertyName	被校验的属性
	 * @param hidAttribute		是否隐藏属性名
	 * @param groups
	 * @throws ConstraintViolationException
	 */
	public static <T> void validateProperty(T beValidateBean, String propertyName, boolean hidAttribute, Class<?>... groups) throws ConstraintViolationException
	{
		Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(beValidateBean, propertyName, groups);
		if( CollectionUtils.isNotEmpty(constraintViolations)){
			throw new ConstraintViolationException(CommonEmun.PARAMES_ERROR.getMessage(), constraintViolations);
		}
		/*List<String> messageList = getMessageListBySet(constraintViolations, hidAttribute);
		if (CollectionUtils.isNotEmpty(messageList))
		{
			throw new ConstraintViolationException(DefaultDescribableEnum.PARAMES_ERROR, ArrayUtils.toString(messageList));
		}*/
	}
	
	/**
	 * 描述：验证指定值是否能够为指定类的指定属性赋值，不能赋值则抛出约束异常
	 * <pre>
	 * 异常数据格式：
	 *     code				描述代码：120002
	 *     message			描述信息：参数异常
	 *     detail			详细描述：[年龄不能小于18岁, 姓名不能为空, 性别不存在]
	 * </pre>
	 * @param beanType			被赋值的类
	 * @param propertyName		被赋值的属性
	 * @param value				赋值的值
	 * @param groups
	 * @throws ConstraintViolationException
	 */
	public static <T> void validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) throws ConstraintViolationException
	{
		validateValue(beanType, propertyName, value, true, groups);
	}
	
	/**
	 * 描述：验证指定值是否能够为指定类的指定属性赋值，不能赋值则抛出校验异常
	 * <pre>
	 * 异常数据格式：
	 *     code				描述代码：120002
	 *     message			描述信息：参数异常
	 *     detail			详细描述
	 *     	hidAttribute：true		
	 *     		[年龄不能小于18岁, 姓名不能为空, 性别不存在]
	 *     	hidAttribute：false		
	 *     		[age：年龄不能小于18岁, name：姓名不能为空, sex：性别不存在]
	 * </pre>
	 * @param beanType			被赋值的类
	 * @param propertyName		被赋值的属性
	 * @param value				赋值的值
	 * @param hidAttribute			是否隐藏属性名
	 * @param groups
	 * @throws ConstraintViolationException
	 */
	public static <T> void validateValue(Class<T> beanType, String propertyName, Object value, boolean hidAttribute, Class<?>... groups) throws ConstraintViolationException
	{
		Set<ConstraintViolation<T>> constraintViolations = validator.validateValue(beanType, propertyName, value, groups);
		if( CollectionUtils.isNotEmpty(constraintViolations)){
			throw new ConstraintViolationException(CommonEmun.PARAMES_ERROR.getMessage(), constraintViolations);
		}
		/*List<String> messageList = getMessageListBySet(constraintViolations, hidAttribute);
		if (CollectionUtils.isNotEmpty(messageList))
		{
			throw new ConstraintViolationException(DefaultDescribableEnum.PARAMES_ERROR, ArrayUtils.toString(messageList));
		}*/
	}
	
	
	
	// ======================================================================================================
	
	/**描述：
	 * <pre>
	 *     获得默认验证器	   	
	 * </pre>
	 * @return
	 */
	private static Validator getValidator()
	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}

	/**
	 * 描述：
	 * <pre>
	 * 	   获得指定验证器
	 * </pre>
	 * @param providerType
	 * @return
	 */
	private static Validator getValidator(Class<? extends ValidationProvider> providerType)
	{
		ValidatorFactory factory = Validation.byProvider(providerType).configure().buildValidatorFactory();
		return factory.getValidator();
	}
	
	/**描述：获得错误信息集
	 * <pre>
	 *     根据违反的约束，获得错误消息集
	 * </pre>
	 * @param constraintViolations
	 * @return
	 */
	public static <T> List<String> getMessageListBySet(Set<ConstraintViolation<T>> constraintViolations, boolean hidAttribute)
	{
		List<String> errorList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(constraintViolations))
		{
			for (ConstraintViolation constraintViolation : constraintViolations)
			{
				if (hidAttribute)
				{
					errorList.add(constraintViolation.getMessage());
				}
				else 
				{
					errorList.add(constraintViolation.getPropertyPath() + SEPARATOR + constraintViolation.getMessage());
				}
			}
		}
		return errorList;
	}
	
	public static List<String> getMessageListBySet2(Set<ConstraintViolation<?>> constraintViolations, boolean hidAttribute)
	{
		List<String> errorList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(constraintViolations))
		{
			for (ConstraintViolation constraintViolation : constraintViolations)
			{
				if (hidAttribute)
				{
					errorList.add(constraintViolation.getMessage());
				}
				else 
				{
					errorList.add(constraintViolation.getPropertyPath() + SEPARATOR + constraintViolation.getMessage());
				}
			}
		}
		return errorList;
	}
}
