package com.robot.robot.controller;

import java.util.Date;
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

import com.robot.robot.domain.TMajorDO;
import com.robot.robot.service.TMajorService;
import com.robot.common.config.Constant;
import com.robot.common.domain.Tree;
import com.robot.common.utils.R;
import com.robot.common.utils.ShiroUtils;

/**
 * 专业表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:58
 */
 
@Controller
@RequestMapping("/robot/tMajor")
public class TMajorController {
	@Autowired
	private TMajorService tMajorService;
	
	@GetMapping()
	@RequiresPermissions("robot:tMajor:tMajor")
	String TMajor(){
	    return "robot/tMajor/tMajor";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tMajor:tMajor")
	public List<TMajorDO>  list(@RequestParam Map<String, Object> params){
		//查询列表数据
//        Query query = new Query(params);
		Map<String, Object> query = new HashMap<>(16);
		List<TMajorDO> tMajorList = tMajorService.list(query);
//		int total = tMajorService.count(query);
//		PageUtils pageUtils = new PageUtils(tMajorList, total);
		return tMajorList;
	}
	
	@GetMapping("/add/{pId}")
	@RequiresPermissions("robot:tMajor:add")
	String add(@PathVariable("pId") Long pId, Model model){
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "专业总栏目");
		} else {
			model.addAttribute("pName", tMajorService.get(pId).getName());
		}
	    return "robot/tMajor/tMajorAdd";
	}

	@GetMapping("/edit/{majorId}")
	@RequiresPermissions("robot:tMajor:edit")
	String edit(@PathVariable("majorId") Long majorId,Model model){
		TMajorDO tMajor = tMajorService.get(majorId);
		model.addAttribute("tMajor", tMajor);
		if(Constant.DEPT_ROOT_ID.equals(tMajor.getParent())) {
			model.addAttribute("parentDeptName", "无");
		}else {
			TMajorDO parTMajor = tMajorService.get(tMajor.getParent());
			model.addAttribute("parentDeptName", parTMajor.getName());
		}
	    return "robot/tMajor/tMajorEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tMajor:add")
	public R save( TMajorDO tMajor){
		//Long userId = ShiroUtils.getUser().getUserId();		//当前系统管理员
		String userName = ShiroUtils.getUser().getUsername();   //创建用户存入userName不是userId
		tMajor.setCreater(userName);		//发布人
		tMajor.setCreatetime(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", tMajor.getName());
		if(tMajorService.count(map)>0){  //保证专业名称唯一
			return R.error(1, "专业名称已存在！");
		}
		if(tMajorService.save(tMajor)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tMajor:edit")
	public R update( TMajorDO tMajor){
		tMajorService.update(tMajor);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tMajor:remove")
	public R remove( Long majorId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parent", majorId);
		if(tMajorService.count(map)>0) {
			return R.error(1, "包含下级菜单,不允许修改");
		}
		if (tMajorService.remove(majorId) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tMajor:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] majorIds){
		tMajorService.batchRemove(majorIds);
		return R.ok();
	}
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<TMajorDO> tree() {
		Tree<TMajorDO> tree = new Tree<TMajorDO>();
		tree = tMajorService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  "robot/tMajor/tMajorTree";
	}

}
