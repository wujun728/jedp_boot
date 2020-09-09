package com.BBS.service.impl;

/**
 * Created by dyl on 2018/8/20.
 */
public class UserException extends Exception {

    public UserException() {
        super();
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable cause) {
        super(cause);
    }
}
