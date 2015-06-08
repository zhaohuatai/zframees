package com.zht.common.event;

import org.springframework.context.ApplicationEvent;

public class RoleOrDeptChangedEvent  extends  ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleOrDeptChangedEvent(Object source, String msg) {
		super(source);
	}
	public RoleOrDeptChangedEvent(Object source) {
		super(source);
	}
}
