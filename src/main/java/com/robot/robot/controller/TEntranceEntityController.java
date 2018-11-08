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

import com.robot.robot.domain.TEntranceEntityDO;
import com.robot.robot.service.TEntranceEntityService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

import javax.annotation.Resource;

/**
 * 场景入口与词槽实体关联表
 * 
 * @author laoGF
 * @date 2018-09-04 14:11:09
 */
 
@Controller
@RequestMapping("/robot/tEntranceEntity")
public class TEntranceEntityController {
	@Resource
	private TEntranceEntityService tEntranceEntityService;
	
	@GetMapping()
	@RequiresPermissions("robot:tEntranceEntity:tEntranceEntity")
	String TEntranceEntity(){
	    return "robot/tEntranceEntity/tEntranceEntity";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tEntranceEntity:tEntranceEntity")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TEntranceEntityDO> tEntranceEntityList = tEntranceEntityService.list(query);
		int total = tEntranceEntityService.count(query);
		PageUtils pageUtils = new PageUtils(tEntranceEntityList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tEntranceEntity/tEntranceEntityAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TEntranceEntityDO tEntranceEntity = tEntranceEntityService.get(id);
		model.addAttribute("tEntranceEntity", tEntranceEntity);
	    return "robot/tEntranceEntity/tEntranceEntityEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TEntranceEntityDO tEntranceEntity){
		if(tEntranceEntityService.save(tEntranceEntity)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TEntranceEntityDO tEntranceEntity){
		tEntranceEntityService.update(tEntranceEntity);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(tEntranceEntityService.remove(id)>0){
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
		tEntranceEntityService.batchRemove(ids);
		return R.ok();
	}
	
}
