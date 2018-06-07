package com.robot.robot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TKeywordReplaceDO;
import com.robot.robot.service.TKeywordReplaceService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:48:51
 */
 
@Controller
@RequestMapping("/robot/tKeywordReplace")
public class TKeywordReplaceController {
	@Autowired
	private TKeywordReplaceService tKeywordReplaceService;
	
	@GetMapping()
	String TKeywordReplace(){
	    return "robot/tKeywordReplace/tKeywordReplace";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
//        Query query = new Query(params);
		Map<String, Object> query = new HashMap<>(0);
	    query.put("charkey", params.get("name"));
		List<TKeywordReplaceDO> tKeywordReplaceList = tKeywordReplaceService.list(query);
		int total = tKeywordReplaceService.count(query);
		PageUtils pageUtils = new PageUtils(tKeywordReplaceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tKeywordReplace/tKeywordReplaceAdd";
	}

	@GetMapping("/edit/{replaceId}")
	String edit(@PathVariable("replaceId") Long replaceId,Model model){
		TKeywordReplaceDO tKeywordReplace = tKeywordReplaceService.get(replaceId);
		model.addAttribute("tKeywordReplace", tKeywordReplace);
	    return "robot/tKeywordReplace/tKeywordReplaceEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TKeywordReplaceDO tKeywordReplace){
		if(tKeywordReplaceService.save(tKeywordReplace)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TKeywordReplaceDO tKeywordReplace){
		tKeywordReplaceService.update(tKeywordReplace);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long replaceId){
		if(tKeywordReplaceService.remove(replaceId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] replaceIds){
		tKeywordReplaceService.batchRemove(replaceIds);
		return R.ok();
	}
	
}
