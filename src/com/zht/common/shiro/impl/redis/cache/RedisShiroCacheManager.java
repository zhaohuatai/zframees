/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.impl.redis.cache;

import org.apache.shiro.cache.Cache;
import org.zht.framework.redis.jedis.JedisManager;

import com.zht.common.shiro.cache.ShiroCacheManager;

/**
 * RedisShiroCacheManager类
 * 
* @ClassName :JedisShiroCacheManager     
* @Description :   
* @createTime :2015年5月7日  上午9:42:59   
* @author ：zhaohuatai   
* @version :1.0
 */
public class RedisShiroCacheManager implements ShiroCacheManager {

    private JedisManager jedisManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new RedisShiroCache<K, V>(name, getJedisManager());
    }

    @Override
    public void destroy() {
        getJedisManager().getJedis().shutdown();
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
