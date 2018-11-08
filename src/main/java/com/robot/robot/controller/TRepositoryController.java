package com.robot.robot.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.robot.system.domain.RoleDO;
import com.robot.system.service.RoleService;
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

import com.robot.robot.domain.TRepositoryDO;
import com.robot.robot.service.TRepositoryService;
import com.robot.common.domain.Tree;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;
import com.robot.common.utils.ShiroUtils;

/**
 * 知识词库表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:58
 */
 
@Controller
@RequestMapping("/robot/tRepository")
public class TRepositoryController {
	@Autowired
	private TRepositoryService tRepositoryService;
	@Autowired
	RoleService roleService;
	
	@GetMapping()
	@RequiresPermissions("robot:tRepository:tRepository")
	String TRepository(){
	    return "robot/tRepository/tRepository";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tRepository:tRepository")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TRepositoryDO> tRepositoryList = tRepositoryService.list(query);
		int total = tRepositoryService.count(query);
		return new PageUtils(tRepositoryList, total);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tRepository:add")
	String add(Model model){
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
	    return "robot/tRepository/tRepositoryAdd";
	}

	@GetMapping("/edit/{repositoryId}")
	@RequiresPermissions("robot:tRepository:edit")
	String edit(@PathVariable("repositoryId") Long repositoryId,Model model){
		TRepositoryDO tRepository = tRepositoryService.get(repositoryId);
		model.addAttribute("tRepository", tRepository);
		List<RoleDO> roles = roleService.list(repositoryId);
		model.addAttribute("roles", roles);
	    return "robot/tRepository/tRepositoryEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tRepository:add")
	public R save( TRepositoryDO tRepository){
		Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
		tRepository.setCreater(String.valueOf(userId));		//发布人
		tRepository.setCreatetime(new Date());
		System.out.println(tRepository.getRoleId());
		if(tRepositoryService.save(tRepository)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tRepository:edit")
	public R update( TRepositoryDO tRepository){
		tRepositoryService.update(tRepository);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tRepository:remove")
	public R remove( Long repositoryId){
		if(tRepositoryService.remove(repositoryId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tRepository:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] repositoryIds){
		tRepositoryService.batchRemove(repositoryIds);
		return R.ok();
	}
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<TRepositoryDO> tree() {
		Tree<TRepositoryDO> tree = new Tree<TRepositoryDO>();
		tree = tRepositoryService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return "robot/tRepository/tRepositoryTree";
	}

}
