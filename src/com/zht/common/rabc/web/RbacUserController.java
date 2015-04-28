/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamItem;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.Querylogic;
import org.zht.framework.data.RowMap;
import org.zht.framework.filter.jcaptcha.JCaptchaFilter;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;

import com.zht.common.rabc.aop.RefreashAuthCacahe;
import com.zht.common.rabc.model.RbacUser;
import com.zht.common.rabc.service.IRbacPermissionService;
import com.zht.common.rabc.service.IRbacRoleService;
import com.zht.common.rabc.service.IRbacUserService;
import com.zht.common.shiro.ShiroSessionUser;
import com.zht.common.shiro.util.ShiroUserUtil;
/**
 * 
* @ClassName :RbacUserController     
* @Description :   
* @createTime :2015年4月3日  下午4:17:50   
* @author ：zhaohuatai   
* @version :1.0
 */
@Controller
@RequestMapping("/rbac/user")
public class RbacUserController extends BaseController{
	private static final String jspPrefix="common/rbac/user/";
	
	@Autowired
    private IRbacPermissionService rbacPermissionService;
	@Autowired
    private IRbacUserService rbacUserService;
	@Autowired
    private IRbacRoleService rbacRoleService;
	
	@RequiresPermissions("RbacUser:enterUserPage")
	@RequestMapping(value="/enterUserPage")
    public String enterUserPage(){
		return jspPrefix+"userDataGrid";
    }
//-------------------------------CRUD--------------------------------------------------------------
	
	RowMap rowMap=new RowMap(RbacUser.class)
			//.addColMap("defaultRole", "defaultRbacRole.name")
			.removeColMap("password")
			.removeColMap("salt");

	@RequiresPermissions("RbacUser:loadUserGridView")
    @RequestMapping(value="/loadUserGridView")
    @ResponseBody 
    public Object loadUserGridView(@ModelAttribute("paramObject") ParamObject paramObject){
	    paramObject.addParamItem("userName", new ParamItem("userName","%??%",paramObject.getReqParam("userName"),Querylogic.L_like));
	    paramObject.addParamItem("alias",  new ParamItem("alias","%??%",paramObject.getReqParam("alias"),Querylogic.L_like));
	    paramObject.addParamItem("enabled",  new ParamItem("enabled",paramObject.getReqParam("enabled"),Querylogic.L_equal));
	    DataSet grid= rbacUserService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	   return FastjsonUtil.convert(grid);
    }

	@RequiresPermissions("RbacUser:enterAddUser")
    @RequestMapping(value="/enterAddUser")
    public String enterAddUser(){
        return jspPrefix+"userAdd";
    }

	@RequiresPermissions("RbacUser:addUser")
    @RefreashAuthCacahe
    @RequestMapping(value="/addUser")
    @ResponseBody 
    public Object addUser(@Valid @ModelAttribute("rbacUser")RbacUser rbacUser,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacUserService.$base_save(rbacUser);
		return ajaxDoneSuccess("数据操作成功 ");
    }
    

	@RequiresPermissions("RbacUser:enterUpdateUser")
    @RequestMapping(value="/enterUpdateUser")
    public String enterUpdateUser(Long id,Model model){
    	RbacUser rbacUser=rbacUserService.$base_find(id);
    	setDataAttribute(model,rbacUser,"rbacUser");
        return jspPrefix+"userEdit";
    }

	@RequiresPermissions("RbacUser:updateUser")
    @RefreashAuthCacahe
    @RequestMapping(value="/updateUser")
    @ResponseBody 
    public Object updateUser(@Valid @ModelAttribute("rbacUser")RbacUser rbacUser,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacUserService.$base_update(rbacUser);
		return ajaxDoneSuccess("数据操作成功 ");
    }

	@RequiresPermissions("RbacUser:deleteUser")
    @RefreashAuthCacahe
    @RequestMapping(value="/deleteUser")
    @ResponseBody 
    public Object deleteUser(Long[] ids){
    	//rbacUserService.deleteRbacUser(ids);
		return ajaxDoneSuccess("数据删除成功 ");
    }
    
//--------------------------------USER-ROLE ------Assign----------------------------------------------

