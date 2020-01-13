package com.kandy.tissot.core.utils;

import java.util.UUID;

/**
 * UUID生成器
 * Created by kandy on 2019/9/17.
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
