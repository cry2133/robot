package com.robot.robot.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TKeywordDO;
import com.robot.robot.service.TKeywordService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.R;
import com.robot.common.utils.ShiroUtils;

/**
 * 关键字表
 */
@Controller
@RequestMapping("/robot/tKeyword")
public class TKeywordController {
	@Autowired
	private TKeywordService tKeywordService;
	
	@GetMapping()
	@RequiresPermissions("robot:tKeyword:tKeyword")
	String TKeyword(){
	    return "robot/tKeyword/tKeyword";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tKeyword:tKeyword")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
//        Query query = new Query(params);
        Map<String, Object> query = new HashMap<>(0);
        query.put("name", params.get("name"));
		List<TKeywordDO> tKeywordList = tKeywordService.list(query);
		int total = tKeywordService.count(query);
		PageUtils pageUtils = new PageUtils(tKeywordList, total);
		return pageUtils;
	}
	
	@RequestMapping("/selectKeyWordPage/{type}")
	String selectTypePage(@PathVariable("type") int type){
		if(type==2){
			return "robot/tKeyword/selectMultipleKeyWord";
		}
	    return "robot/tKeyword/selectKeyWord";
	}
	
	@ResponseBody
	@RequestMapping("/selectKeyWord")
	public PageUtils selectKeyWordPage(@RequestParam Map<String, Object> params){
		//查询列表数据
		Map<String, Object> query = new HashMap<>(0);
        query.put("name", params.get("name"));
        List<TKeywordDO> tKeywordList = tKeywordService.list(query);
		int total = tKeywordService.count(query);
		PageUtils pageUtils = new PageUtils(tKeywordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tKeyword:add")
	String add(){
	    return "robot/tKeyword/tKeywordAdd";
	}

	@GetMapping("/edit/{keywordId}")
	@RequiresPermissions("robot:tKeyword:edit")
	String edit(@PathVariable("keywordId") Long keywordId,Model model){
		TKeywordDO tKeyword = tKeywordService.get(keywordId);
		model.addAttribute("tKeyword", tKeyword);
	    return "robot/tKeyword/tKeywordEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tKeyword:add")
	public R save( TKeywordDO tKeyword){
		boolean flag=tKeywordService.existKeyword(tKeyword.getName());
		if(flag){
			return R.error("关键字已存在");
		}
		
		Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
		tKeyword.setCreater(String.valueOf(userId));		//发布人
		tKeyword.setCreatetime(new Date());
		
		if(tKeywordService.save(tKeyword)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tKeyword:edit")
	public R update( TKeywordDO tKeyword){
		tKeywordService.update(tKeyword);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tKeyword:remove")
	public R remove( Long keywordId){
		if(tKeywordService.remove(keywordId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tKeyword:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] keywordIds){
		tKeywordService.batchRemove(keywordIds);
		return R.ok();
	}
	
}
