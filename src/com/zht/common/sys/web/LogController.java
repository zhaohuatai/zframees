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
import com.zht.common.sys.model.Log;
import com.zht.common.sys.service.ILogService;
@Controller 
@RequestMapping("/sys/common/log")
public class LogController extends BaseController {

	private String jspPrefix="/sys/common/log/";
	@Autowired
	private ILogService logService;
/*--------------------------------------------------------------------------------*/ 	
	@RequiresPermissions("Log:enterLogPage")
	@RequestMapping(value="/enterLogPage")
	public String enterLogPage() throws Exception {
		return jspPrefix+"logDataGrid";	
	}
/*--------------------------------------------------------------------------------*/  	
	
	private static final  RowMap rowMap=new RowMap( 
	"name","name",
	"code","code",
	"depetment","depetment.name",
	"id","id");
/*--------------------------------------------------------------------------------*/  		
	@RequiresPermissions("Log:loadLogGridView")
    @RequestMapping(value="/loadLogGridView")
    @ResponseBody 
    public Object loadLogGridView(@ModelAttribute("paramObject") ParamObject paramObject){
    
		paramObject.addParamItem("key_code", new ParamItem("code",paramObject.getReqParam("code"),"="));
		
	    DataSet grid= logService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	    return FastjsonUtil.convert(grid);
    }
/*--------------------------------Detail------------------------------------------------*/  
	@RequiresPermissions("Log:enterLogDetail")
	@RequestMapping(value="/enterLogDetail")
	public String enterLogDetail(Model model , Long id) throws Exception {	
		Log log=logService.$base_find(id);
		setDataAttribute(model,log,"log");
		return jspPrefix+"logDetail";
	}	
/*-------------------------Add-------------------------------------------------------*/   	
	@RequiresPermissions("Log:enterAddLog")
	@RequestMapping(value="/enterAddLog")
	public String enterAddLog() throws Exception {	
		return jspPrefix+"logAdd";
	}	   
	@RequiresPermissions("Log:addLog")
    @ResponseBody
    @RequestMapping(value="/addLog") 
    public Object addLog(@Valid @ModelAttribute("log")Log log,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	logService.$base_save(log);
    	return ajaxDoneSuccess("数据添加成功 ");
    }
    
/*---------------------------Update-----------------------------------------------------*/    
	@RequiresPermissions("Log:enterUpdateLog")
	@RequestMapping(value="/enterUpdateLog") 
 	public String enterUpdateLog(Model model , Long id) throws Exception {
		Log log=logService.$base_find(id);
		setDataAttribute(model,log,"log");
		return jspPrefix+"logUpdate";
	}       
	@RequiresPermissions("Log:updateLog")
    @ResponseBody
    @RequestMapping(value="/updateLog") 
    public Object updateLog(@Valid @ModelAttribute("log")Log log,BindingResult bindingResult) throws Exception{
    	processValidateResult(bindingResult);
    	logService.$base_update(log);
    	return ajaxDoneSuccess("数据修改成功 ");
    }
/*------------------------------simpleDelete--------------------------------------------------*/    
	@RequiresPermissions("Log:simpleDeleteLog")
	@ResponseBody 
	@RequestMapping(value="/simpleDeleteLog")
    public Object simpleDeleteLog(Long id) throws Exception {
    	logService.$base_delete$Just(id);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    @RequiresPermissions("Log:simpleBatchDeleteLog")
	@ResponseBody 
	@RequestMapping(value="/simpleBatchDeleteLog")
    public Object simpleBatchDeleteRbacUserSession(Long[] ids) throws Exception {
    	logService.$base_deleteByIdsInCase$Just(ids);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
}
