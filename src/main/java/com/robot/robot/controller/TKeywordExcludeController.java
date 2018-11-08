package com.robot.robot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TKeywordExcludeDO;
import com.robot.robot.service.TKeywordExcludeService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.R;

/**
 * 
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:48:51
 */
 
@Controller
@RequestMapping("/robot/tKeywordExclude")
public class TKeywordExcludeController {
	@Autowired
	private TKeywordExcludeService tKeywordExcludeService;
	
	
	@GetMapping()
	String TKeywordExclude(){
	    return "robot/tKeywordExclude/tKeywordExclude";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
//        Query query = new Query(params);
		Map<String, Object> query = new HashMap<>(0);
		if( !"%%".equals( params.get("excludeName") )){
			query.put("keyword", params.get("excludeName"));
		}
		List<TKeywordExcludeDO> tKeywordExcludeList = tKeywordExcludeService.list(query);
		int total = tKeywordExcludeService.count(query);
		PageUtils pageUtils = new PageUtils(tKeywordExcludeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tKeywordExclude/tKeywordExcludeAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TKeywordExcludeDO tKeywordExclude = tKeywordExcludeService.get(id);
		model.addAttribute("tKeywordExclude", tKeywordExclude);
	    return "robot/tKeywordExclude/tKeywordExcludeEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TKeywordExcludeDO tKeywordExclude){
		if(tKeywordExcludeService.save(tKeywordExclude)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TKeywordExcludeDO tKeywordExclude){
		tKeywordExcludeService.update(tKeywordExclude);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(tKeywordExcludeService.remove(id)>0){
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
		tKeywordExcludeService.batchRemove(ids);
		return R.ok();
	}
	
}
