package com.github.zhangkaitao.spring.service;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * User: zhangkaitao
 * Date: 14-7-31
 * Time: ����7:10
 * Version: 1.0
 */
public class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... args) {
        System.out.println("�����첽���������, message : " + throwable.getMessage());
    }
}
