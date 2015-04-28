/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.view;

public class LinkbuttonView {
	public LinkbuttonView() {
	}
	/**
	 * 
	 * @param url
	 * @param iconCls
	 * @param display
	 */
	public LinkbuttonView(String url, String iconCls, String display) {
		super();
		this.url = url;
		this.iconCls = iconCls;
		this.display = display;
	}
	private Long id;
	private String url;
	private String iconCls;
	private String display;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
