/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import org.zht.framework.util.ZBeanUtil;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.rabc.dao.IRbacMenuDao;
import com.zht.common.rabc.dao.IRbacModuleDao;
import com.zht.common.rabc.model.RbacMenu;
import com.zht.common.rabc.service.IRbacMenuService;
import com.zht.common.rabc.service.IRbacPermissionService;
import com.zht.common.rabc.util.RbacMenuUtil;
import com.zht.common.rabc.view.AccordtionView;
import com.zht.common.rabc.view.LinkbuttonView;
import com.zht.common.shiro.util.ShiroUserUtil;

@Service
@Transactional(rollbackFor=Exception.class)
public class RbacMenuServiceImpl extends BaseServiceImpl<RbacMenu> implements IRbacMenuService{

	@Autowired
	private IRbacModuleDao  rbacMenuModuleDao;
	@Autowired
	private IRbacPermissionService  permissionService;
	@Autowired
	private IRbacMenuDao  rbacMenuDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fillContent(AccordtionView view,List<Map> allMenuList,List<String> permCodeListUserhave){
		List<LinkbuttonView> linkbuttonViewList=new ArrayList<LinkbuttonView>(); 
		for (Map<String,Object> map : allMenuList) {
			Long pid = (Long) map.get("pid");
			if(view.getId().equals(pid)){
				String permCode=(String) map.get("pcode");
				if(ZBeanUtil.isEmptyValue(permCode)){
					continue;
				}
				if(!permCodeListUserhave.contains(permCode)){
					continue;
				}
				LinkbuttonView btnView=new LinkbuttonView();
				btnView.setDisplay((String) map.get("display"));
				btnView.setIconCls((String) map.get("iconCls"));
				String url=(String) map.get("url");
				if(url!=null&&url.startsWith("/")){
					url=ZStrUtil.substringAfter(url,"/");
				}
				btnView.setUrl(url);
				btnView.setId((Long) map.get("id"));
				linkbuttonViewList.add(btnView);
			}
		}
		view.setLinkbuttonViewList(linkbuttonViewList);
	}
	@SuppressWarnings("unchecked")
	public List<Long> findMenuIdListByParentId(String parentId) {
		List<Long> rootIdList = (List<Long>) baseDaoImpl.findJustList("select id from RbacMenu m where m.parentRbacMenu.id = " + parentId,
				new ParamObject(POType.H_NO_NC));
		if (rootIdList == null || rootIdList.size() == 0) {
			throw new ServiceLogicalException("未发现数据根节点，请检查数据 ");
		}
		return rootIdList;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<AccordtionView> loadMenuByUserId(Long moduleId,Long userId) {
		String userNamea=ShiroUserUtil.getUserName();
		
		List<String> permCodeListUserhave=permissionService.findAllPermsUserHaveInDefaultRole(userNamea);
		if(permCodeListUserhave==null||permCodeListUserhave.size()==0){
			return null;
		}
		ParamObject paramObject=new ParamObject(POType.H_NO_NC);
		String hql=
				  " select "
				+ " new map(mm.id as id, "
				+ " mm.parentRbacMenu.id as pid, "
				+ " mm.display as display, "
				+ " mm.iconCls as iconCls, "
				+ " mm.type as type, "
				+ " p.url as url, "
				+ " p.code as pcode) "
				+ " @from RbacMenu mm left outer join  mm.rbacPermission p  "
				+ " where 1=1 ";
				if(moduleId!=null){
					hql +=" and mm.rbacModule.id=:rbacModuleId ";
					paramObject.addParam("rbacModuleId", moduleId);
				}
				hql+=" order by mm.disIndex ";
				List<Map> allMenuList=(List<Map>) baseDaoImpl.findJustList(hql, paramObject);
				if(allMenuList==null||allMenuList.size()==0){
					return null;
				}
				List<AccordtionView> groupList = new ArrayList<AccordtionView>();
				
				for (Map<String,Object> map : allMenuList) {
					String type = (String) map.get("type");
					if (RbacMenu.TYPE_G.equals(type)) {
						AccordtionView view = new AccordtionView();
						view.setTitle((String) map.get("display"));
						view.setIconCls((String) map.get("iconCls"));
						view.setId((Long) map.get("id"));
						fillContent(view, allMenuList,permCodeListUserhave);
						groupList.add(view);
					}
				}
				Iterator<AccordtionView> sListIterator = groupList.iterator(); 
				while(sListIterator.hasNext()){  
					AccordtionView view = sListIterator.next();  
					if(ZBeanUtil.isEmptyValue(view.getLinkbuttonViewList())){
						sListIterator.remove();  
				    }  
				}  
			return groupList;
	}
	
	@Override
	public DataSet  loadRbacMenuTreeGrid(ParamObject paramObject) {
		paramObject.initType(POType.H_NO_NC);
	  String hql=" select "
						 + "m.id as id ,"
				         + "m.parentRbacMenu.id as _parentId ,"
				         + "m.display as display, "
				         + "m.iconCls as iconCls,"
				         + "m.description as description,"
				         + "m.type as type, "
				         + "m.disIndex as disIndex, "
				         + "p.url as url "
				         
				         + "@from RbacMenu m left join m.rbacPermission p";
		DataSet dataSet=baseDaoImpl.loadDataSet(hql,paramObject);
		return dataSet;
	
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> loadMenuCombotree() {
		List<Long> rootIdList = (List<Long>) baseDaoImpl.findJustList("select id from RbacMenu m where m.parentRbacMenu.id is null ",
				new ParamObject(POType.H_NO_NC));
		if (rootIdList == null || rootIdList.size() == 0) {
			throw new ServiceLogicalException("未发现数据根节点，请检查数据 ");
		}

		if (rootIdList.size() > 1) {
			throw new ServiceLogicalException("发现多个根节点数据，请检查数据 ");
		}
		String hql = " select " + "g.id as id ,g.parentRbacMenu.id as _parentId ,g.display as text @from RbacMenu g "
				+ "order by g.parentRbacMenu.id ";

		DataSet dataSet = baseDaoImpl.loadDataSet(hql, new ParamObject(POType.H_NO_NC));
		Map root = dataSet.getRows().get(0);// 菜单根节点 parent_id = null ORDER BY
											// parent_id 根节点位于第一位
		RbacMenuUtil.traverse(root, dataSet.getRows());
		List<Map> mapList = new ArrayList<Map>();
		mapList.add(root);
		return mapList;
	}
 
}