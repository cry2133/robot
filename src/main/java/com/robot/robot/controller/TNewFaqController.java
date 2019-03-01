package com.robot.robot.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.robot.common.annotation.Log;
import com.robot.common.utils.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.domain.TNewFaqDO;
import com.robot.robot.service.TNewFaqService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 新问答表
 * 
 * @author laoGF
 * @date 2018-08-09 19:03:40
 */
@Controller
@RequestMapping("/robot/tNewFaq")
public class TNewFaqController {
	@Resource
	private TNewFaqService tNewFaqService;

	private Query queryFilterList = null;

	@GetMapping()
	@RequiresPermissions("robot:tNewFaq:tNewFaq")
	String TNewFaq(){
	    return "robot/tNewFaq/tNewFaq";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tNewFaq:tNewFaq")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		queryFilterList = query;
		Long userId = ShiroUtils.getUserId();   //获取当前用户
		query.put("userId",userId);
		String question = (String)query.get("question");
		if(StringUtils.isNotEmpty(question)){
			query.put("question", "%" + question + "%");
			query.put("questions", "%" + question + "%");
		}

		List<TNewFaqDO> tNewFaqList = tNewFaqService.list(query);
		int total = tNewFaqService.count(query);
		return new PageUtils(tNewFaqList, total);
	}
	
	@GetMapping("/add")
	String add(){
		return "robot/tNewFaq/tNewFaqAdd";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		TNewFaqDO tNewFaq = tNewFaqService.get(id);
		model.addAttribute("tNewFaq", tNewFaq);
	    return "robot/tNewFaq/tNewFaqEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TNewFaqDO tNewFaq){
		List<String> list = tNewFaqService.verify(tNewFaq);
		if(list.size() > 0){
			Map<String, Object> map = new HashMap<>();
			map.put("code", 200);
			map.put("msg", list.toString());
			return R.ok(map);
		}
		if(tNewFaqService.save(tNewFaq)>0){
			return R.ok();
		}
		return R.error();
	}


	/**
	 * 强制保存
	 */
	@ResponseBody
	@PostMapping("/forceSave")
	public R forceSave( TNewFaqDO tNewFaq){
		if(tNewFaqService.save(tNewFaq)>0){
			return R.ok();
		}
		return R.error();
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TNewFaqDO tNewFaq){
		List<String> list = tNewFaqService.verify(tNewFaq);
		if(list.size() > 0){
			Map<String, Object> map = new HashMap<>();
			map.put("code", 200);
			map.put("msg", list.toString());
			return R.ok(map);
		}
		if(tNewFaqService.update(tNewFaq)>0){
			return R.ok();
		}
		return R.error();
	}


	/**
	 * 强制修改
	 */
	@ResponseBody
	@RequestMapping("/forceUpdate")
	public R forceUpdate( TNewFaqDO tNewFaq){
		if(tNewFaqService.update(tNewFaq)>0){
			return R.ok();
		}
		return R.error();
	}


	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(tNewFaqService.remove(id)>0){
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
		tNewFaqService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 模型训练
	 */
	@PostMapping( "/trainOrLoadModel")
	@ResponseBody
	public R trainOrLoadModel(@RequestParam("train_file_name") String train_file_name,
							  @RequestParam("model_file_name") String model_file_name){
		try{
			Word2VecTrain.trainOrLoadModel(train_file_name,model_file_name);
		}catch (IOException e){
			e.printStackTrace();
			return R.error("训练失败！请确认路径是否正确！");
		}
		return R.ok("训练成功！");
	}



	@RequestMapping({ "expFaqList" })
	@Log("导出知识库问题信息excel")
	public void expFaqList(HttpServletRequest request,
						   HttpServletResponse response) {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("知识库问题信息");
		sheet.setDefaultColumnWidth(15);

		HSSFCellStyle titleStyle = wb.createCellStyle();
		HSSFFont titleFont = wb.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFRow row = sheet.createRow(0);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(titleStyle);

		cell = row.createCell(1);
		cell.setCellValue("知识库名称");
		cell.setCellStyle(titleStyle);

		cell = row.createCell(2);
		cell.setCellValue("专业名称");
		cell.setCellStyle(titleStyle);

		cell = row.createCell(3);
		cell.setCellValue("代表问题");
		cell.setCellStyle(titleStyle);

		cell = row.createCell(4);
		cell.setCellValue("问题集");
		cell.setCellStyle(titleStyle);

		cell = row.createCell(5);
		cell.setCellValue("答案");
		cell.setCellStyle(titleStyle);


		try {
			queryFilterList.remove("offset");
			queryFilterList.remove("limit");
			Long userId = ShiroUtils.getUserId();   //获取当前用户
			queryFilterList.put("userId",userId);
			List<TNewFaqDO> tFaqList = tNewFaqService.list(queryFilterList);
			TNewFaqDO tFaqDO = null;
			for (int i = 0; i < tFaqList.size(); i++) {
				tFaqDO = tFaqList.get(i);
				row = sheet.createRow(i + 1);

				cell = row.createCell(0);
				cell.setCellValue(tFaqDO.getId());
				cell.setCellStyle(style);

				cell = row.createCell(1);
				cell.setCellValue(tFaqDO.getRepositoryName());
				cell.setCellStyle(style);

				cell = row.createCell(2);
				cell.setCellValue(tFaqDO.getMajorName());
				cell.setCellStyle(style);

				cell = row.createCell(3);
				cell.setCellValue(tFaqDO.getQuestion());
				cell.setCellStyle(style);

				cell = row.createCell(4);
				cell.setCellValue(tFaqDO.getQuestions());
				cell.setCellStyle(style);

				cell = row.createCell(5);
				cell.setCellValue(tFaqDO.getAnswer());
				cell.setCellStyle(style);

			}
			response.setContentType("application/vnd.ms-excel");
			if(request.getHeader("user-agent").indexOf("Firefox") > -1){ //火狐编码
				String fileName = "知识库问题信息.xls";
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			}else{
				response.setHeader("Content-disposition", "attachment;filename="
						+ java.net.URLEncoder.encode("知识库问题信息.xls", "UTF-8"));
			}
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
