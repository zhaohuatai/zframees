package ${model.packageName};

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import ${model.interfacePackageName}.I${model.entitySimpleClassName}Service;
import org.zht.framework.service.impl.BaseServiceImpl;
import ${model.daoPackageName}.I${model.entitySimpleClassName}Dao;
import ${model.entityFullClassName};

@Service
@Transactional(rollbackFor=Exception.class)
public class ${model.entitySimpleClassName}ServiceImpl extends BaseServiceImpl<${model.entitySimpleClassName}> implements I${model.entitySimpleClassName}Service{
 	@Autowired
	private I${model.entitySimpleClassName}Dao ${model.entitySimpleClassName ? uncap_first}Dao;
	
 
}