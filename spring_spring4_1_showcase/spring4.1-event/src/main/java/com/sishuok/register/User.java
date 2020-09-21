/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sishuok.register;

import java.io.Serializable;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-7-8 ����9:37
 * <p>Version: 1.0
 */
public class User implements Serializable {
    private String username;
    private String password;

    //Ϊ�˲��Է��� ����ʵ��
    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
