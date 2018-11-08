package com.robot.robot.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TFaqLogDO;
import com.robot.robot.service.TFaqLogService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

import javax.annotation.Resource;

/**
 * 问答记录表
 * 
 * @author laoGF
 * @date 2018-08-09 19:03:39
 */
 
@Controller
@RequestMapping("/robot/tFaqLog")
public class TFaqLogController {
	@Resource
	private TFaqLogService tFaqLogService;
	
	@GetMapping()
	@RequiresPermissions("robot:tFaqLog:tFaqLog")
	String TFaqLog(){
	    return "robot/tFaqLog/tFaqLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tFaqLog:tFaqLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TFaqLogDO> tFaqLogList = tFaqLogService.list(query);
		int total = tFaqLogService.count(query);
		PageUtils pageUtils = new PageUtils(tFaqLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tFaqLog/tFaqLogAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TFaqLogDO tFaqLog = tFaqLogService.get(id);
		model.addAttribute("tFaqLog", tFaqLog);
	    return "robot/tFaqLog/tFaqLogEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TFaqLogDO tFaqLog){
		if(tFaqLogService.save(tFaqLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequiresPermissions("robot:tFaqLog:edit")
	public R update( TFaqLogDO tFaqLog){
		tFaqLogService.update(tFaqLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tFaqLog:remove")
	public R remove( Long id){
		if(tFaqLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tFaqLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tFaqLogService.batchRemove(ids);
		return R.ok();
	}



	
}
