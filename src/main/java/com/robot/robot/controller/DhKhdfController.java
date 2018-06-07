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
import com.robot.robot.domain.DhKhdfDO;
import com.robot.robot.service.DhKhdfService;

/**
 * 
 * 客户电费查询
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年6月6日
 */
@Controller
@RequestMapping("/dl/dhKhDf")
public class DhKhdfController {
	@Autowired
	private DhKhdfService dhKhdfService;
	
	@GetMapping()
	@RequiresPermissions("dl:dhKhDf:dhKhDf")
	String DhKhdf(){
	    return "dl/dhKhDf/dhKhDf";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("dl:dhKhDf:dhKhDf")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DhKhdfDO> dhKhdfList = dhKhdfService.allList(query);
		params.remove("offset");
		params.remove("limit");
		int total = dhKhdfService.allList(params).size();
		PageUtils pageUtils = new PageUtils(dhKhdfList, total);
		return pageUtils;
	}
}
