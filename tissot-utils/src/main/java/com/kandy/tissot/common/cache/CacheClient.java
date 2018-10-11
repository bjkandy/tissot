package com.kandy.tissot.common.cache;

import java.util.List;
import java.util.Map;

/**
 * 缓存客户端
 * @author bjkandy
 * @date 2015-12-08
 */
public interface CacheClient {

    /**
     * 设置缓存数据
     * @param key   键
     * @param value 值
     * @param expiry    有效期(分钟)
     */
    public Boolean set(String key, Object value, int expiry);

    /**
     * 设置缓存数据
     * @param key   键
     * @param value 值
     * @param expiry    有效期(秒)
     */
    public Boolean setBySecends(String key, Object value, int expiry);

    /**
     * 获取缓存数据
     * @param key
     * @return
     */
    public Object get(String key);

    /**
     * 删除缓存数据
     * @param key
     */
    public Boolean delete(String key);

    /**
     * 检查缓存数据是否存在
     * @param key
     * @return
     */
    public boolean exists(String key);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getMulti(List keys);

    @SuppressWarnings("rawtypes")
    public Object[] getMulti(String keys[]);

    /**
     * Incr方法.
     */
    /**
     *
     * @param key           键
     * @param by            步长
     * @param defaultValue  默认
     * @param expiry        过期时间(分钟)
     * @return
     */
    public long incr(String key, int by, long defaultValue, int expiry);

    /**
     * Decr方法.
     * @param key           键
     * @param by            步长
     * @param defaultValue  默认
     * @param expiry        过期时间(分钟)
     * @return
     */
    public long decr(String key, int by, long defaultValue, int expiry);
    
    /**
     * touch方法重置过期时间
     * @Author   lixinpeng
     * @Date     2016年7月27日下午2:57:39
     * @param key           键
     * @param expiry        过期时间(分钟)
     * @return
     */
    public void touch(String key, int expiry);
}
