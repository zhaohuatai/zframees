/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.message;

import java.io.Serializable;

public class webUploaderJaon implements Serializable{
	
	public WebUploaderError error;
	private String jsonrpc;
	private String id;
	public WebUploaderError getError() {
		return error;
	}
	public void setError(WebUploaderError error) {
		this.error = error;
	}
	public String getJsonrpc() {
		return jsonrpc;
	}
	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

	
	
}
