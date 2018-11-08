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

import com.robot.robot.domain.TNonexistentFaqDO;
import com.robot.robot.service.TNonexistentFaqService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 未找到的问答表
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-18 11:43:24
 */
 
@Controller
@RequestMapping("/robot/tNonexistentFaq")
public class TNonexistentFaqController {
	@Autowired
	private TNonexistentFaqService tNonexistentFaqService;
	
	@GetMapping()
	@RequiresPermissions("robot:tNonexistentFaq:tNonexistentFaq")
	String TNonexistentFaq(){
	    return "robot/tNonexistentFaq/tNonexistentFaq";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tNonexistentFaq:tNonexistentFaq")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TNonexistentFaqDO> tNonexistentFaqList = tNonexistentFaqService.list(query);
		int total = tNonexistentFaqService.count(query);
		PageUtils pageUtils = new PageUtils(tNonexistentFaqList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tNonexistentFaq:add")
	String add(){
	    return "robot/tNonexistentFaq/tNonexistentFaqAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tNonexistentFaq:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TNonexistentFaqDO tNonexistentFaq = tNonexistentFaqService.get(id);
		model.addAttribute("tNonexistentFaq", tNonexistentFaq);
	    return "robot/tNonexistentFaq/tNonexistentFaqEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tNonexistentFaq:add")
	public R save( TNonexistentFaqDO tNonexistentFaq){
		if(tNonexistentFaqService.save(tNonexistentFaq)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tNonexistentFaq:edit")
	public R update( TNonexistentFaqDO tNonexistentFaq){
		tNonexistentFaqService.update(tNonexistentFaq);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tNonexistentFaq:remove")
	public R remove( Long id){
		if(tNonexistentFaqService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tNonexistentFaq:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tNonexistentFaqService.batchRemove(ids);
		return R.ok();
	}
	
}
