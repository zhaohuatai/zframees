/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.cache;

import org.apache.shiro.cache.Cache;
/**
 * ShiroCache 管理类接口，
 * <br>此处定义统一接口，方便不同实现 ,例如：ehcahe ，memory cache ，redis，mogodb等
* @ClassName :ShiroCacheManager     
* @Description :   
* @createTime :2015年5月7日  上午9:41:42   
* @author ：zhaohuatai   
* @version :1.0
 */
public interface ShiroCacheManager {

	/**
	 * getter cache
	 * @param name
	 * @return
	 */
    <K, V> Cache<K, V> getCache(String name);

   /**
    * destroy
    */
    void destroy();

}
