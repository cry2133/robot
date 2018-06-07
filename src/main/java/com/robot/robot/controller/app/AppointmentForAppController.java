package com.robot.robot.controller.app;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.robot.robot.bean.UserModel;
import com.robot.robot.common.UrlManagement;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TAppointmentDO;
import com.robot.robot.domain.TIdentityInfoDO;
import com.robot.robot.service.TAppointmentService;
import com.robot.robot.service.TIdentityInfoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        List<TIdentityInfoDO> userList = null;
        try {
            userList =  tIdentityInfoService.selectByIdentityID(identityID);
            if(userList.isEmpty())
            {
                return ResponseBean.success("请先进行实名认证！");
            }
        }catch (Exception e) {
            logger.error("verifyID异常", e);
        }
        return ResponseBean.success(userList);
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
    public ResponseBean appointment(String identityID,String appointmentTime,String type) {
        List<TAppointmentDO> appointmentList=null;
        try {
        	TAppointmentDO data=new TAppointmentDO();
//            if(StringUtils.isNotEmpty(appointmentTime)){
//            	Date at=new Date(appointmentTime);
//            	data.setAppointmenttime(at);
//            }
        	data.setIdentityID(identityID);
        	//appointmentTime=2017-08-30 15:00-16:00
            data.setAppointmentTime(appointmentTime);
            data.setType(type);
            appointmentList=tAppointmentService.selectByAppointmentTime(data.getAppointmentTime());
            if (appointmentList.size()<2)
            {
            	tAppointmentService.save(data);
                return ResponseBean.success("恭喜您，预约成功！");
            }
            else {
                return ResponseBean.success("预约失败，当前时段预约人数已满<br>请预约其它时段！");
            }
        }catch (Exception e) {
            logger.error("appointment异常", e);
        }
        return ResponseBean.success();
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
	    public ResponseBean searchAppointmentInfo(@RequestBody UserModel userModel) {
	        List<TAppointmentDO> appointmentList = null;
	        try {
	            appointmentList = tAppointmentService.selectByIdentityID(userModel.getIdentityID());
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
	    public ResponseBean appointmentDelete(String identityID,String appointmentTime,String type) {
	        try{
	        	TAppointmentDO appointment = new TAppointmentDO();
	            appointment.setIdentityID(identityID);
	            appointment.setAppointmentTime(appointmentTime);
	            appointment.setType(type);
	            if (appointment.getIdentityID().equals(identityID) && appointment.getAppointmentTime().equals(appointmentTime) && appointment.getType().equals(type)) {
	            	tAppointmentService.deleteAppointmentTime(appointment.getIdentityID(), appointment.getAppointmentTime(), appointment.getType());
	                return ResponseBean.success();
	            }
	    }catch (Exception e){
	        logger.error("appointmentDelete异常",e);
	        }
	        return ResponseBean.fail();
	    }
}
