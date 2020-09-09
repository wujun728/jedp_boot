/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Caratacus
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baomidou.hibernateplus.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;

import com.baomidou.hibernateplus.entity.EntityFieldInfo;
import com.baomidou.hibernateplus.entity.EntityInfo;

/**
 * <p>
 * 反射工具类
 * </p>
 *
 * @author Caratacus
 * @Date 2016-09-22
 */
public class ReflectionKit {

    private static final Logger logger = Logger.getLogger(ReflectionKit.class);

    /**
     * <p>
     * 反射 method 方法名，例如 getId
     * </p>
     *
     * @param str
     *            属性字符串内容
     * @return
     */
    public static String getMethodCapitalize(final String str) {
        return StringUtils.concatCapitalize("get", str);
    }

    /**
     * 获取 public get方法的值
     *
     * @param cls
     * @param entity
     *            实体
     * @param str
     *            属性字符串内容
     * @return Object
     */
    public static Object getMethodValue(Class<?> cls, Object entity, String str) {
        Object obj = null;
        try {
            Method method = cls.getMethod(getMethodCapitalize(str));
            obj = method.invoke(entity);
        } catch (NoSuchMethodException e) {
            logger.warn(String.format("Warn: No such method. in %s.  Cause:", cls.getSimpleName()) + e);
        } catch (IllegalAccessException e) {
            logger.warn(String.format("Warn: Cannot execute a private method. in %s.  Cause:", cls.getSimpleName()) + e);
        } catch (InvocationTargetException e) {
            logger.warn("Warn: Unexpected exception on getMethodValue.  Cause:" + e);
        }
        return obj;
    }

    /**
     * 获取 public get方法的值
     *
     * @param entity
     *            实体
     * @param str
     *            属性字符串内容
     * @return Object
     */
    public static Object getMethodValue(Object entity, String str) {
        if (null == entity) {
            return null;
        }
        return getMethodValue(entity.getClass(), entity, str);
    }

    /**
     * 调用对象的get方法检查对象所有属性是否为null
     *
     * @param bean
     *            检查对象
     * @return boolean true对象所有属性不为null,false对象所有属性为null
     */
    public static boolean checkFieldValueNotNull(Object bean) {
        if (null == bean) {
            return false;
        }
        Class<?> cls = bean.getClass();
        EntityInfo entityInfo = EntityInfoUtils.getEntityInfo(cls);
        boolean result = false;
        for (EntityFieldInfo fieldInfo : entityInfo.getFieldInfos()) {
            String fieldName = fieldInfo.getProperty();
            Object val = getMethodValue(cls, bean, fieldName);
            if (StringUtils.checkValNotNull(val)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 反射对象获取泛型
     *
     * @param clazz
     *            对象
     * @param index
     *            泛型所在位置
     * @return Class
     */
    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            logger.warn(String.format("Warn: %s's superclass not ParameterizedType", clazz.getSimpleName()));
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.warn(String.format("Warn: Index: %s, Size of %s's Parameterized Type: %s .", index, clazz.getSimpleName(),
                    params.length));
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(String.format("Warn: %s not set the actual class on superclass generic parameter",
                    clazz.getSimpleName()));
            return Object.class;
        }

        return (Class) params[index];
    }

    /**
     * 获取类中所有包含返回值的方法
     *
     * @param clazz
     * @return
     */
    public static Map<String, Method> getReturnMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        Map<String, Method> methodMap = new HashMap<String, Method>();
        for (Method method : methods) {
            Type genericReturnType = method.getGenericReturnType();
            if (Void.TYPE.equals(genericReturnType)) {
                continue;
            }
            methodMap.put(method.getName(), method);
        }

		/* 处理父类方法 */
        Class<?> superClass = clazz.getSuperclass();
        if (superClass.equals(Object.class)) {
            return methodMap;
        }
        methodMap.putAll(getReturnMethods(superClass));

        return methodMap;
    }

    /**
     * 获取方法是否包含该注解
     *
     * @param method
     * @param annotationClass
     * @return
     */
    public static <T extends Annotation> boolean hasAnnotation(Method method, Class<T> annotationClass) {
        return (method.getAnnotation(annotationClass)) != null;
    }

}