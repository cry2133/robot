package com.robot.robot.controller;

import java.io.IOException;
import java.util.*;

import com.robot.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TNewFaqDO;
import com.robot.robot.service.TNewFaqService;

import javax.annotation.Resource;

/**
 * 新问答表
 * 
 * @author laoGF
 * @date 2018-08-09 19:03:40
 */
@Controller
@RequestMapping("/robot/tNewFaq")
public class TNewFaqController {
	@Resource
	private TNewFaqService tNewFaqService;

	@GetMapping()
	@RequiresPermissions("robot:tNewFaq:tNewFaq")
	String TNewFaq(){
	    return "robot/tNewFaq/tNewFaq";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tNewFaq:tNewFaq")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Long userId = ShiroUtils.getUserId();   //获取当前用户
		query.put("userId",userId);
		List<TNewFaqDO> tNewFaqList = tNewFaqService.list(query);
		int total = tNewFaqService.count(query);
		return new PageUtils(tNewFaqList, total);
	}
	
	@GetMapping("/add")
	String add(){
		return "robot/tNewFaq/tNewFaqAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TNewFaqDO tNewFaq = tNewFaqService.get(id);
		model.addAttribute("tNewFaq", tNewFaq);
	    return "robot/tNewFaq/tNewFaqEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TNewFaqDO tNewFaq){
		if(tNewFaqService.save(tNewFaq)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TNewFaqDO tNewFaq){
		if(tNewFaqService.update(tNewFaq)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tNewFaq:remove")
	public R remove( Long id){
		if(tNewFaqService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tNewFaq:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tNewFaqService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 模型训练
	 */
	@PostMapping( "/trainOrLoadModel")
	@ResponseBody
	public R trainOrLoadModel(@RequestParam("train_file_name") String train_file_name,
							  @RequestParam("model_file_name") String model_file_name){
		try{
			Word2VecTrain.trainOrLoadModel(train_file_name,model_file_name);
		}catch (IOException e){
			e.printStackTrace();
			return R.error("训练失败！请确认路径是否正确！");
		}
		return R.ok("训练成功！");
	}


}
