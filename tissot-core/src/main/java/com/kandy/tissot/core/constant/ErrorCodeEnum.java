package com.kandy.tissot.core.constant;

/**
 * 系统错误码
 */
public enum ErrorCodeEnum {

    //系统类
    系统异常(999, "系统繁忙,请稍后重试!"),
    系统提醒(888,"系统提醒"),
    系统提示刷新(886,"系统提示刷新"),
    系统提示(887,"系统提示"),

    //参数类
    参数异常(700,"参数异常."),
    参数丢失(710,"参数丢失."),
    参数不为空(722,"参数不为空."),
    非法参数(720,"参数非法");
    private int code;
    private String message;

    private ErrorCodeEnum(int code, String message) {
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

    public static boolean contain(Integer value) {
        if (value == null) {
            return false;
        }
        ErrorCodeEnum[] values = ErrorCodeEnum.values();
        for (ErrorCodeEnum sexEnum : values) {
            if (sexEnum.code == value) {
                return true;
            }
        }
        return false;
    }
}

