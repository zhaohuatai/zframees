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
* @ClassName :RbacRolePermission     
* @Description :   
* @createTime :2015年4月3日  下午4:15:12   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_role_permission")
public class RbacRolePermission  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public RbacRolePermission() {}
	public RbacRolePermission(Long id) {
		this.setId(id);
	}
	public RbacRolePermission(RbacRole rbacRole, RbacPermission rbacPermission) {
		super();
		this.rbacPermission = rbacPermission;
		this.rbacRole = rbacRole;
	}

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="permission_id")
	private RbacPermission rbacPermission;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="role_id")
	private RbacRole rbacRole;

	public RbacPermission getRbacPermission() {
		return rbacPermission;
	}
	public void setRbacPermission(RbacPermission rbacPermission) {
		this.rbacPermission = rbacPermission;
	}
	public RbacRole getRbacRole() {
		return rbacRole;
	}
	public void setRbacRole(RbacRole rbacRole) {
		this.rbacRole = rbacRole;
	}
	
	
}
