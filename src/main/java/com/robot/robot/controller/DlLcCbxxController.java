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

import com.robot.robot.domain.DlLcCbxxDO;
import com.robot.robot.service.DlLcCbxxService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;

/**
 * 根据抄表计划和运行电能表等客户档案信息通过抄表数据开放形成的抄表信息及计费电量的存储
 * 
 * @author yobi
 * @email ***
 * @date 2018-03-29 17:44:46
 */
 
@Controller
@RequestMapping("/dl/dlLcCbxx")
public class DlLcCbxxController {
	@Autowired
	private DlLcCbxxService dlLcCbxxService;
	
	@GetMapping()
	@RequiresPermissions("dl:dlLcCbxx:dlLcCbxx")
	String DlLcCbxx(){
	    return "dl/dlLcCbxx/dlLcCbxx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("dl:dlLcCbxx:dlLcCbxx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DlLcCbxxDO> dlLcCbxxList = dlLcCbxxService.list(query);
		int total = dlLcCbxxService.count(query);
		PageUtils pageUtils = new PageUtils(dlLcCbxxList, total);
		return pageUtils;
	}
	
	
}
