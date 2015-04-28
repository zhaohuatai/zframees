/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zht.framework.zhtdao.identity.PKBaseEntity;
/**
 * 
* @ClassName :RbacModuleMenu     
* @Description :   
* @createTime :2015年4月3日  下午4:15:58   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_module_menu")
public class RbacModuleMenu  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public RbacModuleMenu() {}
	
	public RbacModuleMenu(Long id) {
		this.setId(id);
	}
	public RbacModuleMenu(RbacModule rbacModule,RbacMenu rbacMenu) {
		this.setRbacMenu(rbacMenu);
		this.setRbacModule(rbacModule);
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_menu_id")
	private RbacMenu rbacMenu;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_module_id")
	private RbacModule rbacModule;

	public RbacMenu getRbacMenu() {
		return rbacMenu;
	}

	public void setRbacMenu(RbacMenu rbacMenu) {
		this.rbacMenu = rbacMenu;
	}

	public RbacModule getRbacModule() {
		return rbacModule;
	}

	public void setRbacModule(RbacModule rbacModule) {
		this.rbacModule = rbacModule;
	}
	

	

	
}
