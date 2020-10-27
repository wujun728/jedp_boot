/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sishuok.hello;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-7-8 ����9:10
 * <p>Version: 1.0
 */
@Component
public class ZhangsanListener implements ApplicationListener<ContentEvent> {

    @Override
    public void onApplicationEvent(final ContentEvent event) {
        System.out.println("�����յ����µ����ݣ�" + event.getSource());
    }
}
