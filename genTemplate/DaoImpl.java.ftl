package ${model.packageName};

import org.springframework.stereotype.Repository;
import org.zht.framework.zhtdao.base.impl.BaseDaoImpl;
import ${model.interfacePackageName}.I${model.entitySimpleClassName}Dao;

@Repository
public class ${model.entitySimpleClassName}DaoImpl extends BaseDaoImpl implements I${model.entitySimpleClassName}Dao {
 	
 
}