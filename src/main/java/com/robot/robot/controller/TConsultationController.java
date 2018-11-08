package com.robot.robot.controller;

import java.util.Date;
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

import com.robot.robot.domain.TConsultationDO;
import com.robot.robot.service.TConsultationService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 咨询建议表
 * 
 * @author laoGF
 * @email ***
 * @date 2018-09-07 18:14:00
 */
 
@Controller
@RequestMapping("/robot/tConsultation")
public class TConsultationController {
	@Autowired
	private TConsultationService tConsultationService;
	
	@GetMapping()
	@RequiresPermissions("robot:tConsultation:tConsultation")
	String TConsultation(){
	    return "robot/tConsultation/tConsultation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tConsultation:tConsultation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TConsultationDO> tConsultationList = tConsultationService.list(query);
		int total = tConsultationService.count(query);
		PageUtils pageUtils = new PageUtils(tConsultationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tConsultation/tConsultationAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TConsultationDO tConsultation = tConsultationService.get(id);
		model.addAttribute("tConsultation", tConsultation);
	    return "robot/tConsultation/tConsultationEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TConsultationDO tConsultation){
		tConsultation.setCreatetime(new Date());
		if(tConsultationService.save(tConsultation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TConsultationDO tConsultation){
		tConsultationService.update(tConsultation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(tConsultationService.remove(id)>0){
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
		tConsultationService.batchRemove(ids);
		return R.ok();
	}
	
}
