/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.constant;

import org.zht.framework.util.ConfigUtil;

public class GenConstant {
	public static final String project_path=ConfigUtil.getConfig("system.properties", "project.path");
	
	public static final String genTemplateDir="genTemplate";
	public static final String hiberModel_template_dir=project_path+genTemplateDir+"/entity.java.ftl";
	
	//action
	public static final String action_template_dir=project_path+genTemplateDir+"/Action.java.ftl";
	
	public static final String action_template_just_from_main_dir=project_path+genTemplateDir+"/Action_main.java.ftl";
	
	//dao
	public static final String daoInterface_template_dir=project_path+genTemplateDir+"/daointerface.java.ftl";
	public static final String daoImpl_template_dir=project_path+genTemplateDir+"/daoimpl.java.ftl";
	
	//Service
	public static final String serviceInterface_template_dir=project_path+genTemplateDir+"/ServiceInterface.java.ftl";
	public static final String serviceImpl_template_dir=project_path+genTemplateDir+"/ServiceImpl.java.ftl";
	
	//jsp
	public static final String jsp_list_dataGrid_template_dir=project_path+genTemplateDir+"/jsp_data_dataGrid.jsp.ftl";
	public static final String jsp_list_treeGrid_template_dir=project_path+genTemplateDir+"/jsp_data_treeGrid.jsp.ftl";
	
	public static final String jsp_add_template_dir=project_path+genTemplateDir+"/jsp_Add.jsp.ftl";
	public static final String jsp_update_template_dir=project_path+genTemplateDir+"/jsp_Update.jsp.ftl";
	public static final String jsp_listforlookup_template_dir=project_path+genTemplateDir+"/jsp_ListLookUp.jsp.ftl";

}
