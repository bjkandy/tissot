package com.kandy.tissot.result;

import java.io.Serializable;

/**
 * Created by bjkandy on 2015/12/29.
 */
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = -5512178961566392313L;
    private final static String OK_MESSAGE = "OK";

    private int code = 200;
    private String message;
    private T data;
    private int pageSize;

    public BaseResult(){
        super();
        this.code = 200;
        this.message = OK_MESSAGE;
    }

    public BaseResult(T data){
        super();
        this.data = data;
        this.code = 200;
        this.message = OK_MESSAGE;
    }

    public BaseResult(T data, int code, String message){
        super();
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
