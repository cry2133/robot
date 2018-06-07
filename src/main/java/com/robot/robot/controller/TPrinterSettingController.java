package com.robot.robot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

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

import com.robot.robot.bean.PrintBean;
import com.robot.robot.domain.TPrinterSettingDO;
import com.robot.robot.service.TPrinterSettingService;
import com.robot.system.domain.UserDO;
import com.robot.system.service.UserService;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;
import com.robot.common.utils.ShiroUtils;

/**
 * 打印机设置表
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-26 09:55:51
 */
 
@Controller
@RequestMapping("/robot/tPrinterSetting")
public class TPrinterSettingController {
	@Autowired
	private TPrinterSettingService tPrinterSettingService;
	@Autowired
	UserService userService;
	
	@GetMapping()
	@RequiresPermissions("robot:tPrinterSetting:tPrinterSetting")
	String TPrinterSetting(){
	    return "robot/tPrinterSetting/tPrinterSetting";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tPrinterSetting:tPrinterSetting")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
//        Query query = new Query(params);
        Map<String, Object> query = new HashMap<>(0);
		List<TPrinterSettingDO> tPrinterSettingList = tPrinterSettingService.list(query);
		for(TPrinterSettingDO tPrinterSetting:tPrinterSettingList){
			UserDO userDO = userService.get(Long.valueOf(tPrinterSetting.getCreater()));
			tPrinterSetting.setCreateName(userDO.getName());
		}
		
		int total = tPrinterSettingService.count(query);
		PageUtils pageUtils = new PageUtils(tPrinterSettingList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tPrinterSetting:add")
	String add(){
	    return "robot/tPrinterSetting/tPrinterSettingAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("robot:tPrinterSetting:edit")
	String edit(@PathVariable("id") Long id,Model model){
		//查找所有打印服务
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
		PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
		List<PrintBean> printers = new ArrayList<PrintBean>();
		for(int i=0;i<printService.length;i++){
			PrintBean bean =new PrintBean();
			bean.setPrintName(printService[i].getName());
			printers.add(bean);
		}
		model.addAttribute("printers", printers);
		
		
		TPrinterSettingDO tPrinterSetting = tPrinterSettingService.get(id);
		model.addAttribute("tPrinterSetting", tPrinterSetting);
	    return "robot/tPrinterSetting/tPrinterSettingEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tPrinterSetting:add")
	public R save( TPrinterSettingDO tPrinterSetting){
		Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
		tPrinterSetting.setCreater(String.valueOf(userId));		//发布人
		tPrinterSetting.setUpdatetime(new Date());
		if(tPrinterSettingService.save(tPrinterSetting)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tPrinterSetting:edit")
	public R update( TPrinterSettingDO tPrinterSetting){
		Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
		tPrinterSetting.setCreater(String.valueOf(userId));		//发布人
		tPrinterSetting.setUpdatetime(new Date());
		tPrinterSettingService.update(tPrinterSetting);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tPrinterSetting:remove")
	public R remove( Long id){
		if(tPrinterSettingService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tPrinterSetting:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		tPrinterSettingService.batchRemove(ids);
		return R.ok();
	}
	
}
