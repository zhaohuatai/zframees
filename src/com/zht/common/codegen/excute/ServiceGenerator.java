/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
//pizza
package com.zht.common.codegen.excute;


public interface ServiceGenerator extends  Generator{
	
	public void genAll(String entityFullClassName);
	public void genServiceInterface(String entityFullClassName);
	public void genServiceImpl(String entityFullClassName);
	
}
