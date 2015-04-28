///**
// * Copyright (c) 2015 https://github.com/zhaohuatai
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// */
//package com.zht.common.rabc.model;
//import java.util.HashSet;
//import java.util.Set;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Table;
//import javax.persistence.OneToMany;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.zht.framework.zhtdao.identity.PKBaseEntity;
///**
// * 
//* @ClassName :RbacResource     
//* @Description :   
//* @createTime :2015年4月3日  下午4:15:42   
//* @author ：zhaohuatai   
//* @version :1.0
// */
//@Entity()
//@Table(name = "rbac_resource")
//public class RbacResource extends PKBaseEntity{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//
//	public RbacResource() {}
//	/**
//	 * 外键--树形结构父节点
//	 */
//	@ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="parent_id")
//	private RbacResource parentRbacResource;	
//
//	/**
//	 * 资源名称
//	 */
//	@Column(name = "name", length = 40, nullable = false)
//    private String name;
//
//
//	@Column(name = "description", length = 40, nullable = true)
//    private String description;
//	
//	@Column(name = "icon", length = 10)
//    private String icon;
//	
//	
//	@Column(name = "dis_index")
//    private Integer disIndex;
//	
//
////	/**
////	 * 是否禁用
////	 */
////	@Column(name = "enabled", nullable = false)
////    private Boolean enabled;
//
//	/**
//	 * 外键--树形子节点  资源
//	 */
//	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "parentRbacResource")
//	@javax.persistence.OrderBy("disIndex")
//	private Set<RbacResource> childRbacResources = new HashSet<RbacResource>(0);
//
////	/**
////	 * 关联--许可资源
////	 */
////	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "rbacResource")
////	@javax.persistence.OrderBy("id")
////	private Set<RbacPermission> rbacPermissions = new HashSet<RbacPermission>(0);
//	
//	
//	public Set<RbacResource> getChildRbacResources() {
//		return this.childRbacResources;
//	}
//
//	public void setChildRbacResources(Set<RbacResource> rbacResources) {
//		this.childRbacResources = rbacResources;
//	}
//
////	public Set<RbacPermission> getRbacPermissions() {
////		return this.rbacPermissions;
////	}
////
////	public void setRbacPermissions(Set<RbacPermission> rbacPermissions) {
////		this.rbacPermissions = rbacPermissions;
////	}
//	public RbacResource getParentRbacResource() {
//		return parentRbacResource;
//	}
//	public void setParentRbacResource(RbacResource parentRbacResource) {
//		this.parentRbacResource = parentRbacResource;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
////	public Boolean getEnabled() {
////		return enabled;
////	}
////	public void setEnabled(Boolean enabled) {
////		this.enabled = enabled;
////	}
//	
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}
//
//	public String getIcon() {
//		return icon;
//	}
//
//	public void setIcon(String icon) {
//		this.icon = icon;
//	}
//
//	public Integer getDisIndex() {
//		return disIndex;
//	}
//
//	public void setDisIndex(Integer disIndex) {
//		this.disIndex = disIndex;
//	}
//
//	
//}
