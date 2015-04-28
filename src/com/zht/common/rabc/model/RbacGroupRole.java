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
//注意树形结构，父用户组编辑之后，会影响到自用户组中
/**
 * 
* @ClassName :RbacGroupRole     
* @Description :   
* @createTime :2015年4月3日  下午4:13:29   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_group_role")
public class RbacGroupRole   extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public RbacGroupRole() {}
	public RbacGroupRole(Long id) {
		this.setId(id);
	}
	public RbacGroupRole(RbacGroup rbacGroup, RbacRole rbacRole) {
		super();
		this.rbacGroup = rbacGroup;
		this.rbacRole = rbacRole;
	}

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_group_id")
	private RbacGroup rbacGroup;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_role_id")
	private RbacRole rbacRole;

	public RbacGroup getRbacGroup() {
		return rbacGroup;
	}
	public void setRbacGroup(RbacGroup rbacGroup) {
		this.rbacGroup = rbacGroup;
	}
	public RbacRole getRbacRole() {
		return rbacRole;
	}
	public void setRbacRole(RbacRole rbacRole) {
		this.rbacRole = rbacRole;
	}
	
	
}
