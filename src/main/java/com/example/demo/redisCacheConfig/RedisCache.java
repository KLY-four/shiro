package com.example.demo.redisCacheConfig;

import com.example.demo.config.ApplicationContextUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

public class RedisCache implements Cache {

    /**
     * 以下方法是shiro提供的对缓存的同一接口模型，也就是对于其他的缓存框架，这里的方法是
     * 主入口，至于使用哪种缓存框架，我们在方法内部实现即可。
     * */

    /**
     * shiro的认证和授权在调用缓存时，事宜 用户名为 key(也就是第一个参数)，认证或授权信息为value(也就是第二个参数)
     * 这里以redis为例，shiro的认证数据和授权数据一般都以map结构存放，key为认证或授权的名称（这个在realm中可以指定，如果不指定会有默认
     * 他的获取，可以从cacheManager中的实现getCache实现方法中传入。）
     * value的数据则是 以用户名作为key 认证和授权数据作为value。
     * 注意：以下的put方式是由shiro自动调用，第一个参数是用户名，第二个参数是认证或授权信息。
     * */

    /**
     * 注意：在使用redis时，由于内部序列化时极大可能会用到jdk的序列化，所以我们的相关类需要实现序列化接口。
     * 其次，由于shiro在设计时的问题，在密码加密时的salt中(也就是ByteSource)没有实现JDK序列化接口(因为ByteSource(正确的说应该是ByteSource的实现类)也是要参与序列化的)，
     * 因此如果我们redis同时采用了jdk序列化，又使用了salt，那么就会会报错。因此我们需要自定义类来解决。
     * */
    private String cacheName;

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }
   /**
    * 以下方法的实现均以 hash结构为基础(因为上卖弄说了，认证和授权信息一般都是以hash存放)
    * */
    // o 就是用户名
    @Override
    public Object get(Object o) throws CacheException {
        System.out.println(444);
        final RedisTemplate template = getRedisTemplate();
        return template.opsForHash().get(cacheName,o);
    }

    //o2 是认证或授权信息。当然我们一般不直接将用户名作为key
    //而是用认证和授权的缓存名称作为key ，用户名和认证或授权信息作为hash存储
    //这样就可以很好的区分认证和缓存信息
    @Override
    public Object put(Object o, Object o2) throws CacheException {
        System.out.println(333);
        final RedisTemplate template = getRedisTemplate();
        template.opsForHash().put(cacheName,o,o2);
        return null;
    }

    //删除指定用户的认证或授权信息（用户注销时会调用）
    @Override
    public Object remove(Object o) throws CacheException {
        return getRedisTemplate().opsForHash().delete(this.cacheName,o);
    }

    //删除指定key
    @Override
    public void clear() throws CacheException {
      getRedisTemplate().delete(this.cacheName);
    }

    //获取缓存数量
    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    //获取所有的hashkey
    @Override
    public Set keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }

    public RedisTemplate getRedisTemplate(){
        return (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");
    }
}
