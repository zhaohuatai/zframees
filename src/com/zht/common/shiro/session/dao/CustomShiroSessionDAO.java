/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.session.dao;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.zht.common.shiro.session.repository.IShiroSessionRepository;

import java.io.Serializable;
import java.util.Collection;

/**
 * 继承实现 shiro sessionDAO，实现session的存取，
* @ClassName :CustomShiroSessionDAO     
* @Description :   
* @createTime :2015年5月7日  上午9:29:54   
* @author ：zhaohuatai   
* @version :1.0
 */
public class CustomShiroSessionDAO extends AbstractSessionDAO {
	/**
	 * ShiroSession DAO存取接口
	 */
    private IShiroSessionRepository shiroSessionRepository;
    /*
     * (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.SessionDAO#update(org.apache.shiro.session.Session)
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
//        System.out.println("update session");
        getShiroSessionRepository().saveSession(session);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.SessionDAO#delete(org.apache.shiro.session.Session)
     */
    @Override
    public void delete(Session session) {
        if (session == null) {
            return;
        }
        Serializable id = session.getId();
        if (id != null) {
//            System.out.println("delete session");
            getShiroSessionRepository().deleteSession(id);
        }
    }

    /*
     * 注意此处：在线用户量大的话 会对内存 形成巨大压力，
     * 后期意向改成分批处理
     * (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.SessionDAO#getActiveSessions()
     */
    @Override
    public Collection<Session> getActiveSessions() {
    	//System.out.println("get active sessions");
        return getShiroSessionRepository().getAllSessions();
    }

	/*
	 * (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session)
	 */
    @Override
    protected Serializable doCreate(Session session) {
    	//System.out.println("do create session");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        getShiroSessionRepository().saveSession(session);
        return sessionId;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java.io.Serializable)
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
    	// System.out.println("do read session");
        return getShiroSessionRepository().getSession(sessionId);
    }

    /**
     * getter
     * @return
     */
    public IShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    /**
     * setter
     */
    public void setShiroSessionRepository( IShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

}
