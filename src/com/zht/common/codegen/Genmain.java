/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen;

import java.io.IOException;

import org.zht.framework.util.ZStrUtil;

import com.zht.common.codegen.excute.ActionGenerator;
import com.zht.common.codegen.excute.DaoGenerator;
import com.zht.common.codegen.excute.ServiceGenerator;
import com.zht.common.codegen.excute.impl.ActionGeneratorImpl;
import com.zht.common.codegen.excute.impl.DaoGeneratorImpl;
import com.zht.common.codegen.excute.impl.ServiceGeneratorImpl;

public class Genmain {
	public static void main(String[] args) throws IOException {
		String path=Genmain.class.getResource("/").getPath();
		System.out.println(path);
		DaoGenerator daoG=new DaoGeneratorImpl();
		ServiceGenerator serG=new ServiceGeneratorImpl();
		ActionGenerator actionG=new ActionGeneratorImpl();
		String modeClazzName="com.zht.common.dauth.model.RbacDataAccessExp";
		//daoG.genAll(modeClazzName);
		//serG.genAll(modeClazzName);
		//actionG.generatorAction(modeClazzName, "/common/rbac/dataAccessExp", null, null);
	System.out.println("-----执行完成了...");
	String sr="{id}{#f# in (select id from DepartmentUser du where du.department=#v#)}{@did}";
	sr=sr.replace("}", "");
	sr=sr.replaceFirst("\\{", "");
	String[] sdsd=sr.split("\\{");
	String filed=sdsd[0];
	String value=sdsd[2];
	String exp=sdsd[1];
	exp=exp.replace("#f#", filed);
	exp=exp.replace("#v#", value);
	System.out.println(exp);
	}
	
}
