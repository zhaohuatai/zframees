package com.zht.common.sys.web;

import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.ParamItem;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.RowMap;
import org.zht.framework.data.Querylogic;
import com.zht.common.sys.model.Department;
import com.zht.common.sys.service.IDepartmentService;
@Controller 
@RequestMapping("/common/sys/department")
public class DepartmentController extends BaseController {

	private String jspPrefix="/common/sys/department/";
	@Autowired
	private IDepartmentService departmentService;
/*--------------------------------------------------------------------------------*/ 	
	@RequiresPermissions("Department:enterDepartmentPage")
	@RequestMapping(value="/enterDepartmentPage")
	public String enterDepartmentPage() throws Exception {
		return jspPrefix+"departmentTreeGrid";
	}
/*--------------------------------------------------------------------------------*/  	
	
	private static final  RowMap rowMap=new RowMap( 
	"name","name",
	"remark","remark",
	"modifyTime","modifyTime",
	"parentDepartment","parentDepartment.name",
	"_parentId","parentDepartment.id",
	"creater","creater",
	"id","id");
/*--------------------------------------------------------------------------------*/  		
	@RequiresPermissions("Department:loadDepartmentGridView")
    @RequestMapping(value="/loadDepartmentGridView")
    @ResponseBody 
    public Object loadDepartmentGridView(@ModelAttribute("paramObject") ParamObject paramObject){
    
		paramObject.addParamItem("key_name", new ParamItem("name","%??%",paramObject.getReqParam("name"),"LIKE"));
		paramObject.addParamItem("key_parentDepartment", new ParamItem("parentDepartment.id",paramObject.getReqParam("parentDepartment.id"),"=",Querylogic.T_Long));
		
	    DataSet grid= departmentService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	    return FastjsonUtil.convert(grid);
    }
/*--------------------------------Detail------------------------------------------------*/  
	@RequiresPermissions("Department:enterDepartmentDetail")
	@RequestMapping(value="/enterDepartmentDetail")
	public String enterDepartmentDetail(Model model , Long id) throws Exception {	
		Department department=departmentService.$base_find(id);
		setDataAttribute(model,department,"department");
		return jspPrefix+"departmentDetail";
	}	
/*-------------------------Add-------------------------------------------------------*/   	
	@RequiresPermissions("Department:enterAddDepartment")
	@RequestMapping(value="/enterAddDepartment")
	public String enterAddDepartment() throws Exception {	
		return jspPrefix+"departmentAdd";
	}	   
	@RequiresPermissions("Department:addDepartment")
    @ResponseBody
    @RequestMapping(value="/addDepartment") 
    public Object addDepartment(@Valid @ModelAttribute("department")Department department,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	departmentService.$base_save(department);
    	return ajaxDoneSuccess("数据添加成功 ");
    }
    
/*---------------------------Update-----------------------------------------------------*/    
	@RequiresPermissions("Department:enterUpdateDepartment")
	@RequestMapping(value="/enterUpdateDepartment") 
 	public String enterUpdateDepartment(Model model , Long id) throws Exception {
		Department department=departmentService.$base_find(id);
		setDataAttribute(model,department,"department");
		return jspPrefix+"departmentUpdate";
	}       
	@RequiresPermissions("Department:updateDepartment")
    @ResponseBody
    @RequestMapping(value="/updateDepartment") 
    public Object updateDepartment(@Valid @ModelAttribute("department")Department department,BindingResult bindingResult) throws Exception{
    	processValidateResult(bindingResult);
    	departmentService.$base_update(department);
    	return ajaxDoneSuccess("数据修改成功 ");
    }
/*------------------------------simpleDelete--------------------------------------------------*/    
	@RequiresPermissions("Department:simpleDeleteDepartment")
	@ResponseBody 
	@RequestMapping(value="/simpleDeleteDepartment")
    public Object simpleDeleteDepartment(Long id) throws Exception {
    	departmentService.$base_delete$Just(id);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    @RequiresPermissions("Department:simpleBatchDeleteDepartment")
	@ResponseBody 
	@RequestMapping(value="/simpleBatchDeleteDepartment")
    public Object simpleBatchDeleteRbacUserSession(Long[] ids) throws Exception {
    	departmentService.$base_deleteByIdsInCase$Just(ids);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    
/*-------------------------------------------------------------------------------------------------------*/
	@RequiresPermissions("Department:loadDepartmentCombotree")
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/loadDepartmentCombotree")
	@ResponseBody
	public Object loadDepartmentCombotree() throws Exception {
		java.util.List<java.util.Map> list= departmentService.$base_loadCombotree();
		return FastjsonUtil.convert(list);
	}
}
