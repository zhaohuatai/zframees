package com.zht.common.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.zht.common.shiro.exception.ShiroSessionExpiredException;

public class OnLineSessionListener implements SessionListener{

	@Override
	public void onExpiration(Session session) {
		throw new ShiroSessionExpiredException("");
	}

	@Override
	public void onStart(Session session) {
	}

	@Override
	public void onStop(Session session) {
		
	}


}
