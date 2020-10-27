package com.github.zhangkaitao.spring.service;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.Future;

/**
 * User: zhangkaitao
 * Date: 14-7-31
 * Time: ����7:12
 * Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class AsyncServiceTest {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void testAsync() throws Exception {
        asyncService.throwException();
        Thread.sleep(1000L);
    }

    @Test
    public void testAsyncGet1() throws Exception {
        //�첽����ֱ�ӷ��أ�������null�������н���������Ҫ��ȡ����뷵��AsyncResult
        Assert.assertEquals(null, asyncService.asyncGet1());
        Thread.sleep(1000L);
    }

    @Test
    public void testAsyncGet2() throws Exception {
        ListenableFuture<String> listenableFuture = asyncService.asyncGet2();
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

        listenableFuture.addCallback(successCallback, failureCallback);
        Assert.assertEquals("123", listenableFuture.get());
    }
}
