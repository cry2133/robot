package com.robot.robot.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.DlKhYdkhDO;
import com.robot.robot.service.DlKhYdkhService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;

/**
 * 该表存放用电客户和考核户、违约窃电黑户的基本信息
1、用电客户定义：依法与供电企业建立供用电关系的组织或个人
 * 
 * @author yobi
 * @email ***
 * @date 2018-03-29 17:44:46
 */
 
@Controller
@RequestMapping("/dl/dlKhYdkh")
public class DlKhYdkhController {
	@Autowired
	private DlKhYdkhService dlKhYdkhService;
	
	@GetMapping()
	@RequiresPermissions("dl:dlKhYdkh:dlKhYdkh")
	String DlKhYdkh(){
	    return "dl/dlKhYdkh/dlKhYdkh";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("dl:dlKhYdkh:dlKhYdkh")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DlKhYdkhDO> dlKhYdkhList = dlKhYdkhService.list(query);
		int total = dlKhYdkhService.count(query);
		PageUtils pageUtils = new PageUtils(dlKhYdkhList, total);
		return pageUtils;
	}
	
	
}
