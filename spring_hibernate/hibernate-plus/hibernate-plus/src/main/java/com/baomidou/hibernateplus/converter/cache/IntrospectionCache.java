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
package com.baomidou.hibernateplus.converter.cache;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.ref.Reference;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

import com.baomidou.hibernateplus.converter.utils.StrUtils;
import com.baomidou.hibernateplus.exceptions.HibernatePlusException;

/**
 * JavaBean信息缓存
 * <p/>
 * User: liyd
 * Date: 13-5-8 下午6:36
 * version $Id: IntrospectionCache.java, v 0.1 Exp $
 */
public class IntrospectionCache {

    /**
     * Map keyed by class containing IntrospectionCache.
     * Needs to be a WeakHashMap with WeakReferences as values to allow
     * for proper garbage collection in case of multiple class loaders.
     */
    public static final Map<Class<?>, Object> classCache = Collections
            .synchronizedMap(new WeakHashMap<Class<?>, Object>());

    /**
     * 类的属性信息，key为属性名
     */
    private final Map<String, PropertyDescriptor> propertyDescriptorCache;

    /**
     * Instantiates a new Introspection cache.
     *
     * @param beanClass the bean class
     */
    private IntrospectionCache(Class<?> beanClass) {

        try {

            final BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);

            // 从Introspector缓存立即移除类，在类加载终止时允许适当的垃圾收集
            // 我们不管如何总是缓存在这里,这是一个GC友好的方式，对比于IntrospectionCache，
            // Introspector没有使用弱引用作为WeakHashMap的值
            Class<?> classToFlush = beanClass;
            do {
                Introspector.flushFromCaches(classToFlush);
                classToFlush = classToFlush.getSuperclass();
            } while (classToFlush != null);

            this.propertyDescriptorCache = new LinkedHashMap<String, PropertyDescriptor>();

            // 此调用较慢，所以我们只执行一次
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {

                if (Class.class.equals(beanClass) && "classLoader".equals(pd.getName())) {
                    // 忽略 Class.getClassLoader() 方法 - 没有人会需要绑定到那
                    continue;
                }

                this.propertyDescriptorCache.put(pd.getName(), pd);
            }

        } catch (IntrospectionException ex) {
            throw new HibernatePlusException("初始化缓存bean信息时出现异常", ex);
        }
    }

    /**
     * For class.
     *
     * @param beanClass the bean class
     * @return the introspection cache
     */
    public static IntrospectionCache forClass(Class<?> beanClass) {

        IntrospectionCache introspectionCache;

        Object value = classCache.get(beanClass);

        if (value instanceof Reference) {
            @SuppressWarnings("rawtypes")
            Reference ref = (Reference) value;
            introspectionCache = (IntrospectionCache) ref.get();
        } else {
            introspectionCache = (IntrospectionCache) value;
        }

        if (introspectionCache == null) {

            introspectionCache = new IntrospectionCache(beanClass);
            classCache.put(beanClass, introspectionCache);

        }

        return introspectionCache;

    }

    /**
     * Get property descriptors.
     *
     * @return the property descriptor [ ]
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] pds = new PropertyDescriptor[this.propertyDescriptorCache.size()];
        int i = 0;
        for (PropertyDescriptor pd : this.propertyDescriptorCache.values()) {
            pds[i] = pd;
            i++;
        }
        return pds;
    }

    /**
     * Get property descriptor.
     *
     * @param name the name
     * @return the property descriptor
     */
    public PropertyDescriptor getPropertyDescriptor(String name) {

        PropertyDescriptor pd = this.propertyDescriptorCache.get(name);

        if (pd == null && StrUtils.isNotBlank(name)) {
            // Same lenient fallback checking as in PropertyTypeDescriptor...
            pd = this.propertyDescriptorCache.get(name.substring(0, 1).toLowerCase() + name.substring(1));
            if (pd == null) {
                pd = this.propertyDescriptorCache.get(name.substring(0, 1).toUpperCase() + name.substring(1));
            }
        }
        return pd;
    }

}
