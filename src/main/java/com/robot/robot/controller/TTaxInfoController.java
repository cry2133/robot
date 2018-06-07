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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TTaxInfoDO;
import com.robot.robot.service.TTaxInfoService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 电力业务类型
 * 
 * @author yobi
 * @email ***
 * @date 2018-02-05 17:25:38
 */
 
@Controller
@RequestMapping("/robot/tTaxInfo")
public class TTaxInfoController {
	@Autowired
	private TTaxInfoService tTaxInfoService;
	
	@GetMapping()
	@RequiresPermissions("robot:tTaxInfo:tTaxInfo")
	String TTaxInfo(){
	    return "robot/tTaxInfo/tTaxInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tTaxInfo:tTaxInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TTaxInfoDO> tTaxInfoList = tTaxInfoService.list(query);
		int total = tTaxInfoService.count(query);
		PageUtils pageUtils = new PageUtils(tTaxInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tTaxInfo:add")
	String add(){
	    return "robot/tTaxInfo/tTaxInfoAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tTaxInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TTaxInfoDO tTaxInfo = tTaxInfoService.get(id);
		model.addAttribute("tTaxInfo", tTaxInfo);
	    return "robot/tTaxInfo/tTaxInfoEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tTaxInfo:add")
	public R save( TTaxInfoDO tTaxInfo){
		Date date=new Date();
		tTaxInfo.setCreatetime(date);
		if(tTaxInfoService.save(tTaxInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tTaxInfo:edit")
	public R update( TTaxInfoDO tTaxInfo){
		tTaxInfoService.update(tTaxInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tTaxInfo:remove")
	public R remove( Integer id){
		if(tTaxInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tTaxInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tTaxInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
