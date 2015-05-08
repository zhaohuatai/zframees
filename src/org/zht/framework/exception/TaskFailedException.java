/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.exception;

public class TaskFailedException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TaskFailedException() {
		super();
	}

	public TaskFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskFailedException(String message) {
		super(message);
	}

	public TaskFailedException(Throwable cause) {
		super(cause);
	}


}
