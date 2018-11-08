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

import com.robot.robot.domain.TIdentityInfoDO;
import com.robot.robot.service.TIdentityInfoService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 实名采集信息
 * 
 */
 
@Controller
@RequestMapping("/robot/tIdentityInfo")
public class TIdentityInfoController {
	@Autowired
	private TIdentityInfoService tIdentityInfoService;
	
	@GetMapping()
	@RequiresPermissions("robot:tIdentityInfo:tIdentityInfo")
	String TIdentityInfo(){
	    return "robot/tIdentityInfo/tIdentityInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tIdentityInfo:tIdentityInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TIdentityInfoDO> tIdentityInfoList = tIdentityInfoService.list(query);
		int total = tIdentityInfoService.count(query);
		PageUtils pageUtils = new PageUtils(tIdentityInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tIdentityInfo:add")
	String add(){
	    return "robot/tIdentityInfo/tIdentityInfoAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tIdentityInfo:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TIdentityInfoDO tIdentityInfo = tIdentityInfoService.get(id);
		model.addAttribute("tIdentityInfo", tIdentityInfo);
	    return "robot/tIdentityInfo/tIdentityInfoEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tIdentityInfo:add")
	public R save( TIdentityInfoDO tIdentityInfo){
		if(tIdentityInfoService.save(tIdentityInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tIdentityInfo:edit")
	public R update( TIdentityInfoDO tIdentityInfo){
		tIdentityInfoService.update(tIdentityInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tIdentityInfo:remove")
	public R remove( Long id){
		if(tIdentityInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tIdentityInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tIdentityInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
