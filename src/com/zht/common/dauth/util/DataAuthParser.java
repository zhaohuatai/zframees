package com.zht.common.dauth.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.zht.framework.data.RowMap;
import org.zht.framework.util.ZBeanUtil;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.dauth.thread.CurrentReqestInfo;
import com.zht.common.dauth.thread.RequestTLUtil;
import com.zht.common.dauth.thread.RequestThreadLocal;
import com.zht.common.shiro.ShiroSessionUser;
import com.zht.common.shiro.util.ShiroUserUtil;

public class DataAuthParser {
	
	@Resource(name="dataAuthMapSource")
	DataAuthMapSource dataAuthMapSourceComponent;
	
	public  String parserExpList(String daoMethedName){
		CurrentReqestInfo currentReqestInfo=RequestThreadLocal.getCurrentReqestInfo();
		if(currentReqestInfo==null){
			return null;
		}
		RequestTLUtil.addDaoAcessMethed(daoMethedName);
		
		String requestPermissionCode=currentReqestInfo.getRquestPermissionCode();
	
		////<rbac_permissionCode,Collection<dataPrivilege>>
		dataAuthMapSourceComponent.refreshResourceDefine();
		Map<String, Collection<String>> permissionDataPrivilegeMapSource=dataAuthMapSourceComponent.getPermissionDataPrivilegeMapSource();
		if(permissionDataPrivilegeMapSource==null||permissionDataPrivilegeMapSource.size()==0){
			return null;
		}
		//<dataPrivilege,Collection<RoleCode>>
		Map<String, Collection<String>> dataPrivilegeRoleMapSource=dataAuthMapSourceComponent.getDataPrivilegeRoleMapSource();
		if(dataPrivilegeRoleMapSource==null||dataPrivilegeRoleMapSource.size()==0){
			return null;
		}
		////<rbac_dataPrivilege,Collection<RbacDataAccessExp>>
		Map<String, Collection<Map<String, Object>>> dataPrivilegeDataAccessExpMapSource=dataAuthMapSourceComponent.getDataPrivilegeDataAccessExpMapSource();
		if(dataPrivilegeDataAccessExpMapSource==null||dataPrivilegeDataAccessExpMapSource.size()==0){
			return null;
		}
		//step 1：判断该功能是否分配DataPrivilege，{RbacUser:loadUserGridView=[asdas]}-- RbacUser:loadUserGridView
		if(!permissionDataPrivilegeMapSource.keySet().contains(requestPermissionCode)){
			return null;
		}
		//step 2：获取已经分配的DataPrivilege集合
		List<String> targetDataPrivilegeList=(List<String>) permissionDataPrivilegeMapSource.get(requestPermissionCode);
		if(targetDataPrivilegeList==null||targetDataPrivilegeList.size()==0){
			return null;
		}
		//step 3：获取用户当前角色
		String currentRoleCode=ShiroUserUtil.getCurrentRoleCode();	
		//step 4：获取已经分配给该角色的PrivilegeCode集合
		List<String> roleDitrabutedDataPrivilegeCodeList = decideByRoleCode(targetDataPrivilegeList,dataPrivilegeRoleMapSource,currentRoleCode);
		if(roleDitrabutedDataPrivilegeCodeList!=null&&roleDitrabutedDataPrivilegeCodeList.size()==0){
			return null ;
		}
		List<String> expList=new ArrayList<String>();
		for(String dataPrivilegeCode :roleDitrabutedDataPrivilegeCodeList){
			List<Map<String, Object>> datapExp = (List<Map<String, Object>>) dataPrivilegeDataAccessExpMapSource.get(dataPrivilegeCode);
			for(Map<String, Object> mm:datapExp){
				String daoAcessMethedInConfig=(String) mm.get("daoAcessMethed");
				int calledTimeInConfig=ZBeanUtil.parseInteger(mm.get("calledTime"),-1);
				if(daoMethedName.equals(daoAcessMethedInConfig)){
					int inthreadcalledTime=RequestTLUtil.getDaoAcessMethedCurrentCallTime(daoMethedName);
					if(calledTimeInConfig==inthreadcalledTime){
						String exp=(String) mm.get("authExp");
						expList.add(exp);
					}
					
				}
			}
		}
		return parseListToSQL(expList);
	}
	//column a>#@uid# {field}{express}{value}
	// {id}{#f# in (select id from DepartmentUser du where du.department=#v#)}{@did}
	//" and id in (select id from DepartmentUser du where du.department ="++") "
	private String parseListToSQL(List<String> expList){
		ShiroSessionUser user=ShiroUserUtil.getShiroSessionUser();
		if(expList==null||expList.size()==0){
			return null;
		}
		String resVa=" ";
		for(String str:expList){
//			String[] oneExp=str.split("#");
//			if(oneExp==null||oneExp.length!=2){
//				continue;
//			}
			//{id}{#f# in (select id from DepartmentUser du where du.department=#v#)}{@did}
			str=str.replace("}", "");
			str=str.replaceFirst("\\{", "");
			String[] sdsd=str.split("\\{");
			
			String filed=sdsd[0].trim();
			String exp=sdsd[1].trim();
			String value=sdsd[2].trim();
		
			exp=exp.replace("#f#", RowMap.queryAsAlias+"."+filed);
			exp=exp.replace("#v#", value);
			user.setCurrentDeptId(1L);
			if(DataAuthConst.uid.equals(value)){
				resVa+=" and (" +exp.replace(value, ""+user.getPersonId())+" )";
			}else if(DataAuthConst.rid.equals(value)){
				resVa+=" and (" +exp.replace(value, ""+user.getCurrentRoleId())+" )";
			}else if(DataAuthConst.pid.equals(value)){
				resVa+=" and (" +exp.replace(value, ""+user.getCurrentPositionId())+" )";
			}else if(DataAuthConst.did.equals(value)){
				resVa+=" and (" +exp.replace(value, ""+user.getCurrentDeptId())+" )";
			}else{
				resVa+=" and ("+exp+") ";
			}
			
		}
		return resVa;
	}
	
	//second setp
	private List<String>  decideByRoleCode(Collection<String> dataPrivilegeCodeList, Map<String, Collection<String>> dataPrivilegeRoleMapSource,String userRoleCode){
		if(dataPrivilegeRoleMapSource==null||dataPrivilegeRoleMapSource.size()==0){
			return null;
		}
		if(dataPrivilegeCodeList==null||dataPrivilegeCodeList.size()==0){
			return null;
		}
		List<String> roledDataPrivilegeCodeList=new ArrayList<String>();
		for(String dataPrivilegeCode:dataPrivilegeCodeList ){
			if(dataPrivilegeRoleMapSource.containsKey(dataPrivilegeCode)){
				Collection<String> roleCoseSet=dataPrivilegeRoleMapSource.get(dataPrivilegeCode);
				if(roleCoseSet!=null&&roleCoseSet.size()>0){
					if(roleCoseSet.contains(userRoleCode)){
						roledDataPrivilegeCodeList.add(dataPrivilegeCode);
					}
				}
			}
		}
		return roledDataPrivilegeCodeList;
	}
}
