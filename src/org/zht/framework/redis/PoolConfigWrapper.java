package org.zht.framework.redis;


import redis.clients.jedis.JedisPoolConfig;
//>>2.4
//maxActive  ==>  maxTotal
//maxWait ==> maxWaitMillis

public class PoolConfigWrapper {

	private final JedisPoolConfig config;

	public PoolConfigWrapper() {
		this.config = new JedisPoolConfig();
	}

	public JedisPoolConfig getConfig() {
		return config;
	}

	public int getMaxIdle() {
		return this.config.getMaxIdle();
	}
	public void setMaxIdle(int maxIdle) {
		this.config.setMaxIdle(maxIdle);
	}

	public int getMinIdle() {
		return this.config.getMinIdle();
	}
	public void setMinIdle(int minIdle) {
		this.config.setMinIdle(minIdle);
	}

	public int getMaxTotal() {
		return this.config.getMaxTotal();
	}
	public void setMaxTotal(int maxTotal) {
		this.config.setMaxTotal(maxTotal);
	}

	//maxWaitMillis
	public long getMaxWaitMillis() {
		return this.config.getMaxWaitMillis();
	}
	public void setMaxWaitMillis(long maxWaitMillis) {
		this.config.setMaxWaitMillis(maxWaitMillis);
	}
/*----------------------------------------------------*/	
	//testOnBorrow
	public boolean isTestOnBorrow() {
		return this.config.getTestOnBorrow();
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.config.setTestOnBorrow(testOnBorrow);
	}

	//testOnReturn
	public boolean getTestOnReturn() {
		return this.config.getTestOnReturn();
	}
	public void setTestOnReturn(boolean testOnReturn) {
		this.config.setTestOnReturn(testOnReturn);
	}
	
	//testOnCreate
	public boolean getTestOnCreate() {
		return this.config.getTestOnCreate();
	}
	public void setTestOnCreate(boolean testOnCreate) {
		this.config.setTestOnCreate(testOnCreate);
	}
	
	//testWhileIdle
	public boolean getTestWhileIdle() {
		return this.config.getTestWhileIdle();
	}
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.config.setTestWhileIdle(testWhileIdle);
	}

	//timeBetweenEvictionRunsMillis
	public long getTimeBetweenEvictionRunsMillis() {
		return this.getTimeBetweenEvictionRunsMillis();
	}
	public void setTimeBetweenEvictionRunsMillis(
			long timeBetweenEvictionRunsMillis) {
		this.config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
	}

	//numTestsPerEvictionRun
	public int getNumTestsPerEvictionRun() {
		return this.config.getNumTestsPerEvictionRun();
	}
	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
	}
	//minEvictableIdleTimeMillis
	public long getMinEvictableIdleTimeMillis() {
		return this.config.getMinEvictableIdleTimeMillis();
	}
	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
	}

	//softMinEvictableIdleTimeMillis
	public long getSoftMinEvictableIdleTimeMillis() {
		return this.config.getSoftMinEvictableIdleTimeMillis();
	}
	public void setSoftMinEvictableIdleTimeMillis(long softMinEvictableIdleTimeMillis) {
		this.config.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
	}

	public boolean isBlockWhenExhausted() {
		return config.getBlockWhenExhausted();
	}

	public void setBlockWhenExhausted(boolean blockWhenExhausted) {
		this.config.setBlockWhenExhausted(blockWhenExhausted);
	}
}
