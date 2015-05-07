/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.impl.redis.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.zht.framework.redis.jedis.JedisManager;
import org.zht.framework.redis.jedis.SerializeUtil;
import org.zht.framework.util.LogUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiroCache的redis形式实现
 * <br>注意cache 采用的是redis db1 索引区，后续使用不要覆盖
* @ClassName :RedisShiroCache     
* @Description :   
* @createTime :2015年5月7日  上午9:44:50   
* @author ：zhaohuatai   
* @version :1.0
 */
public class RedisShiroCache<K, V> implements Cache<K, V> {

    private static final String REDIS_SHIRO_CACHE = "shiro-cache:";
    private static final int DB_INDEX = 1;

    private JedisManager jedisManager;

    /**
     *  cache key 
     */
    private String name;

    public RedisShiroCache(String name, JedisManager jedisManager) {
        this.name = name;
        this.jedisManager = jedisManager;
    }

    /**
     * 例如： shiro-cache:shiro.authorizationCache:[authinfo]
     */
    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.cache.Cache#get(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) throws CacheException {
        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(key));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
        } catch (Exception e) {
        	LogUtil.genErrorLog(RedisShiroCache.class,"get",e.getMessage(), e);
        	throw new CacheException(e);
        }
        return (V) SerializeUtil.deserialize(byteValue);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.cache.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public V put(K key, V value) throws CacheException {
        V previos = get(key);
        try {
            jedisManager.saveValueByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)),
                    SerializeUtil.serialize(value), -1);
        } catch (Exception e) {
        	LogUtil.genErrorLog(RedisShiroCache.class,"put",e.getMessage(), e);
        	throw new CacheException(e);
        }
        return previos;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.cache.Cache#remove(java.lang.Object)
     */
    @Override
    public V remove(K key) throws CacheException {
        V previos = get(key);
        try {
            jedisManager.deleteByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)));
        } catch (Exception e) {
        	LogUtil.genErrorLog(RedisShiroCache.class,"put",e.getMessage(), e);
        	throw new CacheException(e);
        }
        return previos;
    }
  
    /*
     * (non-Javadoc)
     * @see org.apache.shiro.cache.Cache#clear()
     */
    @Override
    public void clear() throws CacheException {
    	  try {
    		  jedisManager.flushDB(DB_INDEX);
          } catch (Exception e) {
        	  LogUtil.genErrorLog(RedisShiroCache.class,"clear",e.getMessage(), e);
        	  throw new CacheException(e);
          }
    	
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.cache.Cache#size()
     */
    @Override
    public int size() {
    	try {
    		Long longSize = new Long(jedisManager.dbSize(DB_INDEX));
            return longSize.intValue();
        } catch (Exception e) {
        	 LogUtil.genErrorLog(RedisShiroCache.class,"clear",e.getMessage(), e);
            throw new CacheException(e);
        }
    }
    /*
     * (non-Javadoc)
     * @see org.apache.shiro.cache.Cache#keys()
     */
    @SuppressWarnings("unchecked")
	@Override
    public Set<K> keys() {
    	try {
    		 Set<byte[]> keys = jedisManager.keys(DB_INDEX,"*"+buildCacheKey("") + "*");
            if (CollectionUtils.isEmpty(keys)) {
            	return Collections.emptySet();
            }else{
            	Set<K> newKeys = new HashSet<K>();
            	for(byte[] key:keys){
            		newKeys.add((K)key);
            	}
            	return newKeys;
            }
        } catch (Exception e) {
        	 LogUtil.genErrorLog(RedisShiroCache.class,"keys",e.getMessage(), e);
            throw new CacheException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.cache.Cache#values()
     */
    @Override
    public Collection<V> values() {
    	try {
            Set<byte[]> keys = jedisManager.keys(DB_INDEX,buildCacheKey("") + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
                    @SuppressWarnings("unchecked")
					V value = get((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
        	 LogUtil.genErrorLog(RedisShiroCache.class,"keys",e.getMessage(), e);
            throw new CacheException(e);
        }
    }

    /**
     * REDIS_SHIRO_CACHE ：标示
     * @param key
     * @return
     */
    private String buildCacheKey(Object key) {
        return REDIS_SHIRO_CACHE + getName() + ":" + key;
    }

}
