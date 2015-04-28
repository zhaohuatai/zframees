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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.zht.framework.zhtdao.identity.PKBaseEntity;

/**
 * 
* @ClassName :RbacMenu     
* @Description :   
* @createTime :2015年4月3日  下午4:16:14   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_menu")
public class RbacMenu extends PKBaseEntity{
	private static final long serialVersionUID = 1L;
	@Transient
	public static final String TYPE_M="M";
	@Transient
	public static final String TYPE_G="G";

	@Column(name="display",nullable=false,length=40)
	private String display;
	
	@Column(name="iconCls", nullable=false, length=40)
	private String iconCls;//
	
	@Column(name="description", nullable=true, length=60)
    private String description;
	
	//M:菜单
	//G：组
	@Column(name="type", nullable=true, length=1)
    private String type;
	
	@Column(name="dis_index")
	@javax.persistence.OrderBy("disIndex")
    private Integer disIndex;

	@Column(name="tab_title", nullable=true, length=40)
	private String tabTitle;//显示的navitab 的 title
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="permission_id", nullable=true)
	private RbacPermission rbacPermission;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private RbacMenu parentRbacMenu;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="module_id")
    private RbacModule rbacModule;
	
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE, mappedBy="parentRbacMenu")
	@javax.persistence.OrderBy("disIndex")
	private Set<RbacMenu> childRbacMenus = new HashSet<RbacMenu>(0);	
		
	public void addChild(RbacMenu menu){
		childRbacMenus.add(menu);
	}
	
	public RbacMenu(){
		
	}
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Integer getDisIndex() {
		return disIndex;
	}

	public void setDisIndex(Integer disIndex) {
		this.disIndex = disIndex;
	}

	public RbacPermission getRbacPermission() {
		return rbacPermission;
	}

	public void setRbacPermission(RbacPermission rbacPermission) {
		this.rbacPermission = rbacPermission;
	}

	public RbacMenu getParentRbacMenu() {
		return parentRbacMenu;
	}

	public void setParentRbacMenu(RbacMenu parentRbacMenu) {
		this.parentRbacMenu = parentRbacMenu;
	}
	public Set<RbacMenu> getChildRbacMenus() {
		return childRbacMenus;
	}
	public void setChildRbacMenus(Set<RbacMenu> childRbacMenus) {
		this.childRbacMenus = childRbacMenus;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RbacModule getRbacModule() {
		return rbacModule;
	}

	public void setRbacModule(RbacModule rbacModule) {
		this.rbacModule = rbacModule;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
