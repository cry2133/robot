package com.robot.robot.controller;

import java.util.List;
import java.util.Map;

import com.robot.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TEntranceFaqDO;
import com.robot.robot.service.TEntranceFaqService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

import javax.annotation.Resource;

/**
 * 多轮问答表
 * 
 * @author laoGF
 * @date 2018-09-28 16:36:54
 */
 
@Controller
@RequestMapping("/robot/tEntranceFaq")
public class TEntranceFaqController {
	@Resource
	private TEntranceFaqService tEntranceFaqService;
	
	@GetMapping()
	@RequiresPermissions("robot:tEntranceFaq:tEntranceFaq")
	String TEntranceFaq(){
	    return "robot/tEntranceFaq/tEntranceFaq";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tEntranceFaq:tEntranceFaq")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Long userId = ShiroUtils.getUserId();   //获取当前用户
		query.put("userId",userId);
		List<TEntranceFaqDO> tEntranceFaqList = tEntranceFaqService.list(query);
		int total = tEntranceFaqService.count(query);
		PageUtils pageUtils = new PageUtils(tEntranceFaqList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tEntranceFaq/tEntranceFaqAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TEntranceFaqDO tEntranceFaq = tEntranceFaqService.get(id);
		model.addAttribute("tEntranceFaq", tEntranceFaq);
	    return "robot/tEntranceFaq/tEntranceFaqEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TEntranceFaqDO tEntranceFaq){
		if(tEntranceFaqService.save(tEntranceFaq)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TEntranceFaqDO tEntranceFaq){
		tEntranceFaqService.update(tEntranceFaq);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(tEntranceFaqService.remove(id)>0){
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
		tEntranceFaqService.batchRemove(ids);
		return R.ok();
	}
	
}
