package com.zht.common.shiro.util;

import java.util.Collection;

import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.zht.framework.spring.SpringUtils;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.shiro.ShiroDbRealm;

public class ShiroSecurityHelper {
	
	private static SessionDAO sessionDAO;
	
	public static void initStaticField(SessionDAO sessionDAO){
		ShiroSecurityHelper.sessionDAO = sessionDAO;
	}
	
	public static Session getSessionByUsername(String username){
		
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		for(Session session : sessions){
			if(null != session && ZStrUtil.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)), username)){
				return session;
			}
		}
		return null;
	}
	
	public static void kickOutUser(String userName){
		Session session = getSessionByUsername(userName);
		if(null != session ){
			SpringUtils.getBean(ShiroDbRealm.class).clearCachedAuthorizationInfo(userName);
			SpringUtils.getBean(ShiroDbRealm.class).clearAuthorizationInfo(userName);
			session.setTimeout(0L);
		}
	}
	
}
