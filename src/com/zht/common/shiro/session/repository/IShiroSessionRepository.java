/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.session.repository;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * ShiroSession 访问接口，可继承此接口，根据实际自由实现，
 * <br>例如：ehcahe ，memory cache ，redis，mogodb等
* @ClassName :IShiroSessionRepository     
* @Description :   
* @createTime :2015年5月7日  上午9:37:05   
* @author ：zhaohuatai   
* @version :1.0
 */
public interface IShiroSessionRepository {

	/**
	 * 存储session
	 * @param session
	 */
    void saveSession(Session session);

    /**
     * 删除session
     * @param sessionId
     */
    void deleteSession(Serializable sessionId);

    /**
     * 获取session
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);

    /**
     * 获取所有session
     * @return
     */
    Collection<Session> getAllSessions();
}
