/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.util;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class RequestUtil {
	 /**
	  * 
	  * @param request
	  * @return
	  */
	public static String getRequestParams(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		if (params == null) {
			return "";
		}
		return JSON.toJSONString(params);
	 }
	public static String getRequestHeaders(HttpServletRequest request) {
	        Map<String, List<String>> headers = Maps.newHashMap();
	        Enumeration<String> namesEnumeration = request.getHeaderNames();
	        while(namesEnumeration!=null&&namesEnumeration.hasMoreElements()) {
	            String name = namesEnumeration.nextElement();
	            Enumeration<String> valueEnumeration = request.getHeaders(name);
	            List<String> values = Lists.newArrayList();
	            while(valueEnumeration.hasMoreElements()) {
	                values.add(valueEnumeration.nextElement());
	            }
	            headers.put(name, values);
	        }
	        return JSON.toJSONString(headers);
	 }
}
