package com.sunyu.redission.exception;

/**
 * @author yu 2018/11/5.
 */
public class RateLimitException extends RuntimeException {

    public RateLimitException(String message){
        super(message);
    }

    public RateLimitException(String message, Throwable cause){
        super(message,cause);
    }
}