	@RequiresPermissions("RbacUser:enterRoleAssignment")
    @RequestMapping(value="/enterRoleAssignment")
    public String enterRoleAssignment(Long id,Model model){
    	RbacUser rbacUser=rbacUserService.$base_find(id);
    	setDataAttribute(model,rbacUser,"rbacUser");
    	return jspPrefix+"roleAssignment";
    }


	@RequiresPermissions("RbacUser:loadRoleCodeUserHaveForCombox")
    @ResponseBody 
    @RequestMapping(value="/loadRoleCodeUserHaveForCombox")
    public Object loadRoleCodeUserHaveForCombox(Long userId){
    	List<?> data =rbacRoleService.findRoleCodeUserHaveForCombox(userId, true);
    	return FastjsonUtil.convert(data);
    }

	@RequiresPermissions("RbacUser:loadRoleGridForUserRoleAssign")
    @RequestMapping(value="/loadRoleGridForUserRoleAssign")
    @ResponseBody 
    public Object loadRoleGridForUserRoleAssign(Long userId,String type){
    	DataSet grid=null;
    	if("FromUserExtendsGroup".equals(type)){
    		grid=rbacRoleService.findRoleDataSetFromUserExtendsGroupsByUserId(userId,null);
    	}else if("FromUserExtendsDepartment".equals(type)){
    		//grid=rbacRoleService.findRoleDataSetFromUserExtendsDepartmentsByUserId(userId,null);
    	}else if("InUserRole".equals(type)){
    		grid=rbacRoleService.findRoleDataSetInUserRoleByUserId(userId,null);
    	}else if("InUserRoleReject".equals(type)){
    		grid=rbacRoleService.findRoleDataSetInUserRoleRejectByUserId(userId,null);
    	}else if("NotInUserRoleANDUserRoleReject".equals(type)){
    		grid=rbacRoleService.findRoleDataSetNotInUserRole$UserRoleReject(userId,null);
    	}
	    return FastjsonUtil.convert(grid);
    }
	@RequiresPermissions("RbacUser:kickOutUser")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/kickOutUser")
    public Object kickOutUser(Long userId){
		rbacUserService.kickOutUser(userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }
	@RequiresPermissions("RbacUser:addRolesToUserRole")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/addRolesToUserRole")
    public Object addRolesToUserRole(Long[] roleIds,Long userId){
    	rbacRoleService.addRolesToUserRole(roleIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }

	@RequiresPermissions("RbacUser:removeRolesFromUserRole")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/removeRolesFromUserRole")
    public Object removeRolesFromUserRole(Long[] roleIds,Long userId){
    	rbacRoleService.removeRolesFromUserRole(roleIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }
	
	@RequiresPermissions("RbacUser:addRolesToUserRoleReject")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/addRolesToUserRoleReject")
    public Object addRolesToUserRoleReject(Long[] roleIds,Long userId){
    	rbacRoleService.addRolesToUserRoleReject(roleIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }

	@RequiresPermissions("RbacUser:removeRolesFromUserRoleReject")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/removeRolesFromUserRoleReject")
    public Object removeRolesFromUserRoleReject(Long[] roleIds,Long userId){
    	rbacRoleService.removeRolesFromUserRoleReject(roleIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }    
//--------------------------------USER-Permission ------Assign----------------------------------------------

	@RequiresPermissions("RbacUser:enterUserPermissionAssignment")
    @RequestMapping(value="/enterUserPermissionAssignment")
    public String enterUserPermissionAssignment(Long id,Model model){
    	RbacUser rbacUser=rbacUserService.$base_find(id);
    	setDataAttribute(model,rbacUser,"rbacUser");
    	return jspPrefix+"permissionAssignment";
    }

	@RequiresPermissions("RbacUser:loadPermissionGridForUserPermissionAssign")
    @RequestMapping(value="/loadPermissionGridForUserPermissionAssign")
    @ResponseBody 
    public Object loadPermissionGridForUserPermissionAssign(ParamObject po,Long userId,String type){
    	DataSet grid=null;
    	if("InUserPermission".equals(type)){
    		grid=rbacPermissionService.findPermissionDataSetInUserPermissionByUserId(po,userId);
    	}else if("InUserPermissionReject".equals(type)){
    		grid=rbacPermissionService.findPermissionDataSetInUserPermissionRejectByUserId(po,userId);
    	}else if("NotInUserPermAndUserPermReject".equals(type)){
    		grid=rbacPermissionService.findPermissionDataSetNotInUserPermAndUserPermReject(po,userId);
    	}
	    return FastjsonUtil.convert(grid);
    }

	@RequiresPermissions("RbacUser:addPermissionsToUserPermission")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/addPermissionsToUserPermission")
    public Object addPermissionsToUserPermission(Long[] permissionIds,Long userId){
    	rbacPermissionService.addPermissionsToUserPermission(permissionIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }

	@RequiresPermissions("RbacUser:removePermissionsFromUserPermission")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/removePermissionsFromUserPermission")
    public Object removePermissionsFromUserPermission(Long[] permissionIds,Long userId){
    	rbacPermissionService.removePermissionsFromUserPermission(permissionIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }

	@RequiresPermissions("RbacUser:addPermissionsToUserPermissionReject")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/addPermissionsToUserPermissionReject")
    public Object addPermissionsToUserPermissionReject(Long[] permissionIds,Long userId){
    	rbacPermissionService.addPermissionsToUserPermissionReject(permissionIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }

	@RequiresPermissions("RbacUser:removePermissionsFromUserPermissionReject")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/removePermissionsFromUserPermissionReject")
    public Object removePermissionsFromUserPermissionReject(Long[] permissionIds,Long userId){
    	rbacPermissionService.removePermissionsFromUserPermissionReject(permissionIds,userId);
		return ajaxDoneSuccess("数据修改成功 ");
    }    
    //--------------------------------------------------
	@ResponseBody 
    @RequestMapping(value="/login")
    public Object login(HttpServletRequest request,Model model,String userName, String password,String jcaptchaCode) {
    	Boolean jcodeValidate=JCaptchaFilter.validateCaptchaChallenge(request, jcaptchaCode);
    	if(!jcodeValidate){
    		return ajaxDoneError("验证码错误");
    	}
    	  UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
    	 // token.setRememberMe(true);  
    	  Subject currentUser = SecurityUtils.getSubject();
    	  try {  
    		  currentUser.login(token);  
    	   }catch(UnknownAccountException uae){  
               System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");  
               return ajaxDoneError("用户名或密码错误");
       		
           }catch(IncorrectCredentialsException ice){  
               System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");  
               return ajaxDoneError("用户名或密码错误");
           }catch(LockedAccountException lae){  
               System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");  
               return ajaxDoneError("验证未通过,账户已锁定");
           }catch(ExcessiveAttemptsException eae){  
               System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多"); 
               return ajaxDoneError("账户已锁定,错误次数过多");
           }catch(AuthenticationException ae){  
               //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
               System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");  
               return ajaxDoneError("验证未通过");
           } 
	    	  //验证是否登录成功  
	          if(currentUser.isAuthenticated()){ 
	        	  ShiroSessionUser suser=new ShiroSessionUser();
	        	  suser.setCurrentRoleCode("admin");
	        	  currentUser.getSession().setAttribute(ShiroUserUtil.SHIROSESSIONUSER, suser);
	              System.out.println("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
	              return ajaxDoneSuccess("登陆成功");
	          }else{  
	              token.clear();
	              return ajaxDoneError("验证未通过"); 
	          }  
    }
    @RequestMapping(value="/core")
    public Object core() {
    	Subject currentUser = SecurityUtils.getSubject();
    	System.out.println(currentUser);
    	return "redirect:/index.jsp";
    }
    
    
    @RequestMapping(value="/loginout")
    public void loginout() {
    	Subject currentUser = SecurityUtils.getSubject();
        if (SecurityUtils.getSubject().getSession() != null){
        	currentUser.logout();
        }
    }
}
