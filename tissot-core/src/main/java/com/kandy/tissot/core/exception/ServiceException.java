package com.kandy.tissot.core.exception;

import com.kandy.tissot.core.enums.ErrorCodeEnum;

/**
 * Created by bjkandy on 2015/12/30.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code = ErrorCodeEnum.系统异常.getCode();

    public ServiceException() {
        super();
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause){
        super(message,cause);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public Integer getCode() {
        return code;
    }
}