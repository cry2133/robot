package com.robot.robot.controller.app;



import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.robot.robot.bean.DeliveryModel;
import com.robot.robot.bean.MessageModel;
import com.robot.robot.common.Constants;
import com.robot.robot.common.PushTypeEnum;
import com.robot.robot.common.UrlManagement;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.utils.JpushUtils;
import com.robot.robot.utils.RSA;

import java.io.*;

/**
 * 
 * 发票上传下载获取地址 (接口暂时废弃不用)
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年5月17日
 */
@Controller
@RequestMapping("/app/ticket")
public class TicketForAppController {
	public static Logger logger = Logger.getLogger(TicketForAppController.class); 
	
	@RequestMapping("/fileUpload")
    public String fileUpload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file, @RequestParam("robotid")String robotid) throws IOException {
        if(file.getOriginalFilename()=="")
        {
            return "上传文件失败!";
        }
        String fileType = file.getContentType();
        if(file.getContentType().equals("application/pdf")) {
            fileType =".pdf";
        }
        else if(file.getContentType().equals("image/png")){
            fileType =".png";
        }
        else if(file.getContentType().equals("image/jpeg")){
            fileType =".jpeg";
        }
        else if(file.getContentType().equals("image/jpg")){
            fileType =".jpg";
        }


        File targetFile = new File(UrlManagement.savaPath, robotid + fileType);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long  endTime=System.currentTimeMillis();
        sendMessageNotifyRobot(robotid,UrlManagement.downloadPath+robotid+fileType);
        return "上传文件成功!";
    }
    
    @GetMapping("getUploadAddress")
	String TFaq(Model model,String robotid){
		model.addAttribute("robotid", robotid); 
		model.addAttribute("actionURL", UrlManagement.uploadPath); 
	    return "robot/ticket/upload";
	}

    @RequestMapping("/getDownloadAddress")
    @ResponseBody
    public ResponseBean getDownloadAddress(String robotid) {
        return ResponseBean.success(UrlManagement.downloadPath+robotid+".pdf");
    }

    // 推送消息给机器人
    private void sendMessageNotifyRobot(String robotNo,String downloadUrl) {
        try {
            DeliveryModel deliveryModel = new DeliveryModel();
            deliveryModel.setDownloadUrl(downloadUrl);
            // 推送的数据
            String data = JSON.toJSONString(deliveryModel);
            // 数据签名
            String sign = RSA.sign(Constants.PRIVATE_KEY, data);
            // 推送出货消息
            JpushUtils.sendPush(PushTypeEnum.TICKETADD, new MessageModel(data, sign).toString(), robotNo);
            System.out.println("sendMessageNotifyRobot is ok!");
        } catch (Exception e) {
            // 禁止抛出异常, 以保证业务的处理结果正常返回给第三方支付
            logger.error("消息推送异常", e);
        }
    }
}
