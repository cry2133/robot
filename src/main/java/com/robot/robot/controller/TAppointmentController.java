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

import com.robot.robot.domain.TAppointmentDO;
import com.robot.robot.service.TAppointmentService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 预约表
 * 
 */
 
@Controller
@RequestMapping("/robot/tAppointment")
public class TAppointmentController {
	@Autowired
	private TAppointmentService tAppointmentService;
	
	@GetMapping()
	@RequiresPermissions("robot:tAppointment:tAppointment")
	String TAppointment(){
	    return "robot/tAppointment/tAppointment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tAppointment:tAppointment")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TAppointmentDO> tAppointmentList = tAppointmentService.list(query);
		int total = tAppointmentService.count(query);
		PageUtils pageUtils = new PageUtils(tAppointmentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tAppointment:add")
	String add(){
	    return "robot/tAppointment/tAppointmentAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tAppointment:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TAppointmentDO tAppointment = tAppointmentService.get(id);
		model.addAttribute("tAppointment", tAppointment);
	    return "robot/tAppointment/tAppointmentEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tAppointment:add")
	public R save( TAppointmentDO tAppointment){
		if(tAppointmentService.save(tAppointment)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tAppointment:edit")
	public R update( TAppointmentDO tAppointment){
		tAppointmentService.update(tAppointment);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tAppointment:remove")
	public R remove( Long id){
		if(tAppointmentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tAppointment:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tAppointmentService.batchRemove(ids);
		return R.ok();
	}
	
}
