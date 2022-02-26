package com.example.demo.redisCacheConfig;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 使用redis作为shiro的缓存，我们需要提供一个redis的cacheManager(实现shiro提供的CacheManager即可)
 * */

public class RedisCacheManager implements CacheManager {
    /**
     * 该实现方法只是一个用于获取缓存对象的方法，缓存功能的实际操作者是一个Cache对象，所以我们需要
     * 自定义一个Cache对象返回(实现shiro的Cache接口即可)
     * getCache的参数是我们在realm中设定的认证和授权的缓存名，在shiro调用该方法是，会自动传入。
     * 我们往往也将该名称作为缓存的key来使用
     * */
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new RedisCache(s);
    }
}
