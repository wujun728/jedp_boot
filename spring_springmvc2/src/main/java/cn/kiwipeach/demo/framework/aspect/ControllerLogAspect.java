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

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Create Date: 2018/01/19
 * Description: 控制器的日志打印，方便开发调试
 * //TODO 1.给接口的调用添加审计功能。
 *
 * @author kiwipeach [1099501218@qq.com]
 */
@Component
public class ControllerLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);
    /**
     * 方法签名，内含方法以及方法参数元信息
     */
    private Signature signature;

    /**
     * @param joinPoint 切点信息参数
     */
    public void beforedMethod(JoinPoint joinPoint) {
        //获取参数和方法签名信息
        this.signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        //获取类和目标方法信息
        Class targetClass = signature.getDeclaringType();
        Method targetMethod = ((MethodSignature) signature).getMethod();
        logger.debug("{}.{}:控制器入参:{}}", targetClass.getName(), targetMethod.getName(), args);
    }

    public void afterMethod() {
        logger.debug("ControllerLogAspect afterMethod....");
    }

    public void afterReturnMethod(Object result) {
        //获取类和目标方法信息
        Class targetClass = signature.getDeclaringType();
        Method targetMethod = ((MethodSignature) signature).getMethod();
        logger.debug("{}.{}:控制器出参:{}}", targetClass.getName(), targetMethod.getName(), JSON.toJSONString(result));
    }

    public void afterThrowMethod(Exception ex) {
        logger.debug("ControllerLogAspect afterThrowMethod....");
    }

}
