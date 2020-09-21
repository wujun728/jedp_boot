package com.sishuok.error;

import org.springframework.util.ErrorHandler;

/**
 * User: zhangkaitao
 * Date: 14-8-1
 * Time: ����9:01
 * Version: 1.0
 */
public class MyErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable throwable) {
        System.out.println("�¼�ʧ����, error message : " + throwable.getMessage());
    }
}
