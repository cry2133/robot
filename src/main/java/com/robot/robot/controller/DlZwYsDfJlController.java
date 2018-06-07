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

import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.robot.domain.DlZwYsDfJlDO;
import com.robot.robot.service.DlZwYsDfJlService;

/**
 * 
 * 电量账务应收电费记录查询
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年6月6日
 */
@Controller
@RequestMapping("/dl/dlZwYsDfJl")
public class DlZwYsDfJlController {
	@Autowired
	private DlZwYsDfJlService dlZwYsDfJlService;
	
	@GetMapping()
	@RequiresPermissions("dl:dlZwYsDfJl:dlZwYsDfJl")
	String DlZwYsDfJl(){
	    return "dl/dlZwYsDfJl/dlZwYsDfJl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("dl:dlZwYsDfJl:dlZwYsDfJl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DlZwYsDfJlDO> dhKhdfList = dlZwYsDfJlService.list(query);
		int total = dlZwYsDfJlService.count(query);
		PageUtils pageUtils = new PageUtils(dhKhdfList, total);
		return pageUtils;
	}
}
