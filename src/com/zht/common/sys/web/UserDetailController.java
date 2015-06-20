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
import org.zht.framework.util.ZStrUtil;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.DataSet;

import com.zht.common.rabc.aop.RefreashAuthCacahe;
import com.zht.common.rabc.service.IRbacRoleService;
import com.zht.common.sys.model.UserDetail;
import com.zht.common.sys.service.IDepartmentService;
import com.zht.common.sys.service.IPositionService;
import com.zht.common.sys.service.IUserDetailService;
@Controller 
@RequestMapping("/common/sys/userDetail")
public class UserDetailController extends BaseController {

	private String jspPrefix="/common/sys/userDetail/";
	@Autowired
	private IUserDetailService userDetailService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IRbacRoleService rbacRoleService;
/*--------------------------------------------------------------------------------*/ 	
	@RequiresPermissions("UserDetail:enterUserDetailPage")
	@RequestMapping(value="/enterUserDetailPage")
	public String enterUserDetailPage() throws Exception {
		return jspPrefix+"userDetailDataGrid";	
	}
/*--------------------------------------------------------------------------------*/  		
	@RequiresPermissions("UserDetail:loadUserDetailGridView")
    @RequestMapping(value="/loadUserDetailGridView")
    @ResponseBody 
    public Object loadUserDetailGridView(@ModelAttribute("paramObject") ParamObject paramObject){
		DataSet grid=  userDetailService.loadUserDetailDataSet(paramObject);
	    return FastjsonUtil.convert(grid);
    }
	
	@SuppressWarnings("rawtypes")
//	@RequiresPermissions("UserDetail:loadUserDetailCombox")
    @RequestMapping(value="/loadUserDetailCombox")
    @ResponseBody 
    public Object loadUserDetailCombox(){
		List<Map> list=userDetailService.loadUserDetailCombox();
	    return FastjsonUtil.convert(list);
    }
/*--------------------------------Detail------------------------------------------------*/  
	@RequiresPermissions("UserDetail:enterUserDetailDetail")
	@RequestMapping(value="/enterUserDetailDetail")
	public String enterUserDetailDetail(Model model , Long id) throws Exception {	
		UserDetail userDetail=userDetailService.$base_find(id);
		setDataAttribute(model,userDetail,"userDetail");
		return jspPrefix+"userDetailDetail";
	}	
	
	@RequiresPermissions("UserDetail:enterUserDetailDeptAssignment")
    @RequestMapping(value="/enterUserDetailDeptAssignment")
    public String enterUserDetailDeptAssignment(Long id,Model model){
		UserDetail userDetail=userDetailService.$base_find(id);
		List<Long> deptIds=departmentService.findDepartmentIdsByUserId(id);
		
    	setDataAttribute(model,userDetail,"userDetail");
    	setDataAttribute(model,ZStrUtil.parseToSplitStr(deptIds),"deptIds");
    	return jspPrefix+"deptAssignment";
    }
	@RequiresPermissions("UserDetail:assignmentUserToDept")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/assignmentUserToDept")
    public Object assignmentUserToDept(String deptIds,Long userId){
		userDetailService.assignmentUserToDept(deptIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }
//--------------------------------------------------------------------------------
	
	@RequiresPermissions("UserDetail:enterUserDetailPositionAssignment")
    @RequestMapping(value="/enterUserDetailPositionAssignment")
    public String enterUserDetailPositionAssignment(Long id,Model model){
		
		UserDetail userDetail=userDetailService.$base_find(id);
		List<Long> positonIds=positionService.findPositionIdsByUserId(id);
    	setDataAttribute(model,userDetail,"userDetail");
    	setDataAttribute(model,ZStrUtil.parseToSplitStr(positonIds),"positonIds");
    	Long defaultPositionId=userDetail.getDefaultPosition()==null?null:userDetail.getDefaultPosition().getId();
    	setDataAttribute(model,defaultPositionId,"defaltPositonId");
    	
    	List<String> roleNames=rbacRoleService.findRoleNameUserHave(id,true);
    	List<String> deptNames = departmentService.findDepartmentNamesByUserId(id);
    	setDataAttribute(model,ZStrUtil.join(roleNames==null?"":roleNames),"roleNamesUserHave");
    	setDataAttribute(model,ZStrUtil.join(deptNames==null?"":deptNames),"deptNamesUserHave");
    	
    	return jspPrefix+"positionAssignment";
    }
	
	@RequiresPermissions("UserDetail:assignPositionUser")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/assignPositionUser")
    public Object assignPositionUser(String positonIds,Long defaltPositonId,Long userId){
		userDetailService.assignPositionUser(positonIds,defaltPositonId,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }
	
/*-------------------------Add-------------------------------------------------------*/   	
	@RequiresPermissions("UserDetail:enterAddUserDetail")
	@RequestMapping(value="/enterAddUserDetail")
	public String enterAddUserDetail() throws Exception {	
		return jspPrefix+"userDetailAdd";
	}	   
	@RequiresPermissions("UserDetail:addUserDetail")
    @ResponseBody
    @RequestMapping(value="/addUserDetail") 
    public Object addUserDetail(@Valid @ModelAttribute("userDetail")UserDetail userDetail,BindingResult bindingResult){
    	processValidateResult(bindingResult);
//    	userDetailService.$base_save(userDetail);
    	userDetailService.createUserDetail(userDetail);
    	return ajaxDoneSuccess("数据添加成功 ");
    }
    
/*---------------------------Update-----------------------------------------------------*/    
	@RequiresPermissions("UserDetail:enterUpdateUserDetail")
	@RequestMapping(value="/enterUpdateUserDetail") 
 	public String enterUpdateUserDetail(Model model , Long id) throws Exception {
		UserDetail userDetail=userDetailService.$base_find(id);
		setDataAttribute(model,userDetail,"userDetail");
		return jspPrefix+"userDetailUpdate";
	}       
	
	@RequiresPermissions("UserDetail:updateUserDetail")
    @ResponseBody
    @RequestMapping(value="/updateUserDetail") 
    public Object updateUserDetail(@Valid @ModelAttribute("userDetail")UserDetail userDetail,BindingResult bindingResult) throws Exception{
    	processValidateResult(bindingResult);
    	userDetailService.$base_update(userDetail);
    	return ajaxDoneSuccess("数据修改成功 ");
    }
/*------------------------------simpleDelete--------------------------------------------------*/    
	@RequiresPermissions("UserDetail:simpleDeleteUserDetail")
	@ResponseBody 
	@RequestMapping(value="/simpleDeleteUserDetail")
    public Object simpleDeleteUserDetail(Long id) throws Exception {
    	userDetailService.$base_delete$Just(id);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    @RequiresPermissions("UserDetail:simpleBatchDeleteUserDetail")
	@ResponseBody 
	@RequestMapping(value="/simpleBatchDeleteUserDetail")
    public Object simpleBatchDeleteRbacUserSession(Long[] ids) throws Exception {
    	userDetailService.$base_deleteByIdsInCase$Just(ids);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    
/*-------------------------------------------------------------------------------------------------------*/
}
