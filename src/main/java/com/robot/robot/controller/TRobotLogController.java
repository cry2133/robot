package com.robot.robot.controller;

import java.util.ArrayList;
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

import com.robot.robot.domain.TRobotLogDO;
import com.robot.robot.service.TRobotLogService;
import com.alibaba.druid.util.StringUtils;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

/**
 * 机器人故障日志表
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-23 11:17:42
 */
 
@Controller
@RequestMapping("/robot/tRobotLog")
public class TRobotLogController {
	@Autowired
	private TRobotLogService tRobotLogService;
	
	@GetMapping()
	@RequiresPermissions("robot:tRobotLog:tRobotLog")
	String TRobotLog(){
	    return "robot/tRobotLog/tRobotLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tRobotLog:tRobotLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		if(StringUtils.isEmpty(params.get("beginTime").toString())){
			params.put("beginTime", null);
		}
		if(StringUtils.isEmpty(params.get("endTime").toString())){
			params.put("endTime", null);
		}
		//查询列表数据
        Query query = new Query(params);
		List<TRobotLogDO> tRobotLogList = tRobotLogService.list(query);
		int total = tRobotLogService.count(query);
		PageUtils pageUtils = new PageUtils(tRobotLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tRobotLog:add")
	String add(){
	    return "robot/tRobotLog/tRobotLogAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tRobotLog:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TRobotLogDO tRobotLog = tRobotLogService.get(id);
		model.addAttribute("tRobotLog", tRobotLog);
	    return "robot/tRobotLog/tRobotLogEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tRobotLog:add")
	public R save( TRobotLogDO tRobotLog){
		if(tRobotLogService.save(tRobotLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tRobotLog:edit")
	public R update( TRobotLogDO tRobotLog){
		tRobotLogService.update(tRobotLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tRobotLog:remove")
	public R remove( Integer id){
		if(tRobotLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tRobotLog:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tRobotLogService.batchRemove(ids);
		return R.ok();
	}
	
	@GetMapping("statistics")
	@RequiresPermissions("robot:tRobotLog:tRobotLog")
	String statistic(){
	    return "robot/tRobotLog/statisticsLog";
	}
	
	/**
	 * 机器人日志统计
	 */
	@ResponseBody
	@GetMapping("/statisticsList")
	@RequiresPermissions("robot:tRobotLog:tRobotLog")
	public PageUtils statisticsList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TRobotLogDO> tRobotLogList = tRobotLogService.statisticsList(query);
		params.remove("offset");
		params.remove("limit");
		int total = tRobotLogService.statisticsList(params).size();
		PageUtils pageUtils = new PageUtils(tRobotLogList, total);
		return pageUtils;
	}
	
	/**
	 * 日志统计*饼图
	 */
	@ResponseBody
	@GetMapping("/getPie")
	@RequiresPermissions("robot:tRobotLog:tRobotLog")
	public PageUtils getPie(@RequestParam Map<String, Object> params){
		params.put("offset", 0);
		params.put("limit", 20);
		List<TRobotLogDO> tRobotLogList = tRobotLogService.statisticsList(params);
		int total = tRobotLogList.size();
		PageUtils pageUtils = new PageUtils(tRobotLogList, total);
		return pageUtils;
	}

	
	/**
	 * 日志统计*线图
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@GetMapping("/getLine")
	@RequiresPermissions("robot:tRobotLog:tRobotLog")
	public Map<String, Object> getLine(@RequestParam Map<String, Object> params){
		params.put("offset", 0);
		params.put("limit", 10);
		params.put("groupByValue", "robotNo");

		List legendData = new ArrayList();
		List xData = new ArrayList();
		List<Map<String,Object>> seriesData = new ArrayList<>();
		
		List<TRobotLogDO> robotNameList = tRobotLogService.statisticsList(params);    //获取前十机器人名称
		
		//获取所有日志标签
		Map<String, Object> param = new HashMap<>();
		param.put("groupByValue", "tag");
		List<TRobotLogDO> tagList = tRobotLogService.statisticsList(param);
		int dataCount = tagList.size();   //设置纵向data个数

		//加入纵向下标
		for(TRobotLogDO tg : tagList){
			String tag = tg.getTag();
			xData.add(tag);
		}
		
		for(int r=0; r<robotNameList.size(); r++){
			String robotName = robotNameList.get(r).getRobotName();
			String robotNo = robotNameList.get(r).getRobotNo();
		
			List tagCountList = new ArrayList();   //给定纵向data数组
			
			//重载纵向data数组
			for(int c=0; c<dataCount; c++){
				tagCountList.add(0);
			}
			
			Map<String, Object> param2 = new HashMap<String, Object>();
			param2.put("robotNo", robotNo);
			param2.put("groupByValue", "tag");
			List<TRobotLogDO> robotNameCount = tRobotLogService.statisticsList(param2);
			
			//填入纵向数据
			for(int i=0; i<xData.size(); i++){
				String x = (String) xData.get(i);
			
				for(int j=0; j<robotNameCount.size(); j++){
					int count = robotNameCount.get(j).getId();
					String tag = robotNameCount.get(j).getTag();
					//填入对应的纵向数据，没有的为0
					if(x.equals(tag)){
						tagCountList.set(i, count);
					}
				}
			}
			
			if(robotName == null){
				robotName = robotNo;
			}
			legendData.add(robotName);    //加入横线上标
			
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("name", robotName);
			data.put("type", "line");
			//data.put("stack", "总量");      //多线会叠加
			data.put("data", tagCountList);
			
			seriesData.add(data);   //加入纵向数组
			
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("legendData", legendData);
		returnMap.put("xData", xData);
		returnMap.put("seriesData", seriesData);
		return returnMap;
	}
	
}
