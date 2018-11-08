package com.robot.robot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.common.utils.ShiroUtils;
import com.robot.robot.domain.TEntityDO;
import com.robot.robot.domain.TEntranceEntityDO;
import com.robot.robot.service.TEntityService;
import com.robot.robot.service.TEntranceEntityService;
import com.robot.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TEntranceDO;
import com.robot.robot.service.TEntranceService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

import javax.annotation.Resource;

/**
 * 场景入口表
 * 
 * @author laoGF
 * @date 2018-09-04 10:53:08
 */
 
@Controller
@RequestMapping("/robot/tEntrance")
public class TEntranceController {
	@Resource
	private TEntranceService tEntranceService;
	@Resource
	private TEntityService tEntityService;
	@Resource
	private TEntranceEntityService tEntranceEntityService;
	@Resource
	private UserService tUserService;

	
	@GetMapping()
	@RequiresPermissions("robot:tEntrance:tEntrance")
	String TEntrance(){
	    return "robot/tEntrance/tEntrance";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tEntrance:tEntrance")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Long userId = ShiroUtils.getUserId();   //获取当前用户
		query.put("userId",userId);
		List<TEntranceDO> tEntranceList = tEntranceService.list(query);
		int total = tEntranceService.count(query);
		PageUtils pageUtils = new PageUtils(tEntranceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(Model model){
		Map<String,Object> map = new HashMap<>();
		List<TEntityDO> list = tEntityService.list(map);
		model.addAttribute("entity",list);
	    return "robot/tEntrance/tEntranceAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		Map<String,Object> map = new HashMap<>();
		List<TEntityDO> list = tEntityService.list(map);
		map.put("entranceId",id);
		List<TEntranceEntityDO> list2 = tEntranceEntityService.list(map);
		for(TEntityDO entityDO : list){
			for(TEntranceEntityDO entranceEntityDO : list2){
				if(entityDO.getId().equals(entranceEntityDO.getEntityId())){
					entityDO.setCheck(true);
				}
			}
		}
		model.addAttribute("entity",list);
		TEntranceDO tEntrance = tEntranceService.get(id);
		model.addAttribute("tEntrance", tEntrance);
	    return "robot/tEntrance/tEntranceEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TEntranceDO tEntrance){
		Long userId = ShiroUtils.getUserId();   //获取当前用户
		List<Long> list = tUserService.listRoles(userId);
		if(list.size() > 0) {
			for (Long roleId : list) {
				tEntrance.setRoleId(roleId);
			}
		}
		if(tEntranceService.save(tEntrance)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TEntranceDO tEntrance){
		tEntranceService.update(tEntrance);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(tEntranceService.remove(id)>0){
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
		tEntranceService.batchRemove(ids);
		return R.ok();
	}
	
}
