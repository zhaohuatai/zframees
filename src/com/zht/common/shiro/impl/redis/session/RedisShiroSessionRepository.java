/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.impl.redis.session;

import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.Session;
import org.springframework.util.CollectionUtils;
import org.zht.framework.redis.jedis.JedisManager;
import org.zht.framework.redis.jedis.SerializeUtil;
import org.zht.framework.util.LogUtil;

import com.zht.common.shiro.impl.redis.cache.RedisShiroCache;
import com.zht.common.shiro.session.repository.IShiroSessionRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * ShiroSession Jedis形式实现类，
 * <br>session存储用redis db0  索引，注意后续使用不要随意占用
* @ClassName :JedisShiroSessionRepository     
* @Description :   
* @createTime :2015年5月7日  上午9:38:33   
* @author ：zhaohuatai   
* @version :1.0
 */
public class RedisShiroSessionRepository implements IShiroSessionRepository {
	
    private static final String REDIS_SHIRO_SESSION = "shiro-session:";
    private static final int SESSION_VAL_TIME_SPAN = 18000;
    private static final int DB_INDEX = 0;

    private JedisManager jedisManager;
    
    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {
            byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
            byte[] value = SerializeUtil.serialize(session);
            long sessionTimeOut = session.getTimeout() / 1000;
            Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
            //2分钟后删除redis数据
//            Long expireTime = sessionTimeOut  + (2 * 60);
            //30s后删除redis数据
//            Long expireTime = sessionTimeOut  + (30);
            getJedisManager().saveValueByKey(DB_INDEX, key, value, expireTime.intValue());
        } catch (Exception e) {
        	LogUtil.genErrorLog(RedisShiroSessionRepository.class,"saveSession",e.getMessage(), e);
        }
    }

    @Override
    public void deleteSession(Serializable id) {
        if (id == null) {
            throw new NullPointerException("session id is empty");
        }
        try {
            getJedisManager().deleteByKey(DB_INDEX,SerializeUtil.serialize(buildRedisSessionKey(id)));
        } catch (Exception e) {
        	LogUtil.genErrorLog(RedisShiroSessionRepository.class,"deleteSession",e.getMessage(), e);
        }
    }
    @Override
    public Session getSession(Serializable id) {
        if (id == null){
        	 throw new NullPointerException("session id is empty");
        }
        Session session = null;
        try {
            byte[] value = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil.serialize(buildRedisSessionKey(id)));
            session = SerializeUtil.deserialize(value, Session.class);
        } catch (Exception e) {
        	LogUtil.genErrorLog(RedisShiroSessionRepository.class,"getSession",e.getMessage(), e);
        }
        return session;
    }

    @Override
    public Collection<Session> getAllSessions() {
//    	   System.out.println("get all sessions");
           try {//shiro-session
        	   Set<byte[]> keys = getJedisManager().keys(DB_INDEX,"*"+REDIS_SHIRO_SESSION + "*"); 
        	   if (!CollectionUtils.isEmpty(keys)) {
                   List<Session> values = new ArrayList<Session>(keys.size());
                   for (byte[] key : keys) {
                       Session value = SerializeUtil.deserialize(getJedisManager().getValueByKey(DB_INDEX, key), Session.class);
//                       System.out.println("get all sessions---活动session---"+ value.getId());
                       if (value != null) {
                           values.add(value);
                       }
                   }
                   return Collections.unmodifiableList(values);
               } else {
                   return Collections.emptyList();
               }
           } catch (Exception t) {
        	   LogUtil.genErrorLog(RedisShiroSessionRepository.class,"getSession",t.getMessage(), t);
               throw new CacheException(t);
           }
    }

    private String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
