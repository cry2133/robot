package com.robot.robot.controller.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.common.config.RobotConfig;
import com.robot.common.utils.RequestUtil;
import com.robot.common.utils.ftp.FtpUtil;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TIdentityInfoDO;
import com.robot.robot.domain.TPrinterSettingDO;
import com.robot.robot.service.TIdentityInfoService;
import com.robot.robot.service.TPrinterSettingService;

@Controller
@RequestMapping("/app/printer")
public class PrinterForAppController {

	public static Logger logger = Logger.getLogger(IdentityInfoForAppController.class); 
	
	private static final String FTP_URL = "10.130.242.66";
	private static final int FTP_PORT = 2121;
	private static final String FTP_USERNAME = "robot";
	private static final String FTP_PASSWORD = "robot";
	private static final String FTP_REMOTEADR = "model";
	private static final String RETURN_FTP_URL = "ftp://10.130.242.66:2121/model/";
	
	@Autowired
	private RobotConfig robotConfig;
	
	@Autowired
	private TPrinterSettingService tPrinterSettingService;
	
	@Autowired
	private TIdentityInfoService tIdentityInfoService;
	
	FtpUtil ftpUtil=new FtpUtil();
	
	/**
	 * 打印功能
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/printer")
	@ResponseBody
	public ResponseBean printer(HttpServletRequest request) throws Exception{
		List<TPrinterSettingDO> list=tPrinterSettingService.list(null);
		if(list.size()<=0){
			logger.error("后台没有设置打印机");
			return ResponseBean.fail("后台没有设置打印机");
		}
		TPrinterSettingDO tPrinterSet=list.get(0);
		
		FileInputStream psStream = null;
		try {
			psStream = new FileInputStream("C:\\Users\\Public\\Pictures\\NVIDIA Corporation\\timg (4).jpg");
		} catch (FileNotFoundException ffne) {
			ffne.printStackTrace();
		}
		if (psStream == null) {
			logger.error("找不到附件");
			return ResponseBean.fail("找不到附件");
		}
		//设置打印数据的格式，此处为图片jpg格式
		DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
		//创建打印数据
//		DocAttributeSet docAttr = new HashDocAttributeSet();//设置文档属性
//		Doc myDoc = new SimpleDoc(psStream, psInFormat, docAttr);
		Doc myDoc = new SimpleDoc(psStream, psInFormat, null);
		
		//设置打印属性
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new Copies(1));//打印份数，1份
		
		//查找所有打印服务
		PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);

		//将所有查找出来的打印机与自己想要的打印机进行匹配，找出自己想要的打印机
		PrintService myPrinter = null;
		for (int i = 0; i < services.length; i++) {
			logger.info("service found: " + services[i]);
			String svcName = services[i].toString();
			if (svcName.contains(tPrinterSet.getPrinterName())) {		//查出所有打印机和后台设置的打印机进行对比
				myPrinter = services[i];
				logger.info("my printer found svcName : " + svcName);
				logger.info("my printer found myPrinter : " + myPrinter);
				break;
			}
		}

		if (myPrinter == null) {
			logger.error("no printer services found");
			return ResponseBean.fail("找不到打印机");
		}
		
		//可以输出打印机的各项属性
		AttributeSet att = myPrinter.getAttributes();

		for (Attribute a : att.toArray()) {

			String attributeName;
			String attributeValue;

			attributeName = a.getName();
			attributeValue = att.get(a.getClass()).toString();

			logger.info(attributeName + " : " + attributeValue);
		}

		DocPrintJob job = myPrinter.createPrintJob();//创建文档打印作业
		try {
			job.print(myDoc, aset);//打印文档

		} catch (Exception pe) {
			logger.error("打印失败 e:"+pe);
			pe.printStackTrace();
		}
		
		return ResponseBean.success();
	}
	
	/**
	 * 返回所有pdf文件名
	* @param request
	* @History  v 1.0
	 */
	@RequestMapping("/getAllFileNames")
	@ResponseBody
	public ResponseBean getAllFileNames(HttpServletRequest request) throws Exception{
		String identityID=RequestUtil.getString(request, "identityID");
		TIdentityInfoDO identityInfo = tIdentityInfoService.selectByIdentityID(identityID);
		
		String path = identityInfo.getName();
		if(path.isEmpty()){
			return  ResponseBean.success("找不到该用户信息！");
		}
		
		// 获得指定文件对象  
        File file = new File(robotConfig.getUploadPath()+"model/"+path);   
        // 获得该文件夹内的所有文件   
        File[] array = file.listFiles();   

		Map<String,Object> map = new HashMap<String,Object>();
		
		if(array.length==0){
			return  ResponseBean.success("无打印发票！");
		}else{
			for (int i=0; i<array.length; i++){
			   if(array[i].isFile())//如果是文件
	            {   
				   String fileName = array[i].getName();   
				   map.put("url", "/files/model/"+path+"/"+fileName);
	            }
				String date = "2018-0"+(i+1);
				String accounts = "2"+i+"5.05";
				
				map.put("date", date);
				map.put("accounts", accounts);
			}
			return ResponseBean.success(map);
		}
		
	}
	
	
	/**
	 * 返回需要打印的pdf
	* @param request
	* @History  v 1.0
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseBean getPrintModelURL(HttpServletRequest request) throws Exception{
		String identityID=RequestUtil.getString(request, "identityID");
		TIdentityInfoDO identityInfo = tIdentityInfoService.selectByIdentityID(identityID);
		String path = identityInfo.getName();
		
		String fileName=RequestUtil.getString(request, "fileName");
		return ResponseBean.success("/files/model/"+path+"/"+fileName);
		//ftpUtil.connectServer(FTP_URL, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_REMOTEADR);

		//下载到本地后返回地址供机器人打印
		//boolean down = ftpUtil.download(fileName, "d:" + File.separator + fileName);
		/*
		if(!down){
			return  ResponseBean.success("文件不存在！");
		}else{
			return ResponseBean.success(RETURN_FTP_URL+fileName);
		}
		*/
	}
	
}
