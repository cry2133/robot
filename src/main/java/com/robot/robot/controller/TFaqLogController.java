package com.robot.robot.controller;

import java.util.*;

import com.robot.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TFaqLogDO;
import com.robot.robot.service.TFaqLogService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

import javax.annotation.Resource;

/**
 * 问答记录表
 * 
 * @author laoGF
 * @date 2018-08-09 19:03:39
 */
 
@Controller
@RequestMapping("/robot/tFaqLog")
public class TFaqLogController {
	@Resource
	private TFaqLogService tFaqLogService;

	List legendData = null;
	Set<String> xData = null;
	List<Map<String,Object>> seriesData = null;

	@GetMapping()
	@RequiresPermissions("robot:tFaqLog:tFaqLog")
	String TFaqLog(){
	    return "robot/tFaqLog/tFaqLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tFaqLog:tFaqLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TFaqLogDO> tFaqLogList = tFaqLogService.list(query);
		int total = tFaqLogService.count(query);
		PageUtils pageUtils = new PageUtils(tFaqLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tFaqLog/tFaqLogAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TFaqLogDO tFaqLog = tFaqLogService.get(id);
		model.addAttribute("tFaqLog", tFaqLog);
	    return "robot/tFaqLog/tFaqLogEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TFaqLogDO tFaqLog){
		if(tFaqLogService.save(tFaqLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequiresPermissions("robot:tFaqLog:edit")
	public R update( TFaqLogDO tFaqLog){
		tFaqLogService.update(tFaqLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tFaqLog:remove")
	public R remove( Long id){
		if(tFaqLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tFaqLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tFaqLogService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/statistics")
	String statistics(){
		return "robot/tFaqLog/statistics";
	}

	@GetMapping("/all")
	String all(){
		return "robot/tFaqLog/all";
	}


	/**
	 * 统计
	 */
	@GetMapping("/statisticsList")
	@ResponseBody
	public Map<String, Object> statisticsList(@RequestParam Map<String, Object> params){
		legendData = new ArrayList();
		xData = new HashSet();
		seriesData = new ArrayList();

		List<TFaqLogDO> list = tFaqLogService.statistics(params);

		Iterator<TFaqLogDO> iterator = list.iterator();
		while(iterator.hasNext()){
			//加入纵向下标
			String tag = iterator.next().getRobotNo();
			xData.add(tag);
		}

		setData(xData,list,0);
		setData(xData,list,1);
		setData(xData,list,2);
		setData(xData,list,3);

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("legendData", legendData);
		returnMap.put("xData", xData);
		returnMap.put("seriesData", seriesData);

		return returnMap;
	}


	/**
	 * 总表
	 */
	@GetMapping("/allList")
	@ResponseBody
	public Map<String, Object> all(@RequestParam Map<String, Object> params){
		legendData = new ArrayList();
		xData = new HashSet();
		seriesData = new ArrayList();

		List<TFaqLogDO> list = tFaqLogService.all(params);

		Iterator<TFaqLogDO> iterator = list.iterator();
		while(iterator.hasNext()){
			//加入纵向下标
			xData.add("总表");
			iterator.next().setRobotNo("总表");
		}

		setData(xData,list,0);
		setData(xData,list,1);
		setData(xData,list,2);
		setData(xData,list,3);

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("legendData", legendData);
		returnMap.put("xData", xData);
		returnMap.put("seriesData", seriesData);

		return returnMap;
	}



	public void setData(Set<String> xData,List<TFaqLogDO> list,int way){

		String lData;
		if(way == 1){
			lData = "单轮应答";
		}else if(way == 2){
			lData = "多轮应答";
		}else if(way == 3){
			lData = "闲聊";
		}else{
			lData = "默认";
		}

		legendData.add(lData);
		List tagCountList = new ArrayList();
		for (String x : xData){
			Long count = 0L;
			for(TFaqLogDO faqLogDO : list){
				if(faqLogDO.getWay() == way && x.equals(faqLogDO.getRobotNo())){
					count = faqLogDO.getId();
				}
			}
			tagCountList.add(count);
		}
		Map<String,Object> data = new HashMap<>();
		data.put("name", lData);
		data.put("type", "bar");
		if(way == 0){
			data.put("barGap", 0);
		}
		data.put("data", tagCountList);
		seriesData.add(data);
	}


}
