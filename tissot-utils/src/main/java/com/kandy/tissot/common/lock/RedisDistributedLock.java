package com.kandy.tissot.common.lock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * Redis分布式锁(单实例)
 * Created by kandy on 2020/9/29.
 */
public class RedisDistributedLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param lockId 锁ID
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String lockId, int expireTime) {
        String result = jedis.set(lockKey, lockId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param lockId 锁ID
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String lockId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(lockId));

        if (RELEASE_SUCCESS.equals(result)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 尝试获取分布式锁(可重入锁)
     * @param jedis
     * @param lockKey
     * @param lockId
     * @param expireTime
     * @return
     */
    public static boolean tryGetDistributedReentrankLock(Jedis jedis, String lockKey, String lockId, int expireTime) {
        // 获取锁ID
        String redisLockId = RedisDistributedLock.getDistributedLockId(jedis, lockKey);
        if (null != redisLockId){
            return lockId.equalsIgnoreCase(redisLockId);
        }else {
            return RedisDistributedLock.tryGetDistributedLock(jedis, lockKey, lockId, expireTime);
        }
    }

    /**
     * 释放分布式锁(可重入锁)
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param lockId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedReentrankLock(Jedis jedis, String lockKey, String lockId) {

        // 获取锁ID
        String redisLockId = RedisDistributedLock.getDistributedLockId(jedis, lockKey);
        if (null == redisLockId){
            // 锁已释放
            return Boolean.TRUE;
        }

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(lockId));

        if (RELEASE_SUCCESS.equals(result)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 获取分布式锁存储的请求标识
     * @param jedis
     * @param lockKey
     * @return
     */
    public static String getDistributedLockId(Jedis jedis,String lockKey){
        return jedis.get(lockKey);
    }
}