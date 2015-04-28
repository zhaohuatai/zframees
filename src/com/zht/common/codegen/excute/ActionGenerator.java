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


public interface ActionGenerator extends  Generator{
	
	public void generatorAction(String entityFullClassName,String controllerNameSpace,GenEntity genEntity,List<GenEntityProperty> genEntityPropertyList);
	
}
