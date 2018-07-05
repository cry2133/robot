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

import com.robot.robot.domain.TQueueDO;
import com.robot.robot.service.TQueueService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 智能排队
 * 
 */
 
@Controller
@RequestMapping("/robot/tQueue")
public class TQueueController {
	@Autowired
	private TQueueService tQueueService;
	
	@GetMapping()
	@RequiresPermissions("robot:tQueue:tQueue")
	String TQueue(){
	    return "robot/tQueue/tQueue";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tQueue:tQueue")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TQueueDO> tQueueList = tQueueService.list(query);
		int total = tQueueService.count(query);
		PageUtils pageUtils = new PageUtils(tQueueList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tQueue:add")
	String add(){
	    return "robot/tQueue/tQueueAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tQueue:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TQueueDO tQueue = tQueueService.get(id);
		model.addAttribute("tQueue", tQueue);
	    return "robot/tQueue/tQueueEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tQueue:add")
	public R save( TQueueDO tQueue){
		if(tQueueService.save(tQueue)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tQueue:edit")
	public R update( TQueueDO tQueue){
		tQueueService.update(tQueue);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tQueue:remove")
	public R remove( Long id){
		if(tQueueService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tQueue:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tQueueService.batchRemove(ids);
		return R.ok();
	}
	
}
