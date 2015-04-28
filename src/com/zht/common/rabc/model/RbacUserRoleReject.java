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
* @ClassName :RbacUserRoleReject     
* @Description :   
* @createTime :2015年4月3日  下午4:14:23   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_user_role_reject")
public class RbacUserRoleReject  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public RbacUserRoleReject() {}
	
	public RbacUserRoleReject(Long id) {
		this.setId(id);
	}
	public RbacUserRoleReject(RbacUser rbacUser,RbacRole rbacRole) {
		this.setRbacUser(rbacUser);
		this.setRbacRole(rbacRole);
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_user_id")
	private RbacUser rbacUser;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_role_id")
	private RbacRole rbacRole;


	public RbacUser getRbacUser() {
		return rbacUser;
	}

	public void setRbacUser(RbacUser rbacUser) {
		this.rbacUser = rbacUser;
	}

	public RbacRole getRbacRole() {
		return rbacRole;
	}

	public void setRbacRole(RbacRole rbacRole) {
		this.rbacRole = rbacRole;
	}
}
