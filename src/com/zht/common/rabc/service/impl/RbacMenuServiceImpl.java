/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.zht.framework.util.ZStrUtil;

import com.zht.common.rabc.dao.IRbacMenuDao;
import com.zht.common.rabc.dao.IRbacModuleDao;
import com.zht.common.rabc.model.RbacMenu;
import com.zht.common.rabc.service.IRbacMenuService;
import com.zht.common.rabc.util.RbacMenuUtil;
import com.zht.common.rabc.view.AccordtionView;
import com.zht.common.rabc.view.LinkbuttonView;

@Service
@Transactional(rollbackFor=Exception.class)
public class RbacMenuServiceImpl extends BaseServiceImpl<RbacMenu> implements IRbacMenuService{

	@Autowired
	private IRbacModuleDao  rbacMenuModuleDao;
	@Autowired
	private IRbacMenuDao  rbacMenuDao;
	
	private void fillContent(AccordtionView view,List<Map<String,Object>> data){
		List<LinkbuttonView> linkbuttonViewList=new ArrayList<LinkbuttonView>(); 
		for (Map<String,Object> map : data) {
			Long pid = (Long) map.get("pid");
			if(view.getId().equals(pid)){
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
	
	@Override
	public List<AccordtionView> findMenuListByModuleId(Long moduleId) {
//		if(moduleId==null){
//			return null;
//		}
		ParamObject paramObject=new ParamObject(POType.H_NO_NC);
		String hql=" select "
				+ "mm.id as id, "
				+ "mm.parentRbacMenu.id as pid, "
				+ "mm.display as display, "
				+ "mm.iconCls as iconCls, "
				+ "mm.type as type, "
				+ "p.url as url "
				+ "@from RbacMenu mm left outer join  mm.rbacPermission p ";
				if(moduleId!=null){
					hql +="where mm.rbacModule.id=:rbacModuleId ";
					paramObject.addParam("rbacModuleId", moduleId);
				}
				
		DataSet dataSet=rbacMenuDao.loadDataSet(hql,paramObject);
		
		
		if (dataSet != null && dataSet.getRows() != null) {
			List<AccordtionView> groupList = new ArrayList<AccordtionView>();
			List<Map<String,Object>>   data = dataSet.getRows();
			for (Map<String,Object> map : data) {
				String type = (String) map.get("type");
				if (RbacMenu.TYPE_G.equals(type)) {
					AccordtionView view = new AccordtionView();
					view.setTitle((String) map.get("display"));
					view.setIconCls((String) map.get("iconCls"));
					view.setId((Long) map.get("id"));
					fillContent(view, data);
					groupList.add(view);
				}
			}
			return groupList;
		}
		return null;
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
		List<Long> rootIdList=(List<Long>) baseDaoImpl.findJustList("select id from RbacMenu m where m.parentRbacMenu.id is null ", new ParamObject(POType.H_NO_NC));
		if(rootIdList==null||rootIdList.size()==0){
			throw new ServiceLogicalException("未发现数据根节点，请检查数据 ");
		}
		if(rootIdList.size()>1){
			throw new ServiceLogicalException("发现多个根节点数据，请检查数据 ");
		}
		String hql=" select "
						 + "g.id as id ,"
				         + "g.parentRbacMenu.id as _parentId ,"
				         + "g.name as text "
				         + "@from RbacMenu g ";
		
		DataSet dataSet=baseDaoImpl.loadDataSet(hql,new ParamObject(POType.H_NO_NC));
		Map root=dataSet.getRows().get(0);
		RbacMenuUtil.traverse(root,dataSet.getRows());
		List<Map> mapList=new ArrayList<Map>();
		mapList.add(root);
		return mapList;
	}

	@Override
	public void deleteRbacMenu(Serializable id) {
		baseDaoImpl.delete(RbacMenu.class, id);
		
	}
 
}