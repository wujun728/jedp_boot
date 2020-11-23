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
package cn.kiwipeach.demo.framework.aspect;


import cn.kiwipeach.demo.framework.annotations.Datasource;
import cn.kiwipeach.demo.framework.datasource.CustomMultipleDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Create Date: 2018/01/15
 * Description: 数据源自动切换切面类
 *
 * @author Wujun
 */
@Component
public class MybatisDatasourceAspect {

    private final static Logger logger = LoggerFactory.getLogger(MybatisDatasourceAspect.class);

    /**
     * 通过判断@Datasource注解配置信息，智能的选择数据源进行查询操作
     *
     * @param joinPoint 切点信息参数
     */
    public void beforedMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Class targetClass = signature.getDeclaringType();
        Method targetMethod = ((MethodSignature) signature).getMethod();
        String datasourceType = null;
        //优先判断方法级别的注解
        if (targetMethod.isAnnotationPresent(Datasource.class)) {
            Datasource methdoAnnotation = targetMethod.getAnnotation(Datasource.class);
            datasourceType = methdoAnnotation.value();
        //其次判断类级别的注解判断
        } else if (targetClass.isAnnotationPresent(Datasource.class)) {
            Datasource classAnnotation = (Datasource) targetClass.getAnnotation(Datasource.class);
            datasourceType = classAnnotation.value();
        } else {
            //TODO 在类和方法上均未检测到@Datasource注解,数据源将自动选择默认数据源,这里暂时不做任何处理
            return;
        }
        CustomMultipleDataSource.setDataSourceKey(datasourceType);
        logger.info("数据源将切换至[{}]进行查询.", datasourceType);
        logger.debug("DatasourceAspect beforedMethod....");
    }

    public void afterMethod() {
        logger.debug("DatasourceAspect afterMethod....");
    }

    public void afterReturnMethod(Object result) {
        logger.debug("DatasourceAspect afterReturnMethod....");
    }

    public void afterThrowMethod(Exception ex) {
        logger.debug("DatasourceAspect afterThrowMethod....");
    }

}
