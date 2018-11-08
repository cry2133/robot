package com.robot.robot.controller;

import java.util.List;
import java.util.Map;

import com.robot.common.utils.ShiroUtils;
import com.robot.system.service.UserService;
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

import com.robot.robot.domain.TEntityDO;
import com.robot.robot.service.TEntityService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;

import javax.annotation.Resource;

/**
 * 词槽实体表
 * 
 * @author laoGF
 * @date 2018-09-03 14:09:43
 */
 
@Controller
@RequestMapping("/robot/tEntity")
public class TEntityController {
	@Resource
	private TEntityService tEntityService;
	@Resource
	private UserService tUserService;
	
	@GetMapping()
	@RequiresPermissions("robot:tEntity:tEntity")
	String TEntity(){
	    return "robot/tEntity/tEntity";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tEntity:tEntity")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Long userId = ShiroUtils.getUserId();   //获取当前用户
		query.put("userId",userId);
		List<TEntityDO> tEntityList = tEntityService.list(query);
		int total = tEntityService.count(query);
		PageUtils pageUtils = new PageUtils(tEntityList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "robot/tEntity/tEntityAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TEntityDO tEntity = tEntityService.get(id);
		model.addAttribute("tEntity", tEntity);
	    return "robot/tEntity/tEntityEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TEntityDO tEntity){
		Long userId = ShiroUtils.getUserId();   //获取当前用户
		List<Long> list = tUserService.listRoles(userId);
		if(list.size() > 0) {
			for (Long roleId : list) {
				tEntity.setRoleId(roleId);
			}
		}
		if(tEntityService.save(tEntity)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TEntityDO tEntity){
		tEntityService.update(tEntity);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(tEntityService.remove(id)>0){
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
		tEntityService.batchRemove(ids);
		return R.ok();
	}
	
}
