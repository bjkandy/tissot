package com.kandy.tissot.common.cache.impl;

import com.kandy.tissot.common.cache.CacheClient;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.OperationTimeoutException;
import net.spy.memcached.internal.OperationFuture;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjkandy on 2015/12/29.
 */
public class TissotMemcachedClient implements CacheClient {
    private MemcachedClient memcachedClient;

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    /**
     * 设置缓存数据
     * @param key   键
     * @param value 值
     * @param expiry    有效期(分钟)
     */
    @Override
    public Boolean set(String key, Object value, int expiry) {
        if (StringUtils.isEmpty(key) || value == null) {
            return false;
        }

        try{
            OperationFuture<Boolean> operationFuture = memcachedClient.set(key, expiry * 60, value);
            return operationFuture.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置缓存数据
     * @param key   键
     * @param value 值
     * @param expiry    有效期(秒)
     */
    @Override
    public Boolean setBySecends(String key, Object value, int expiry) {
        if (StringUtils.isEmpty(key) || value == null) {
            return false;
        }

        try{
            OperationFuture<Boolean> operationFuture = memcachedClient.set(key, expiry, value);
            return operationFuture.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取缓存数据
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        if (StringUtils.isEmpty(key)){
            return null;
        }
        Object o = null;
        try {
            o = memcachedClient.get(key);
        } catch (OperationTimeoutException e) {
            e.printStackTrace();
        }
        return o;
    }

    /**
     * 删除缓存数据
     * @param key
     */
    @Override
    public Boolean delete(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        try{
            OperationFuture<Boolean> operationFuture = memcachedClient.delete(key);
            return operationFuture.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 检查缓存数据是否存在
     * @param key
     * @return
     */
    @Override
    public boolean exists(String key) {
        if (StringUtils.isEmpty(key)){
            return false;
        }else {
            return memcachedClient.get(key) != null;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map getMulti(List keys) {
        if (keys == null || keys.size() == 0) {
            return new HashMap(0);
        } else {
            String strKeys[] = new String[keys.size()];
            strKeys = (String[]) keys.toArray(strKeys);
            return memcachedClient.getBulk(strKeys);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object[] getMulti(String keys[]) {
        if (keys == null || keys.length == 0) {
            return new Object[0];
        } else {
            Map map = memcachedClient.getBulk(keys);
            return map.values().toArray();
        }
    }

    /**
     * Incr方法.
     */
    @Override
    public long incr(String key, int by, long defaultValue,int expiry) {
        return memcachedClient.incr(key, by, defaultValue,expiry * 60);
    }

    /**
     * Decr方法.
     */
    @Override
    public long decr(String key, int by, long defaultValue,int expiry) {
        return memcachedClient.decr(key, by, defaultValue,expiry * 60);
    }

	@Override
	public void touch(String key, int expiry) {
		memcachedClient.touch(key, expiry * 60);
	}
}
