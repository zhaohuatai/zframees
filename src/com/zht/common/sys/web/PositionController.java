package com.zht.common.sys.web;

import java.util.List;
import java.util.Map;

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

import com.zht.common.sys.model.Position;
import com.zht.common.sys.service.IPositionService;
@Controller 
@RequestMapping("/common/sys/position")
public class PositionController extends BaseController {

	private String jspPrefix="/common/sys/position/";
	@Autowired
	private IPositionService positionService;
/*--------------------------------------------------------------------------------*/ 	
	@RequiresPermissions("Position:enterPositionPage")
	@RequestMapping(value="/enterPositionPage")
	public String enterPositionPage() throws Exception {
		return jspPrefix+"positionDataGrid";	
	}
/*--------------------------------------------------------------------------------*/  	
	
	private static final  RowMap rowMap=new RowMap( 
	"name","name",
	"modifyTime","modifyTime",
	"creator","creator",
	"remark","remark",
	"department","department.name",
	"rbacRole","rbacRole.name",
	"id","id");
/*--------------------------------------------------------------------------------*/  		
	@RequiresPermissions("Position:loadPositionGridView")
    @RequestMapping(value="/loadPositionGridView")
    @ResponseBody 
    public Object loadPositionGridView(@ModelAttribute("paramObject") ParamObject paramObject){
		paramObject.addParamItem("name", new ParamItem("name", "%??%", paramObject.getReqParam("name"), Querylogic.L_like));
	    DataSet grid= positionService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	    return FastjsonUtil.convert(grid);
    }
	
	
	@SuppressWarnings("rawtypes")
//	@RequiresPermissions("UserDetail:loadUserDetailCombox")
    @RequestMapping(value="/loadPositionCombox")
    @ResponseBody 
    public Object loadPositionCombox(){
		List<Map> list=positionService.loadPositionCombox();
	    return FastjsonUtil.convert(list);
    }
/*--------------------------------Detail------------------------------------------------*/  
	@RequiresPermissions("Position:enterPositionDetail")
	@RequestMapping(value="/enterPositionDetail")
	public String enterPositionDetail(Model model , Long id) throws Exception {	
		Position position=positionService.$base_find(id);
		setDataAttribute(model,position,"position");
		return jspPrefix+"positionDetail";
	}
	
	@RequiresPermissions("Position:loadPositionCombobox")
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/loadPositionCombobox")
	@ResponseBody
	public Object loadPositionCombobox() throws Exception {
//		java.util.List<java.util.Map> list= positionService.loadAvilablePositionComboxByUserId();
		List<Map> list= positionService.loadPositionCombox();
		return FastjsonUtil.convert(list);
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/loadAvilablePositionComboboxByUserId")
	@ResponseBody
	public Object loadAvilablePositionComboboxByUserId(Long userId) throws Exception {
		List<Map> list= positionService.loadAvilablePositionComboxByUserId(userId);
		return FastjsonUtil.convert(list);
	}
	
/*-------------------------Add-------------------------------------------------------*/   	
	@RequiresPermissions("Position:enterAddPosition")
	@RequestMapping(value="/enterAddPosition")
	public String enterAddPosition() throws Exception {	
		return jspPrefix+"positionAdd";
	}	   
	@RequiresPermissions("Position:addPosition")
    @ResponseBody
    @RequestMapping(value="/addPosition") 
    public Object addPosition(@Valid @ModelAttribute("position")Position position,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	positionService.$base_save(position);
    	return ajaxDoneSuccess("数据添加成功 ");
    }
    
/*---------------------------Update-----------------------------------------------------*/    
	@RequiresPermissions("Position:enterUpdatePosition")
	@RequestMapping(value="/enterUpdatePosition") 
 	public String enterUpdatePosition(Model model , Long id) throws Exception {
		Position position=positionService.$base_find(id);
		setDataAttribute(model,position,"position");
		return jspPrefix+"positionUpdate";
	}       
	@RequiresPermissions("Position:updatePosition")
    @ResponseBody
    @RequestMapping(value="/updatePosition") 
    public Object updatePosition(@Valid @ModelAttribute("position")Position position,BindingResult bindingResult) throws Exception{
    	processValidateResult(bindingResult);
    	positionService.$base_update(position,false);
    	return ajaxDoneSuccess("数据修改成功 ");
    }
/*------------------------------simpleDelete--------------------------------------------------*/    
	@RequiresPermissions("Position:simpleDeletePosition")
	@ResponseBody 
	@RequestMapping(value="/simpleDeletePosition")
    public Object simpleDeletePosition(Long id) throws Exception {
    	positionService.$base_delete$Just(id);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    @RequiresPermissions("Position:simpleBatchDeletePosition")
	@ResponseBody 
	@RequestMapping(value="/simpleBatchDeletePosition")
    public Object simpleBatchDeleteRbacUserSession(Long[] ids) throws Exception {
    	positionService.$base_deleteByIdsInCase$Just(ids);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    
/*-------------------------------------------------------------------------------------------------------*/
}
