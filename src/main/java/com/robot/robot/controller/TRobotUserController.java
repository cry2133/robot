package com.robot.robot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.common.utils.ShiroUtils;
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

import com.robot.robot.domain.TRobotUserDO;
import com.robot.robot.service.TRobotUserService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 机器人编号管理表
 * 
 * @author laoGF
 * @date 2018-08-27 18:19:08
 */
 
@Controller
@RequestMapping("/robot/tRobotUser")
public class TRobotUserController {
	@Autowired
	private TRobotUserService tRobotUserService;
	
	@GetMapping()
	@RequiresPermissions("robot:tRobotUser:tRobotUser")
	String TRobotUser(){
	    return "robot/tRobotUser/tRobotUser";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tRobotUser:tRobotUser")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Long userId = ShiroUtils.getUserId();     //获取当前用户
		query.put("userId",userId);
		List<TRobotUserDO> tRobotUserList = tRobotUserService.list(query);
		int total = tRobotUserService.count(query);
		return new PageUtils(tRobotUserList, total);
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tRobotUser/tRobotUserAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TRobotUserDO tRobotUser = tRobotUserService.get(id);
		model.addAttribute("tRobotUser", tRobotUser);
	    return "robot/tRobotUser/tRobotUserEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TRobotUserDO tRobotUser){
		Map<String,Object> map = new HashMap<>();
		map.put("robotNo",tRobotUser.getRobotNo());
		List<TRobotUserDO> list = tRobotUserService.list(map);
		if(list.size() > 0){
			return R.error("机器人编号已存在！");
		}
		Long userId = ShiroUtils.getUserId();     //获取当前用户
		tRobotUser.setUserId(userId);
		if(tRobotUserService.save(tRobotUser)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TRobotUserDO tRobotUser){
		Map<String,Object> map = new HashMap<>();
		map.put("robotNo",tRobotUser.getRobotNo());
		List<TRobotUserDO> list = tRobotUserService.list(map);
		if(list.size() > 0){
			return R.error("机器人编号已存在！");
		}
		Long userId = ShiroUtils.getUserId();
		tRobotUser.setUserId(userId);
		tRobotUserService.update(tRobotUser);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(tRobotUserService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids){
		tRobotUserService.batchRemove(ids);
		return R.ok();
	}
	
}
