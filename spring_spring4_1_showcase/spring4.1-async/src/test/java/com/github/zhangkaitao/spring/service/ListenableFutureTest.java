package com.github.zhangkaitao.spring.service;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.util.concurrent.*;

import java.util.concurrent.Callable;

/**
 * User: zhangkaitao
 * Date: 14-8-1
 * Time: ����8:40
 * Version: 1.0
 */
public class ListenableFutureTest {

    @Test
    public void test() throws Exception {
        SuccessCallback<String> successCallback = new SuccessCallback<String>() {
            @Override
            public void onSuccess(String str) {
                System.out.println("�첽�ص��ɹ���, return : " + str);
            }
        };
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("�첽�ص�ʧ����, exception message : " + throwable.getMessage());
            }
        };

        ListenableFutureTask<String> future = new ListenableFutureTask(new Callable() {
            @Override
            public String call() throws Exception {
                return "123";
            }
        });
        future.addCallback(successCallback, failureCallback);

        future.run();
        Assert.assertEquals("123", future.get());

        System.out.println("=========================");

        ListenableFutureCallback<String> listenableFutureCallback = new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("�첽�ص�ʧ����, exception message : " + throwable.getMessage());
            }

            @Override
            public void onSuccess(String str) {
                System.out.println("�첽�ص��ɹ���, return : " + str);
            }
        };

        ListenableFutureTask<String> future2 = new ListenableFutureTask(new Callable() {
            @Override
            public String call() throws Exception {
                throw new RuntimeException("������");
            }
        });
        future2.addCallback(listenableFutureCallback);
        future2.run();
    }
}
