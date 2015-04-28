/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.zht.framework.zhtdao.identity.PKBaseEntity;
/**
 * 
* @ClassName :RbacRole     
* @Description :   
* @createTime :2015年4月3日  下午4:15:24   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_role", uniqueConstraints = { @UniqueConstraint(columnNames = "code") })
public class RbacRole extends PKBaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 定义最终静态类型 ： 角色code的前缀
	 */
	@Transient
	public static final String AUTHORITY_PREFIX = "ROLE_";

	@Transient
	public String getPrefixedName() {
		return AUTHORITY_PREFIX + code;
	}
	public RbacRole() {
        super();
	}
	
	@NotNull
	@NotBlank
	@Column(name = "code", length = 40, nullable = false,unique=true)
	private String code;	
	
	@Column(name = "name", length = 40, nullable = false)
	private String name;
	
	@Column(name = "description", length = 60, nullable = true)
	private String description;
	
	@Column(name = "enabled",  nullable = false)
	private Boolean enabled;
	
//	@ManyToMany(fetch=FetchType.LAZY)
//    @JoinTable(name="rbac_role_permission", joinColumns = { 
//        @JoinColumn(name="role_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
//        @JoinColumn(name="permission_id", nullable=false, updatable=false) })
//	private Set<RbacPermission> rbacRolePermission = new HashSet<RbacPermission>(0);
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="rbacRole")
	private Set<RbacRolePermission> rbacRolePermissions = new HashSet<RbacRolePermission>(0);
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
//	public Set<RbacPermission> getRbacRolePermission() {
//		return rbacRolePermission;
//	}
//
//	public void setRbacRolePermission(Set<RbacPermission> rbacRolePermission) {
//		this.rbacRolePermission = rbacRolePermission;
//	}


	public Set<RbacRolePermission> getRbacRolePermissions() {
		return rbacRolePermissions;
	}
	public void setRbacRolePermissions(Set<RbacRolePermission> rbacRolePermissions) {
		this.rbacRolePermissions = rbacRolePermissions;
	}
	public RbacRole(Long id) {
        super();
        this.setId(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
