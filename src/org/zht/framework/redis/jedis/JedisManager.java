/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.redis.jedis;

import java.util.Set;

import org.zht.framework.util.LogUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
/**
 *Jedis 通用工具管理类， 
 *<br>序列化，反序列化方式采用jdk自带API ，
 *    后期意向改成thrift 或google protobuf
* @ClassName :JedisManager     
* @Description :   
* @createTime :2015年5月7日  上午9:18:20   
* @author ：zhaohuatai   
* @version :1.0
 */
public class JedisManager {

    private JedisPool jedisPool;

    /**
     * 连接池中获取Jedis实例
     * @return Jedis实例
     */
    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
        } catch (Exception e) {
            throw new JedisConnectionException(e);
        }
        return jedis;
    }

    /**
     * jedis 实例用完后回归连接池
     * @param jedis
     * @param isBroken
     */
    @SuppressWarnings("deprecation")
	public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;
        if (isBroken)
            getJedisPool().returnBrokenResource(jedis);
        else
            getJedisPool().returnResource(jedis);
    }

    /**
     * 根据key，查询db索引区的数据
     * @param dbIndex redis db索引
     * @param key 数据key
     * @return 字节数据，需要反序列化后使用
     * @throws Exception
     */
    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
        	LogUtil.genErrorLog(JedisManager.class,"getValueByKey",e.getMessage(), e);        	
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }
    /**
     * 根据key，删除db索引区的数据
     * @param dbIndex
     * @param key
     * @throws Exception
     */
    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
        	jedis = getJedis();
            if(dbIndex!=-1){
            	 jedis.select(dbIndex);
            }
           jedis.del(key);
        } catch (Exception e) {
        	LogUtil.genErrorLog(JedisManager.class,"deleteByKey",e.getMessage(), e);
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

   /**
    * 根据key，存储db索引区的数据
    * @param dbIndex
    * @param key
    * @param value
    * @param expireTime
    * @throws Exception
    */
    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0){
            	jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
        	LogUtil.genErrorLog(JedisManager.class,"deleteByKey",e.getMessage(), e);
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }
    /**
     * 清空db块的数据
     * @param dbIndex
     */
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
			LogUtil.genErrorLog(JedisManager.class,"deleteByKey",e.getMessage(), e);
            isBroken = true;
            throw e;
        }finally {
			 returnResource(jedis, isBroken);
		}
	}
	/**
	 * 获取数据记录数
	 * @param dbIndex
	 * @return
	 */
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
			LogUtil.genErrorLog(JedisManager.class,"deleteByKey",e.getMessage(), e);
            isBroken = true;
            throw e;
        }finally {
			returnResource(jedis, isBroken);
		}
		return dbSize;
	}
	
	/**
	 * 根据【字符串】pattern，获取 db dbIndex 区域的所有key
	 * @param dbIndex
	 * @param pattern 注意此处是正则字符串
	 * @return
	 */
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
//			keys = jedis.keys(SerializeUtil.serialize(pattern));
		}   catch (Exception e) {
			LogUtil.genErrorLog(JedisManager.class,"deleteByKey",e.getMessage(), e);
            isBroken = true;
            throw e;
        }finally {
			returnResource(jedis, isBroken);
		}
		return keys;
	}
	
	/**
	 * getter JedisPool
	 * @return
	 */
    public JedisPool getJedisPool() {
        return jedisPool;
    }
    /**
	 * setter JedisPool
	 * @return
	 */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
