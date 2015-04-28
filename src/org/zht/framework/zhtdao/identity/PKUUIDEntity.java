/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.zhtdao.identity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.zht.framework.uuid.UUIDType;

@MappedSuperclass
public abstract class PKUUIDEntity extends AbstractEntity<String> {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "base58UuidGenerator")
	@GenericGenerator(name = "base58UuidGenerator", strategy = UUIDType.Base58UuidGenerator)
	@Column(name ="id",nullable=false,length=22)
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public BaseEntity(ID id) {
//		super();
//		this.id = id;
//	}

    
    
}
