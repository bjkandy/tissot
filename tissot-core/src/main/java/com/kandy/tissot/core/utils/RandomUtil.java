package com.kandy.tissot.core.utils;

import java.util.Random;

/**
 * 生成随机数
 * Created by bjkandy on 2016/1/18.
 */
public class RandomUtil {
    final static Random random = new Random();

    public static Integer getRandom4(){
        return random.nextInt(9000) + 1000;
    }

    public static Integer getRandom5(){
        return random.nextInt(90000) + 10000;
    }

    public static Integer getRandom6(){
        return random.nextInt(900000) + 100000;
    }

    public static Integer getRandom8(){
        return random.nextInt(90000000) + 10000000;
    }
}
