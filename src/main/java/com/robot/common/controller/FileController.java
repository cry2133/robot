package com.robot.common.controller;

import com.robot.common.config.RobotConfig;
import com.robot.common.domain.FileDO;
import com.robot.common.service.FileService;
import com.robot.common.utils.*;
import com.robot.robot.common.UrlManagement;
import com.robot.robot.domain.TAppSoftwareDO;
import com.robot.robot.service.TAppSoftwareService;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

	@Autowired
	private FileService sysFileService;
	
	@Autowired
	private TAppSoftwareService tAppSoftwareService;

	@Autowired
	private RobotConfig robotConfig;

	@GetMapping()
	@RequiresPermissions("common:sysFile:sysFile")
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "common/file/file";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<FileDO> sysFileList = sysFileService.list(query);
		int total = sysFileService.count(query);
		PageUtils pageUtils = new PageUtils(sysFileList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	// @RequiresPermissions("common:bComments")
	String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	// @RequiresPermissions("common:bComments")
	String edit(Long id, Model model) {
		FileDO sysFile = sysFileService.get(id);
		model.addAttribute("sysFile", sysFile);
		return "common/sysFile/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("common:info")
	public R info(@PathVariable("id") Long id) {
		FileDO sysFile = sysFileService.get(id);
		return R.ok().put("sysFile", sysFile);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:save")
	public R save(FileDO sysFile) {
		if (sysFileService.save(sysFile) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("common:update")
	public R update(@RequestBody FileDO sysFile) {
		sysFileService.update(sysFile);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	// @RequiresPermissions("common:remove")
	public R remove(Long id, HttpServletRequest request, TAppSoftwareDO tAppSoftware) {
		String fileName = robotConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm"); 
		String date=sdf.format(sysFileService.get(id).getCreateDate());
		
		if (sysFileService.remove(id) > 0) {
			/**
			 * 删除apk数据库记录
			 */
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("submitTime", date);
			List<TAppSoftwareDO> list = tAppSoftwareService.list(map);
			for(TAppSoftwareDO ts:list){
				if(tAppSoftwareService.remove(ts.getId())==0){
					return R.error("数据库apk记录删除失败！");
				}
			}
			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return R.error("数据库记录删除成功，文件删除失败！");
			}
			return R.ok();
		} else {
			return R.error();
		}
	}

	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:remove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		sysFileService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 
	* @Functionlity  上传文件
	* @Date  2018年5月17日  上午9:26:12
	* @param file
	* @param request
	* @return R
	* @History  v 1.0
	 */
	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String username= ShiroUtils.getUser().getUsername();		//当前系统管理员
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(new Date());
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/"+username+"/"+date+"/"+fileName, new Date(),file.getOriginalFilename());
		try {
			FileUtil.uploadFile(file.getBytes(), robotConfig.getUploadPath()+username+"/"+date+"/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
	/**
	 * 
	* @Functionlity  填写APK信息
	* @Date  2018年5月17日  下午2:33:33
	* @return String
	* @History  v 1.0
	 */
	@GetMapping("/addAPK")
	String addAPK(){
	    return "common/file/addAPK";
	}
	
	/**
	 * 
	* @Functionlity  上传APK
	* @Date  2018年5月17日  上午9:25:50
	* @param file
	* @param request
	* @return R
	* @History  v 1.0
	 */
	@RequestMapping(value = "/uploadAPK", method = RequestMethod.POST)
	@ResponseBody
	String uploadAPK(@RequestParam("file") MultipartFile file,HttpServletRequest request, TAppSoftwareDO tAppSoftware) {
		 
		String fileName = file.getOriginalFilename();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm"); 
		Date newDate = new Date();
		String date=sdf.format(newDate);
		
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/"+date+"/"+fileName, newDate,file.getOriginalFilename());
		sysFileService.save(sysFile);
		
		/*
		File savePath = new File(request.getSession().getServletContext().getRealPath("/software"));  
        if (!savePath.exists()) {  
            savePath.mkdirs();  
        }
        String softwarePath = savePath.toString()+"/";
        */
		try {
	    	 FileUtil.uploadFile(file.getBytes(), robotConfig.getUploadPath()+date+"/", fileName);
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
         
	    String version = request.getParameter("version");
	    String type = request.getParameter("type");
	    String robotNo = request.getParameter("robotNo");
	    String description = request.getParameter("description");
	    
		tAppSoftware.setVersion(version);
		tAppSoftware.setType(type);
		tAppSoftware.setDescription(description);
		tAppSoftware.setPath("/files/"+date+"/"+fileName);
		tAppSoftware.setUserId(null);
		tAppSoftware.setRobotNo(robotNo);
		tAppSoftware.setSubmitTime(date);
		tAppSoftwareService.save(tAppSoftware);
		
		return "上传成功！请退出页面！";
	}
	
	
}
