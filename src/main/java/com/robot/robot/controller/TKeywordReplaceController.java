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

import com.robot.robot.domain.TKeywordDO;
import com.robot.robot.domain.TKeywordReplaceDO;
import com.robot.robot.service.TKeywordReplaceService;
import com.robot.robot.service.TKeywordService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.R;
import com.robot.common.utils.StringUtils;

/**
 * 关键字替换
 */
@Controller
@RequestMapping("/robot/tKeywordReplace")
public class TKeywordReplaceController {
	@Autowired
	private TKeywordReplaceService tKeywordReplaceService;
	@Autowired
	private TKeywordService tKeywordService;
	
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
		Map<String, Object> query2 = new HashMap<>(0);
		String keyWord = (String)params.get("keyWordName");
		if(StringUtils.isNotEmpty(keyWord)){
			query2.put("name", keyWord);
		    List<TKeywordDO> tKeyword = tKeywordService.list(query2);
		    if(tKeyword.size()>0){
			    for(TKeywordDO kw:tKeyword){
			    	query.put("keywordId", kw.getKeywordId());
			    }
		    }
		}
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
