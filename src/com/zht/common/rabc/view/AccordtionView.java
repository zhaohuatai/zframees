/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.view;

import java.util.List;

public class AccordtionView {
	private Long id;
	private String title;
	private String iconCls;
	private Boolean collapsible;
	private List<LinkbuttonView> linkbuttonViewList;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<LinkbuttonView> getLinkbuttonViewList() {
		return linkbuttonViewList;
	}
	public void setLinkbuttonViewList(List<LinkbuttonView> linkbuttonViewList) {
		this.linkbuttonViewList = linkbuttonViewList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getCollapsible() {
		return collapsible;
	}
	public void setCollapsible(Boolean collapsible) {
		this.collapsible = collapsible;
	}

	
}
