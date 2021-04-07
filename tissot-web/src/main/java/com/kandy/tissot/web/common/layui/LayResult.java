package com.kandy.tissot.web.common.layui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * LayUI Result
 * Created by kandy on 2021/1/22.
 */
public class LayResult extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    public LayResult() {
        put("code", 0);
    }

    public static LayResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static LayResult error(String msg) {
        return error(500, msg);
    }

    public static LayResult error(int code, String msg) {
        LayResult layResult = new LayResult();
        layResult.put("code", code);
        layResult.put("msg", msg);
        return layResult;
    }

    public static LayResult ok(String msg) {
        LayResult layResult = new LayResult();
        layResult.put("msg", msg);
        return layResult;
    }

    public static LayResult ok(Map<String, Object> map) {
        LayResult layResult = new LayResult();
        layResult.putAll(map);
        return layResult;
    }

    public static LayResult ok() {
        return new LayResult();
    }

    @Override
    public LayResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}