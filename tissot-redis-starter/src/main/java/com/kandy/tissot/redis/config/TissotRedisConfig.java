package com.kandy.tissot.redis.config;


import com.kandy.tissot.redis.util.*;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;

/**
 * redis配置
 */
@Configuration
public class TissotRedisConfig {

    /**
     * Redis 服务主机IP
     */
    @Value("${spring.redis.host:localhost}")
    private String host;

    /**
     * Redis 服务端口号
     */
    @Value("${spring.redis.port:6379}")
    private Integer port;

    /**
     * 密码
     */
    @Value("${spring.redis.password}")
    private String password;

    /**
     * Redis db
     */
    @Value("${spring.redis.database:1}")
    private Integer database;

    /**
     * 超时时间
     */
    @Value("${spring.redis.timeout:10000}")
    private Long timeout;

   /**
     * 资源池确保最少空闲的连接数
     */
    @Value("${spring.redis.pool.minIdle:2}")
    private Integer minIdle;
    /**
     * 资源池允许最大空闲的连接数
     */
    @Value("${spring.redis.pool.maxIdle:10}")
    private Integer maxIdle;
    /**
     * 资源池中最大连接数
     */
    @Value("${spring.redis.pool.maxActive:10}")
    private Integer maxActive;
    /**
     * 当资源池连接用尽后，调用者的最大等待时间(单位为毫秒)
     */
    @Value("${spring.redis.pool.maxWait:1000}")
    private Long maxWait;

    @Value("${spring.redis.pool.testOnBorrow:false}")
    private Boolean testOnBorrow;

    @Bean(name = "tissotRedisTemplate")
    public RedisTemplate<Serializable, Serializable> redisTemplate(@Qualifier("tissotRedisConnectionFactory") LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<Serializable, Serializable>();
        //key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
        //所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现 ObjectRedisSerializer
        //或者JdkSerializationRedisSerializer序列化方式;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        //以上4条配置可以不用
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean(name = "tissotRedisConnectionFactory")
    public LettuceConnectionFactory redisConnectionFactory(Environment environment) {
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(host, port);
        standaloneConfig.setPassword(RedisPassword.of(password));
        standaloneConfig.setDatabase(database);

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(testOnBorrow);

        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder().poolConfig(config);
        builder.commandTimeout(Duration.ofMillis(timeout));
        return new LettuceConnectionFactory(standaloneConfig, builder.build());
    }

    /**
     * Reids 基础操作工具类
     *
     * @return
     */
    @Bean
    public BaseRedisUtil baseRedisUtil() {
        return new BaseRedisUtil();
    }

    /**
     * Redis Hash 操作工具类
     *
     * @return
     */
    @Bean
    public RedisHashUtil hashRedisUtil() {
        return new RedisHashUtil();
    }

    /**
     * Redis List 操作工具类
     *
     * @return
     */
    @Bean
    public RedisListUtil listRedisUtil() {
        return new RedisListUtil();
    }

    /**
     * Redis Set 操作工具类
     *
     * @return
     */
    @Bean
    public RedisSetUtil setRedisUtil() {
        return new RedisSetUtil();
    }

    @Bean
    public RedisStringUtil stringRedisUtil() {
        return new RedisStringUtil();
    }

    /**
     * Redis Zset 操作工具类
     *
     * @return
     */
    @Bean
    public RedisZsetUtil zsetRedisUtil() {
        return new RedisZsetUtil();
    }
}
