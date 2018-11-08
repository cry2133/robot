package com.robot.robot.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.robot.robot.domain.TFaqDO;
import com.robot.robot.domain.TKeywordMiddleDO;
import com.robot.robot.service.TFaqService;
import com.robot.robot.service.TKeywordMiddleService;

import net.sf.json.JSONArray;

import com.robot.common.annotation.Log;
import com.robot.common.exception.ImportFailException;
import com.robot.common.utils.ExcelReader;
import com.robot.common.utils.PageUtils;
import com.robot.common.utils.Query;
import com.robot.common.utils.R;
import com.robot.common.utils.ShiroUtils;

/**
 * 问答表
 */
@Controller
@RequestMapping("/robot/tFaq")
@Transactional
public class TFaqController {
	public static Logger log = Logger.getLogger(TFaqController.class); 
	
	@Autowired
	private TFaqService tFaqService;
	@Autowired
	private TKeywordMiddleService tKeywordMiddleService;
	
	private Query queryFilterList = null;
	
	@GetMapping()
	@RequiresPermissions("robot:tFaq:tFaq")
	String TFaq(Model model,String repositoryId,String majorId,String type){
		model.addAttribute("repositoryId", repositoryId); 
		model.addAttribute("majorId", majorId); 
		model.addAttribute("type", type); 
	    return "robot/tFaq/tFaq";
	}
	
	@GetMapping("/tFaqTest")
	String tFaqTest(Model model,String repositoryId,String type){
	    return "robot/tFaq/tFaqTest";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("robot:tFaq:tFaq")
	public PageUtils list(@RequestParam Map<String, Object> params){
		Long userId = ShiroUtils.getUserId();   //获取当前用户
		//查询列表数据
        Query query = new Query(params);
        query.put("userId",userId);
        queryFilterList = query;
		List<TFaqDO> tFaqList = tFaqService.list(query);
		int total = tFaqService.count(query);
		return new PageUtils(tFaqList, total);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("robot:tFaq:add")
	String add(){
	    return "robot/tFaq/tFaqAdd";
	}
	

	@GetMapping("/edit/{faqId}")
	@RequiresPermissions("robot:tFaq:edit")
	String edit(@PathVariable("faqId") Long faqId,Model model){
		TFaqDO tFaq = tFaqService.get(faqId);
		model.addAttribute("tFaq", tFaq); 
	    return "robot/tFaq/tFaqEdit";
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("robot:tFaq:add")
	public R save( TFaqDO tFaq){
		Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
		tFaq.setCreater(String.valueOf(userId));		//发布人
		tFaq.setCreatetime(new Date());
		tFaq.setAmount(0);
		
		boolean flag=tKeywordMiddleService.existKeygroup(tFaq.getKeygroup(),0L);
		if(flag){
			return R.error("关键字组合已存在");
		}
		
		if(tFaqService.save(tFaq)>0){
			TKeywordMiddleDO keywordMiddle=new TKeywordMiddleDO();
			keywordMiddle.setFaqId(tFaq.getFaqId());
			keywordMiddle.setKeygroup(tFaq.getKeygroup());
			keywordMiddle.setKeygroupName(tFaq.getKeygroupName());
			if(tKeywordMiddleService.save(keywordMiddle)>0){
				return R.ok();
			}else{
				return R.error();
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("robot:tFaq:edit")
	public R update( TFaqDO tFaq){
		boolean flag=tKeywordMiddleService.existKeygroup(tFaq.getKeygroup(),tFaq.getFaqId());
		if(flag){
			return R.error("关键字组合已存在");
		}
		
		
		TKeywordMiddleDO keywordMiddle=tKeywordMiddleService.getByFaqId(tFaq.getFaqId());
		keywordMiddle.setKeygroup(tFaq.getKeygroup());
		keywordMiddle.setKeygroupName(tFaq.getKeygroupName());
		tKeywordMiddleService.update(keywordMiddle);
		
		tFaqService.update(tFaq);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("robot:tFaq:remove")
	public R remove( Long faqId){
		if(tFaqService.remove(faqId)>0){
			if(tKeywordMiddleService.removeByFaqId(faqId)>0){
				return R.ok();
			}else{
				return R.error();
			}
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("robot:tFaq:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] faqIds){
		tKeywordMiddleService.batchRemoveByFaqIds(faqIds);
		tFaqService.batchRemove(faqIds);
		return R.ok();
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
		cell.setCellValue("问答编号");
		cell.setCellStyle(titleStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("知识库名称");
		cell.setCellStyle(titleStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("专业名称");
		cell.setCellStyle(titleStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("问题");
		cell.setCellStyle(titleStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("答案");
		cell.setCellStyle(titleStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("关键字组合");
		cell.setCellStyle(titleStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("创建时间");
		cell.setCellStyle(titleStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("回答次数");
		cell.setCellStyle(titleStyle);
		

		try {
			queryFilterList.remove("offset");
			queryFilterList.remove("limit");
			List<TFaqDO> tFaqList = tFaqService.list(queryFilterList);
			TFaqDO tFaqDO = null;
			for (int i = 0; i < tFaqList.size(); i++) {
				tFaqDO = tFaqList.get(i);
				row = sheet.createRow(i + 1);
				
				cell = row.createCell(0);
				cell.setCellValue(tFaqDO.getFaqId());
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
				cell.setCellValue(tFaqDO.getAnswer());
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(tFaqDO.getKeygroupName());
				cell.setCellStyle(style);
				
				SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cell = row.createCell(6);
				cell.setCellValue(myFmt2.format(tFaqDO.getCreatetime()));
				cell.setCellStyle(style);
				
				cell = row.createCell(7);
				cell.setCellValue(tFaqDO.getAmount());
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
	
	@RequestMapping(value="importFaqsExcelSources")
	@Log("导入知识库问答数据")
    @ResponseBody
    public R importFaqsExcelSources(MultipartHttpServletRequest request, HttpServletResponse response){
        Map<String, MultipartFile> files = request.getFileMap();
        Iterator<MultipartFile> it = files.values().iterator();
        MultipartFile file = (MultipartFile) it.next();
        ExcelReader excelReader = new ExcelReader();
        List<Integer> failList=new ArrayList<Integer>();
        int existcount =0;
        int count = 0;
        try {
            Map<Integer, String> contentMap = excelReader.readExcelMarkContent(file.getInputStream(),0);
            count = contentMap.size();
            for(int i =0;i<count;i++){
            	 String content = contentMap.get(i+1);
            	 try {
            		 boolean isExist=this.tFaqService.importFaqsExcelSources(content);
            		 if(isExist){
            			 existcount++;
            		 }
				} catch (ImportFailException e) {
					failList.add(i+2);		//增加第一行菜单
				}
            }
           
        } catch (Exception ex) {
            log.error("导入项目失败：失败原因：",ex);
            return R.newError("系统异常，请重试");
        }
        JSONArray json=JSONArray.fromObject(failList);
        log.info("导入完成,本次导入数据:"+count+",本次成功导入数据"+((count-failList.size())-existcount)+"条,数据已存在"+existcount+"条,导入失败的数据行数"+json.toString() );
        return R.ok("导入完成,本次成功导入数据"+((count-failList.size())-existcount)+"条,数据已存在"+existcount+"条,导入失败的数据行数"+json.toString() );
    }
	
	/**
	 * 跳转到项目导入页面
	 */
	@GetMapping("/toImport")
    public String toImport() {  
		 return "robot/tFaq/tFaqImportExcel";
    }
}
