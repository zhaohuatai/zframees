/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.redis.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zht.common.shiro.redis.session.repository.IShiroSessionRepository;

import java.io.Serializable;
import java.util.Collection;


public class CustomShiroSessionDAO extends AbstractSessionDAO {
	private static Logger logger = LoggerFactory.getLogger(CustomShiroSessionDAO.class);
	
    private IShiroSessionRepository shiroSessionRepository;

    @Override
    public void update(Session session) throws UnknownSessionException {
        System.out.println("update session");
        getShiroSessionRepository().saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null) {
            return;
        }
        Serializable id = session.getId();
        if (id != null) {
            System.out.println("delete session");
            getShiroSessionRepository().deleteSession(id);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        System.out.println("get active sessions");
        return getShiroSessionRepository().getAllSessions();
    }

	
    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("do create session");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        getShiroSessionRepository().saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("do read session");
        return getShiroSessionRepository().getSession(sessionId);
    }

    public IShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository( IShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

}
