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

import com.robot.robot.domain.TQueueInfoDO;
import com.robot.robot.service.TQueueInfoService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 智能排队
 * 
 * @author lao
 * @email ***
 * @date 2018-06-19 18:30:53
 */
 
@Controller
@RequestMapping("/robot/tQueueInfo")
public class TQueueInfoController {
	@Autowired
	private TQueueInfoService tQueueInfoService;
	
	@GetMapping()
	@RequiresPermissions("robot:tQueueInfo:tQueueInfo")
	String TQueueInfo(){
	    return "robot/tQueueInfo/tQueueInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tQueueInfo:tQueueInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TQueueInfoDO> tQueueInfoList = tQueueInfoService.list(query);
		int total = tQueueInfoService.count(query);
		PageUtils pageUtils = new PageUtils(tQueueInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tQueueInfo:tQueueInfo")
	String add(){
	    return "robot/tQueueInfo/tQueueInfoAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tQueueInfo:tQueueInfo")
	String edit(@PathVariable("id") Integer id,Model model){
		TQueueInfoDO tQueueInfo = tQueueInfoService.get(id);
		model.addAttribute("tQueueInfo", tQueueInfo);
	    return "robot/tQueueInfo/tQueueInfoEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tQueueInfo:tQueueInfo")
	public R save( TQueueInfoDO tQueueInfo){
		Date date=new Date();
		tQueueInfo.setCreatetime(date);
		if(tQueueInfoService.save(tQueueInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tQueueInfo:tQueueInfo")
	public R update( TQueueInfoDO tQueueInfo){
		tQueueInfoService.update(tQueueInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tQueueInfo:tQueueInfo")
	public R remove( Integer id){
		if(tQueueInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tQueueInfo:tQueueInfo")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tQueueInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
