package com.kandy.tissot.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by bjkandy on 2016/9/5.
 */
public abstract class JSONUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static final String toJSONString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }
}
