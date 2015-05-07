/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.listener;

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
