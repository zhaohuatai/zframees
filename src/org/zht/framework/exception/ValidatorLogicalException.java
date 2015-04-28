/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.exception;

public class ValidatorLogicalException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidatorLogicalException() {
		super();
	}

	public ValidatorLogicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidatorLogicalException(String message) {
		super(message);
	}

	public ValidatorLogicalException(Throwable cause) {
		super(cause);
	}


}
