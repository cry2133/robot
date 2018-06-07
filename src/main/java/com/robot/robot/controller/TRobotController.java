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

import com.robot.robot.domain.TRobotDO;
import com.robot.robot.service.TRobotService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 机械人账户
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-16 10:58:34
 */
 
@Controller
@RequestMapping("/robot/tRobot")
public class TRobotController {
	@Autowired
	private TRobotService tRobotService;
	
	@GetMapping()
	@RequiresPermissions("robot:tRobot:tRobot")
	String TRobot(){
	    return "robot/tRobot/tRobot";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tRobot:tRobot")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TRobotDO> tRobotList = tRobotService.list(query);
		int total = tRobotService.count(query);
		PageUtils pageUtils = new PageUtils(tRobotList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tRobot:add")
	String add(){
	    return "robot/tRobot/tRobotAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tRobot:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TRobotDO tRobot = tRobotService.get(id);
		model.addAttribute("tRobot", tRobot);
	    return "robot/tRobot/tRobotEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tRobot:add")
	public R save( TRobotDO tRobot){
		if(tRobotService.save(tRobot)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tRobot:edit")
	public R update( TRobotDO tRobot){
		tRobotService.update(tRobot);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tRobot:remove")
	public R remove( Long id){
		if(tRobotService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tRobot:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tRobotService.batchRemove(ids);
		return R.ok();
	}
	
}
