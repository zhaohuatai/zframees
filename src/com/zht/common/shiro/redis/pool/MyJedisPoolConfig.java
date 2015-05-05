package com.zht.common.shiro.redis.pool;

import redis.clients.jedis.JedisPoolConfig;

public class MyJedisPoolConfig extends JedisPoolConfig{
	private int maxIdle;

}
