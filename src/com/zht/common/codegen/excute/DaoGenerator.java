/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.excute;

public interface DaoGenerator extends  Generator{
	public void genAll(String entityFullClassName);
	public void genDaoInterface(String entityFullClassName);
	public void genDaoImpl(String entityFullClassName);
	
}
