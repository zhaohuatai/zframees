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
* @ClassName :RbacUserPermission     
* @Description :   
* @createTime :2015年4月3日  下午4:14:51   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_user_permission")
public class RbacUserPermission  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public RbacUserPermission() {}
	
	public RbacUserPermission(Long id) {
		this.setId(id);
	}
	public RbacUserPermission(RbacUser rbacUser,RbacPermission rbacPermission) {
		this.setRbacUser(rbacUser);
		this.setRbacPermission(rbacPermission);
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_user_id")
	private RbacUser rbacUser;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_rbacPermission_id")
	private RbacPermission rbacPermission;
	
	public RbacUser getRbacUser() {
		return rbacUser;
	}

	public void setRbacUser(RbacUser rbacUser) {
		this.rbacUser = rbacUser;
	}

	public RbacPermission getRbacPermission() {
		return rbacPermission;
	}

	public void setRbacPermission(RbacPermission rbacPermission) {
		this.rbacPermission = rbacPermission;
	}

	
}
