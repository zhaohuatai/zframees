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
* @ClassName :RbacGroupUser     
* @Description :   
* @createTime :2015年4月3日  下午4:16:24   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_group_user")
public class RbacGroupUser  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public RbacGroupUser() {
		
	}
	
	public RbacGroupUser(Long id) {
		this.setId(id);
	}
	
	public RbacGroupUser(RbacUser rbacUser, RbacGroup rbacGroup) {
		super();
		this.rbacUser = rbacUser;
		this.rbacGroup = rbacGroup;
	}

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_user_id")
	private RbacUser rbacUser;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_group_id")
	private RbacGroup rbacGroup;

	public RbacUser getRbacUser() {
		return rbacUser;
	}

	public void setRbacUser(RbacUser rbacUser) {
		this.rbacUser = rbacUser;
	}

	public RbacGroup getRbacGroup() {
		return rbacGroup;
	}

	public void setRbacGroup(RbacGroup rbacGroup) {
		this.rbacGroup = rbacGroup;
	}
	

}
