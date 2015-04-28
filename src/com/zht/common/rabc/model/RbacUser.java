/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.zht.framework.zhtdao.identity.PKBaseEntity;

import com.zht.common.sys.model.Position;
import com.zht.common.sys.model.UserDetail;
/**
 * 
* @ClassName :RbacUser     
* @Description :   
* @createTime :2015年4月3日  下午4:15:01   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_user", uniqueConstraints = { @UniqueConstraint(columnNames = "userName") })
public class RbacUser extends PKBaseEntity  {
	private static final long serialVersionUID = 1L;
	
	public RbacUser() {
		super();
	}

	public RbacUser(Long id) {
		this.setId(id);
	}
	/**
	 * 登录名
	 */
	@Column(name = "userName", length = 30, nullable = false ,unique=true)
	private String userName;	
	
	/**
	 * 别名
	 */
	@Column(name = "alias", length = 30, nullable = true)
	private String alias;	
	
	/**
	 * 密码
	 */
	@Column(name = "password", length = 36, nullable = true)
	private String password;
	
	@Column(name = "salt", length = 70, nullable = true)
	private String salt;
	
	/**
	 * 描述、备注
	 */
	@Column(name = "description", length = 30, nullable = true)
	private String description;
	
	/**
	 * 是否被禁用
	 */
	@Column(name = "enabled",  nullable = false)
	private Boolean enabled;
	
	/**
	 * 账户是否过期
	 */
	@Column(name = "accountNonExpired",  nullable = false)
	private Boolean accountNonExpired ;
	
	/**
	 * 用户认证是否过期
	 */
	@Column(name = "credentialsNonExpired",  nullable = false)
	private Boolean credentialsNonExpired ;
	
	/**
	 * 用户账户是否锁定
	 */
	@Column(name = "accountNonLocked",  nullable = false)
	private Boolean accountNonLocked;
	
	
	
	/**
	 * 共享主键，关联人员基本信息,,
	 */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,optional = false )
    @PrimaryKeyJoinColumn 
	private UserDetail userDetail;
	
	/**
	 * 默认角色
	 */
//----------------------默认角色---zht修改0426
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "defaultRole_id")
	private RbacRole defaultRbacRole;
	
	/**
	 * 默认职位
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "defaultPosition_id")
	private Position defaultPosition;
	

	public RbacRole getDefaultRbacRole() {
		return defaultRbacRole;
	}

	public void setDefaultRbacRole(RbacRole defaultRbacRole) {
		this.defaultRbacRole = defaultRbacRole;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Position getDefaultPosition() {
		return defaultPosition;
	}

	public void setDefaultPosition(Position defaultPosition) {
		this.defaultPosition = defaultPosition;
	}
	
}
