package com.organization.ENews.exceptions;

public class InvalidUserException extends BusinessException {

    public InvalidUserException(String errorDesc, int errorCode) {
        super(errorDesc, errorCode);
    }

    public InvalidUserException(String message, String errorDesc, int errorCode) {
        super(message, errorDesc, errorCode);
    }

    public InvalidUserException(String message, Throwable cause, String errorDesc, int errorCode) {
        super(message, cause, errorDesc, errorCode);
    }

    public InvalidUserException(Throwable cause, String errorDesc, int errorCode) {
        super(cause, errorDesc, errorCode);
    }
}
