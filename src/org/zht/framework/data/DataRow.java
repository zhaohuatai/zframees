/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.data;

import java.util.HashMap;
import java.util.Map;

public class DataRow extends HashMap<String, Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataRow() {
		super();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataRow(Map map) {
		if(map!=null){
			this.clear();this.putAll(map);
		}
	
	}
}
