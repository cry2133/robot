package com.robot.robot.controller.app;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.robot.robot.common.UrlManagement;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TAppointmentDO;
import com.robot.robot.domain.TIdentityInfoDO;
import com.robot.robot.service.TAppointmentService;
import com.robot.robot.service.TIdentityInfoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * 
 * 预约办理业务接口
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年6月6日
 */
@Controller
@RequestMapping("/app/authentication")
public class AppointmentForAppController {
	public static Logger logger = Logger.getLogger(AppointmentForAppController.class); 

	@Autowired
	private TIdentityInfoService tIdentityInfoService;
	
	
	@Autowired
	private TAppointmentService tAppointmentService;

	 @GetMapping("verify")
	String verify(Model model){
		model.addAttribute("actionURL", UrlManagement.uploadPath); 
	    return "robot/tAppointment/verify";
	}
	 
	 /**
	  * 此接口给H5网页使用
	 * @Functionlity  返回用户信息接口
	 * @Date  2018年6月7日
	 * @param identityID
	 * @return ResponseBean
	 * @History  v 1.0
	  */
    @RequestMapping(value = "/verifyID",method= RequestMethod.GET)
    @ResponseBody
    public ResponseBean verifyID(String identityID) {
        TIdentityInfoDO user = null;
        try {
            user =  tIdentityInfoService.selectByIdentityID(identityID);
            if(user.getName().isEmpty())
            {
                return ResponseBean.success("请先进行实名认证！");
            }
        }catch (Exception e) {
            logger.error("verifyID异常", e);
        }
        return ResponseBean.success(user);
    }
	 
    /**
     * 
    * @Functionlity  预约线下业务接口
    * @Date  2018年6月7日
    * @param identityID
    * @param appointmentTime
    * @param type
    * @return ResponseBean
    * @History  v 1.0
     */
	@RequestMapping(value = "/appointment",method= RequestMethod.GET)
	@ResponseBody
    public ResponseBean appointment(String identityID,String appointmentTime,int taxID) {
        try {
        	TAppointmentDO data=new TAppointmentDO();
        	Map<String, Object> map=new HashMap<String, Object>();
        	data.setIdentityID(identityID);
        	//appointmentTime=2017-08-30 15:00-16:00
            data.setAppointmentTime(appointmentTime);
            data.setTaxID(taxID);
            
            map.put("identityID", identityID);
            map.put("taxID", taxID);
            //先查用户是否已经预约了
            int getCount = tAppointmentService.list(map).size();
            if(getCount>0){
            	return ResponseBean.fail("对不起！你已预约了其他业务！");
            }
            
            map.put("appointmentTime", appointmentTime);
            map.remove("identityID");
            map.remove("taxID");
            //再查询该时间是否有多人预约
            int count = tAppointmentService.list(map).size();
            switch(count){
            case 0:
            	tAppointmentService.save(data);
                return ResponseBean.success("恭喜您，预约成功！");
			case 1:
				tAppointmentService.save(data);
                return ResponseBean.success("恭喜您，预约成功！");
            case 2:
                return ResponseBean.success("预约失败！当前时段预约人数已满<br>请预约其它时段！");
            default:
                return ResponseBean.fail("对不起！预约失败！");
            }
        }catch (Exception e) {
            logger.error("appointment异常", e);
        }
        return ResponseBean.fail("对不起！预约失败！");
    }
	
	/**
	 * 
	* @Functionlity  查询预约信息接口
	* @Date  2018年6月7日
	* @param userModel
	* @return ResponseBean
	* @History  v 1.0
	 */
	 @RequestMapping(value = "/searchAppointmentInfo", method = POST)
	 @ResponseBody
	    public ResponseBean searchAppointmentInfo(String identityID) {
	        try {
	        	Map<String, Object> map=new HashMap<String, Object>();
	        	 List<TAppointmentDO> appointmentList = null;
	        	map.put("identityID", identityID);
	            appointmentList = tAppointmentService.list(map);
	            if (appointmentList.isEmpty()) {
	                return ResponseBean.fail("没有相关用户预约信息！");
	            }
	            return ResponseBean.success(appointmentList.toArray());
	        }catch (Exception e) {
	            logger.error("search error", e);
	        }
	        return ResponseBean.fail();
	    }
	 
	 /**
	  * 
	 * @Functionlity  预约删除接口
	 * @Date  2018年6月7日
	 * @param identityID
	 * @param appointmentTime
	 * @param type
	 * @return ResponseBean
	 * @History  v 1.0
	  */
	 @RequestMapping(value = "/appointmentDelete",method= RequestMethod.GET)
	 @ResponseBody
	    public ResponseBean appointmentDelete(String identityID,String appointmentTime,int taxID) {
	        try{
	        	Map<String, Object> map = new HashMap<String, Object>();
	        	map.put("identityID", identityID);
	        	//map.put("appointmentTime", appointmentTime);
	        	map.put("taxID", taxID);
	            int del = tAppointmentService.deleteAppointmentTime(map);
	            if(del==1){
	            	 return ResponseBean.success();
	            }else{
	            	 return ResponseBean.fail();
	            }
	    }catch (Exception e){
	        logger.error("删除预约异常",e);
	        }
	        return ResponseBean.fail();
	    }
}
