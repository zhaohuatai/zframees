/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;
import org.zht.framework.zhtdao.identity.PKBaseEntity;


/**
 *RBAC 许可资源 POJO实体类 
 * @author zhaoht
 * @version v 1.0
 * @modify 2012-12-07-17
 * 
 * select User from User User join User.groups g where g.id = 1
 * select elements(UserGroup.users) from UserGroup UserGroup where UserGroup.id = 1     
 * 
 * from User User where not exists (select 1 from User u join u.groups g where g.id = 1 and User.id = u.id)   
 * from User User where not exists (from UserGroup UserGroup where UserGroup.id = 1 and UserGroup in elements(User.groups))    
 * 
 */
@Entity()
@Table(name = "rbac_permission", uniqueConstraints = { @UniqueConstraint(columnNames = "code") })
public class RbacPermission extends PKBaseEntity {
	private static final long serialVersionUID = 1L;
	
	public RbacPermission(Long id) {
		this.setId(id);
	}
	
	@NotNull
	@NotBlank
	@Column(name = "code", length = 128, nullable = false,unique=true)
	private String code;	
	@Column(name = "name", length = 40, nullable = false)
	private String name;

	@NotNull
	@NotBlank
	@Column(name = "url", length = 255, nullable = false,unique=true)
	private String url;

	@Column(name = "description", length = 60, nullable = true)
	private String description;	

	@Column(name="enabled", nullable=false)
    private Boolean enabled;

	/**
	 * 许可类型
	 * M :菜单，menu
	 * P：内部链接或按钮：process
	 * 
	 */
	@Column(name="type", nullable=false, length=1)
	private String type;
		
	public RbacPermission() {
        super();
	}
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
