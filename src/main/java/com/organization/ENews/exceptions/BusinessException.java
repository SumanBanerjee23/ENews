package com.organization.ENews.exceptions;

import java.util.Date;

public class BusinessException extends RuntimeException {

    String errorDesc;
    Date errorTime;
    int errorCode;

    public BusinessException(String errorDesc, int errorCode) {
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
        this.errorTime = new Date();
    }

    public BusinessException(String message, String errorDesc, int errorCode) {
        super(message);
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
        this.errorTime = new Date();
    }

    public BusinessException(String message, Throwable cause, String errorDesc, int errorCode) {
        super(message, cause);
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
        this.errorTime = new Date();
    }

    public BusinessException(Throwable cause, String errorDesc, int errorCode) {
        super(cause);
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
        this.errorTime = new Date();
    }
}
