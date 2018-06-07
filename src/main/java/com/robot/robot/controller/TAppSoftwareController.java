package com.robot.robot.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TAppSoftwareDO;
import com.robot.robot.service.TAppSoftwareService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 机器人版本号表
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-23 11:17:33
 */
 
@Controller
@RequestMapping("/robot/tAppSoftware")
public class TAppSoftwareController {
	@Autowired
	private TAppSoftwareService tAppSoftwareService;
	
	@GetMapping()
	@RequiresPermissions("robot:tAppSoftware:tAppSoftware")
	String TAppSoftware(){
	    return "robot/tAppSoftware/tAppSoftware";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tAppSoftware:tAppSoftware")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TAppSoftwareDO> tAppSoftwareList = tAppSoftwareService.list(query);
		int total = tAppSoftwareService.count(query);
		PageUtils pageUtils = new PageUtils(tAppSoftwareList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tAppSoftware:add")
	String add(){
	    return "robot/tAppSoftware/tAppSoftwareAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tAppSoftware:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TAppSoftwareDO tAppSoftware = tAppSoftwareService.get(id);
		model.addAttribute("tAppSoftware", tAppSoftware);
	    return "robot/tAppSoftware/tAppSoftwareEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tAppSoftware:add")
	public R save( TAppSoftwareDO tAppSoftware){
		if(tAppSoftwareService.save(tAppSoftware)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tAppSoftware:edit")
	public R update( TAppSoftwareDO tAppSoftware){
		tAppSoftwareService.update(tAppSoftware);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tAppSoftware:remove")
	public R remove( Integer id){
		if(tAppSoftwareService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tAppSoftware:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tAppSoftwareService.batchRemove(ids);
		return R.ok();
	}
	
}
