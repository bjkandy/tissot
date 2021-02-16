package com.kandy.tissot.core.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串脱敏工具类
 * Created by bjkandy on 2016/1/22.
 */
public class StringHiddenUtil {
    private static Logger logger = LoggerFactory.getLogger(StringHiddenUtil.class);

    /**
     * 按字符串下标脱敏
     * @param source    源字符串
     * @param begin     开始下标
     * @param end       结束下标
     * @return
     */
    public static String getHiddenString(String source,Integer begin,Integer end){
        return StringHiddenUtil.getHiddenString(source, begin, end, '*');
    }

    /**
     * 按字符串下标脱敏
     * @param source    源字符串
     * @param begin     开始下标
     * @param end       结束下标
     * @param hidden    脱敏显示符
     * @return
     */
    public static String getHiddenString(String source,Integer begin,Integer end,char hidden){
        if (!StringUtils.isEmpty(source)){
            if (!(begin >= 0 && begin <=source.length())){
                logger.warn("隐藏字符异常: begin=>{} end=>{} length=>{}",begin,end,source.length());
                begin = 0;
            }

            if (!(begin <= end && end <= source.length())){
                logger.warn("隐藏字符异常: begin=>{} end=>{} length=>{}",begin,end,source.length());
                end = source.length();
            }
            char[] sourceArr = source.toCharArray();
            for (int index = begin;index < end;index++){
                sourceArr[index] = hidden;
            }
            return String.valueOf(sourceArr);
        }
        return null;
    }

    /**
     * 按字符串前后位脱敏
     * @param source    源字符串
     * @param begin     前几位
     * @param end       后几位
     * @return
     */
    public static String getHiddenStringByDisplay(String source,Integer begin,Integer end){
        return StringHiddenUtil.getHiddenStringByDisplay(source, begin, end, '*');
    }
    public static String getHiddenStringByDisplay(String source,Integer begin,Integer end,char hidden){
        if (!StringUtils.isEmpty(source)){
            int length = source.length();
            if (!(begin >= 0 && begin <= length)){
                logger.warn("隐藏字符异常: begin=>{} end=>{} length=>{}",begin,end,length);
                begin = 0;
            }

            if (!(begin + end < source.length() && end < length)){
                logger.warn("隐藏字符异常: begin=>{} end=>{} length=>{}",begin,end,length);
                end = 0;
            }
            char[] sourceArr = source.toCharArray();
            for (int index = begin;index < length - end;index++){
                sourceArr[index] = hidden;
            }
            return String.valueOf(sourceArr);
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println(StringHiddenUtil.getHiddenStringByDisplay("6215590200002266807",3,5));
    }
}
