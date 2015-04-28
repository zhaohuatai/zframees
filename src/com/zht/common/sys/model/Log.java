package com.zht.common.sys.model;
@javax.persistence.Entity()
@javax.persistence.Table(name = "sys_log")
public class Log extends org.zht.framework.zhtdao.identity.PKBaseEntity{

	private static final long serialVersionUID = 1L;
	
	public Log() {}
	public Log(Long id) {
		this.setId(id);
	}
	@org.hibernate.validator.constraints.Length(min=0,max=30)	@javax.persistence.Column(name = "name",unique = false,nullable = true,length = 30)
	private java.lang.String name;
	
	@org.hibernate.validator.constraints.Length(min=0,max=30)	@javax.validation.constraints.NotNull 	@org.hibernate.validator.constraints.NotBlank	@javax.persistence.Column(name = "code",unique = true,nullable = false,length = 30)
	private java.lang.String code;
	
	@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)	@javax.persistence.JoinColumn(name="deptment_id")
	private com.zht.common.sys.model.Department depetment;
	
	//<-------------------------------------------->
	public void setName(java.lang.String name){
		this.name=name;
	}
	public java.lang.String getName(){
		return this.name;
	}
	
	public void setCode(java.lang.String code){
		this.code=code;
	}
	public java.lang.String getCode(){
		return this.code;
	}
	
	public void setDepetment(com.zht.common.sys.model.Department depetment){
		this.depetment=depetment;
	}
	public com.zht.common.sys.model.Department getDepetment(){
		return this.depetment;
	}
	
	

}
