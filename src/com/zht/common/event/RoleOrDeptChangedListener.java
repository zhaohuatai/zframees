package com.zht.common.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zht.framework.spring.SpringUtils;

import com.zht.common.sys.service.impl.UserDetailServiceImpl;
@Component
public class RoleOrDeptChangedListener implements ApplicationListener<RoleOrDeptChangedEvent>{
	@Async  
	@Override
	public void onApplicationEvent(RoleOrDeptChangedEvent roleOrDeptChangedEvent) {
		Long userId=(Long) roleOrDeptChangedEvent.getSource();
		if(userId==null){
			return;
		}
		UserDetailServiceImpl detailServiceImpl=SpringUtils.getBean(UserDetailServiceImpl.class);
		detailServiceImpl.processUserPositionChangeWhenNoifyed(userId);
	}

}
