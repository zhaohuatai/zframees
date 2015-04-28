/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
//pizza
package com.zht.common.codegen.excute;

import java.util.List;
import java.util.Map;

public interface JSPGenerator extends  Generator{
	
	public void genAll(String WEBINFPath,String nameSpace,String entityClassName,List<Map<String,String>> fieldListMap);
	public void genjsp_list(String WEBINFPath,String nameSpace,String entityClassName,List<Map<String,String>> fieldListMap);
	public void genjsp_add(String WEBINFPath,String nameSpace,String entityClassName,List<Map<String,String>> fieldListMap);
	public void genjsp_update(String WEBINFPath,String nameSpace,String entityClassName,List<Map<String,String>> fieldListMap);
	public void genjsp_listForLookUp(String WEBINFPath,String nameSpace,String entityClassName,List<Map<String,String>> fieldListMap);
	
}
