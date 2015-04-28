package ${model.packageName};
<#if (model.genEntity.isTree == true)>
@org.zht.framework.annos.TreeConstruct()
</#if>
@javax.persistence.Entity()
@javax.persistence.Table(name = "${model.genEntity.tableName}")
public class ${model.entitySimpleClassName} extends org.zht.framework.zhtdao.identity.PKBaseEntity{

	private static final long serialVersionUID = 1L;
	
	public ${model.entitySimpleClassName}() {}
	public ${model.entitySimpleClassName}(Long id) {
		this.setId(id);
	}
	<#list model.genEntityPropertyList as property>
	<#if (property.relationType=='onetomany'|| property.relationType=='manytomanySlaver' || property.relationType=='manytomanyMarster')>
	${property.generatedHibernateModelOfPropertyStr ? default("") }
	private java.util.Set<${property.propertyType}> ${property.propertyName};
	
	<#else>
	${property.generatedHibernateModelOfPropertyStr ? default("") }
	private ${property.propertyType} ${property.propertyName};
	
	</#if>
	</#list>
	//<-------------------------------------------->
	<#list model.genEntityPropertyList as property>
	<#if (property.relationType=='onetomany'|| property.relationType=='manytomanySlaver' || property.relationType=='manytomanyMarster')>
	public void set${property.propertyName ? cap_first}(java.util.Set<${property.propertyType}> ${property.propertyName}){
		this.${property.propertyName}=${property.propertyName};
	}
	public java.util.Set<${property.propertyType}> get${property.propertyName ? cap_first}(){
		return this.${property.propertyName};
	}
	
	<#else>
	public void set${property.propertyName ? cap_first}(${property.propertyType} ${property.propertyName}){
		this.${property.propertyName}=${property.propertyName};
	}
	public ${property.propertyType} get${property.propertyName ? cap_first}(){
		return this.${property.propertyName};
	}
	
	</#if>
	</#list>
	

}
