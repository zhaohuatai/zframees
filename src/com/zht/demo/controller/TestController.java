package com.zht.demo.controller;

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
@RequestMapping("/test")
public class TestController extends BaseController {

	private String jspPrefix="/sys/common/log/";
	@Autowired
	private ILogService logService;
	
	
    @RequiresPermissions("Log:simpleBatchDeleteLog")
	@ResponseBody 
	@RequestMapping(value="/simpleBatchDeleteLog")
    public Object simpleBatchDeleteRbacUserSession(Long[] ids) throws Exception {
    	logService.$base_deleteByIdsInCase$Just(ids);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
}
