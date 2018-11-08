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

import com.robot.robot.domain.TKeywordMiddleDO;
import com.robot.robot.service.TKeywordMiddleService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 问答与关键字关系表
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:26:26
 */
 
@Controller
@RequestMapping("/robot/tKeywordMiddle")
public class TKeywordMiddleController {
	@Autowired
	private TKeywordMiddleService tKeywordMiddleService;
	
	@GetMapping()
	@RequiresPermissions("robot:tKeywordMiddle:tKeywordMiddle")
	String TKeywordMiddle(){
	    return "robot/tKeywordMiddle/tKeywordMiddle";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tKeywordMiddle:tKeywordMiddle")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TKeywordMiddleDO> tKeywordMiddleList = tKeywordMiddleService.list(query);
		int total = tKeywordMiddleService.count(query);
		PageUtils pageUtils = new PageUtils(tKeywordMiddleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tKeywordMiddle:add")
	String add(){
	    return "robot/tKeywordMiddle/tKeywordMiddleAdd";
	}

	@GetMapping("/edit/{middleId}")
	@RequiresPermissions("robot:tKeywordMiddle:edit")
	String edit(@PathVariable("middleId") Long middleId,Model model){
		TKeywordMiddleDO tKeywordMiddle = tKeywordMiddleService.get(middleId);
		model.addAttribute("tKeywordMiddle", tKeywordMiddle);
	    return "robot/tKeywordMiddle/tKeywordMiddleEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tKeywordMiddle:add")
	public R save( TKeywordMiddleDO tKeywordMiddle){
		if(tKeywordMiddleService.save(tKeywordMiddle)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tKeywordMiddle:edit")
	public R update( TKeywordMiddleDO tKeywordMiddle){
		tKeywordMiddleService.update(tKeywordMiddle);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tKeywordMiddle:remove")
	public R remove( Long middleId){
		if(tKeywordMiddleService.remove(middleId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tKeywordMiddle:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] middleIds){
		tKeywordMiddleService.batchRemove(middleIds);
		return R.ok();
	}
	
}
