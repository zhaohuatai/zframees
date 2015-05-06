/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisManager {

    private JedisPool jedisPool;

    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
        } catch (Exception e) {
            throw new JedisConnectionException(e);
        }
        return jedis;
    }

    @SuppressWarnings("deprecation")
	public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;
        if (isBroken)
            getJedisPool().returnBrokenResource(jedis);
        else
            getJedisPool().returnResource(jedis);
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
        	jedis = getJedis();
            if(dbIndex!=-1){
            	 jedis.select(dbIndex);
            }
            long size=jedis.del(key);
            System.out.println(size);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0)
                jedis.expire(key, expireTime);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }
	public void flushDB(int dbIndex) {
		 Jedis jedis = null;
		 boolean isBroken = false;
		try {
			 jedis = getJedis();
			 if(dbIndex!=-1){
				 jedis.select(dbIndex);
			 }
			jedis.flushDB();
		}  catch (Exception e) {
            isBroken = true;
            throw e;
        }finally {
			 returnResource(jedis, isBroken);
		}
	}
	
	public Long dbSize(int dbIndex) {
		Long dbSize = 0L;
		Jedis jedis =null;
		 boolean isBroken = false;
		try {
			jedis=getJedis();
			 if(dbIndex!=-1){
				 jedis.select(dbIndex);
			 }
			dbSize = jedis.dbSize();
		}   catch (Exception e) {
            isBroken = true;
            throw e;
        }finally {
			returnResource(jedis, isBroken);
		}
		return dbSize;
	}
	//*shiro-cache:shiro.authorizationCache:*
	public Set<byte[]> keys(int dbIndex,String pattern) {
		 Jedis jedis =null;
		 boolean isBroken = false;
		 Set<byte[]> keys = null;
		try {
			jedis=getJedis();
			 if(dbIndex!=-1){
				 jedis.select(dbIndex);
			 }
			keys = jedis.keys(pattern.getBytes());
		}   catch (Exception e) {
            isBroken = true;
            throw e;
        }finally {
			returnResource(jedis, isBroken);
		}
		return keys;
	}
	
	
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
