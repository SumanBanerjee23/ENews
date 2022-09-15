package com.organization.ENews.exceptions;

public enum ErrorCode {

    DUPLICATE_USER(100,"User is already registered"),
    INVALID_USER(101,"Invalid User details given");


    private int errorCode;
    private String errorDesc;

    ErrorCode(int errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
