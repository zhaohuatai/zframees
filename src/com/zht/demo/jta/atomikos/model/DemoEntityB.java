package com.zht.demo.jta.atomikos.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@javax.persistence.Entity()
@javax.persistence.Table(name = "aaaaaaDemoEntityB")
public class DemoEntityB extends org.zht.framework.zhtdao.identity.PKBaseEntity{

	public DemoEntityB() {}
	public DemoEntityB(Long id) {
		this.setId(id);
	}
	@org.hibernate.validator.constraints.Length(min=0,max=30)
	@javax.persistence.Column(name = "name",unique = false,nullable = true,length = 30)
	private java.lang.String name;
	
	@org.hibernate.validator.constraints.Length(min=0,max=30)
	@javax.validation.constraints.NotNull 
	@org.hibernate.validator.constraints.NotBlank
	@javax.persistence.Column(name = "code",unique = true,nullable = false,length = 30)
	private java.lang.String code;

	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	
	
}
