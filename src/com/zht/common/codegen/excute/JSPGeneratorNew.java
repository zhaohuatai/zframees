/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
//pizza
package com.zht.common.codegen.excute;

import java.util.List;

import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;

public interface JSPGeneratorNew extends  Generator{
	
	public void genAll(String entityFullClassName,String controllerNameSpace, GenEntity genEntity,List<GenEntityProperty> genEntityPropertyList);
	public void genjsp_list(String entityFullClassName,String controllerNameSpace, GenEntity genEntity,List<GenEntityProperty> genEntityPropertyList);
	public void genjsp_add(String entityFullClassName,String controllerNameSpace, GenEntity genEntity,List<GenEntityProperty> genEntityPropertyList);
	public void genjsp_update(String entityFullClassName,String controllerNameSpace, GenEntity genEntity,List<GenEntityProperty> genEntityPropertyList);
	public void genjsp_listForLookUp(String entityFullClassName,String controllerNameSpace, GenEntity genEntity,List<GenEntityProperty> genEntityPropertyList);
	
}
