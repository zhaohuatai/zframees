/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.service.impl.BaseServiceImpl;
import com.zht.common.rabc.model.RbacGroupRole;
import com.zht.common.rabc.service.IRbacGroupRoleService;
/**
 * 
* @ClassName :RbacGroupRoleServiceImpl     
* @Description :   
* @createTime :2015年4月3日  下午4:20:38   
* @author ：zhaohuatai   
* @version :1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class RbacGroupRoleServiceImpl extends BaseServiceImpl<RbacGroupRole> implements IRbacGroupRoleService{
}
