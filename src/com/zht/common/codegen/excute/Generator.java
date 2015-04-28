/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.excute;

import java.util.Map;

public interface Generator {

	public void generate(String templateFileName, Map<?,?> data,String fileName) ;

}
