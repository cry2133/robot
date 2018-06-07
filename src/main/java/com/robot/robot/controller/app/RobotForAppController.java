package com.robot.robot.controller.app;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.robot.robot.bean.DeliveryModel;
import com.robot.robot.bean.MessageModel;
import com.robot.robot.bean.TicketModel;
import com.robot.robot.common.Constants;
import com.robot.robot.common.PushTypeEnum;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TRobotDO;
import com.robot.robot.service.TRobotService;
import com.robot.robot.utils.JpushUtils;
import com.robot.robot.utils.MD5;
import com.robot.robot.utils.RSA;
import com.robot.robot.utils.TicketManager;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/app/robot")
public class RobotForAppController {
	public static Logger logger = Logger.getLogger(RobotForAppController.class); 
	
	@Autowired
	private TRobotService tRobotService;

	/**
     * 注册
     *
     * @param robot 注册参数 no, passwd
     * @return Response
     */
    @RequestMapping(value = "/register", method = POST)
    @ResponseBody
    public ResponseBean register(HttpServletRequest request,@RequestBody TRobotDO robot) {
        String passowrd = robot.getPassword();
        String decryptPassword;
        List<TRobotDO> robotList= null;
        try {
            decryptPassword = RSA.decryptByPrivateKey(passowrd, Constants.PRIVATE_KEY);
            robot.setPassword( MD5.encrypt(decryptPassword));
            robot.setArea(Constants.TAX_KEY);
            robot.setCreatetime(new Date());
            robotList =  tRobotService.selectByRobotID(robot.getRobotNo());
            if(robotList.isEmpty())
            {
            	tRobotService.save(robot);
                return ResponseBean.success(Constants.TAX_KEY);
            }
            else
            {

                return ResponseBean.fail("该账号已经注册");
            }
        }catch (Exception e) {
            logger.error("register error", e);
        }
        return ResponseBean.fail();
    }
    
    /**
     * 机器号注册查询
     *
     * @param robotNo  参数 robotNo
     * @return Response
     */
    @RequestMapping(value = "/registerQuery", method = GET)
    @ResponseBody
    public ResponseBean registerQuery(String robotNo) {
        List<TRobotDO> robotList = null;
        try{
        	TRobotDO robot = new TRobotDO();
            robot.setRobotNo(robotNo);
            robotList = tRobotService.selectByRobotID(robotNo);
            if (!robotList.isEmpty()){
                return ResponseBean.success(robotList.get(0).getArea());
            }else {
                return ResponseBean.fail("该账号不存在");
            }
        }catch (Exception e) {
            logger.error("registerQuery异常", e);
        }
        return ResponseBean.fail();
    }
    
    /**
     * 登录
     *
     * @param robot 登录参数 no, passwd
     * @return 登录成功返回一个票据
     */
    @RequestMapping(value = "/login", method = POST)
    @ResponseBody
    public ResponseBean login(TRobotDO robot) {
        String uuid = "";
        List<TRobotDO> robotList= null;
        try {
            robotList =  tRobotService.selectByRobotIDandPassowrd(robot.getRobotNo(),robot.getPassword());
            if(robotList.isEmpty())
            {
                return ResponseBean.fail("机器人名或者密码错误！");
            }
        }catch (Exception e) {
            logger.error("login", e);
        }
        uuid = UUID.randomUUID().toString();
        Date date=new Date();
        TicketModel ticketModel= new TicketModel();
        ticketModel.setTicket(uuid);
        ticketModel.setRobotNo(robot.getRobotNo());
        ticketModel.setCreateTime(date);

        TicketManager.addTicket(ticketModel);
        return ResponseBean.success(uuid);
    }
    
    /**
     * 登出
     *
     * @param ticket 票据
     * @return Response
     */
    @RequestMapping(value = "/logout", method = GET)
    public ResponseBean logout(String ticket) {
        if(TicketManager.delTicketByTicket(ticket)) {
            return ResponseBean.success();
        }
        return ResponseBean.fail();
    }
    
    /**
     * 机器号区域修改
     *
     * @param robotNo  @param area  参数 robotNo  area
     *
     */
    @RequestMapping(value = "/updateArea", method = GET)
    public ResponseBean updateArea(String area,String robotNo) {
        List<TRobotDO> robotList=null;
        try {
        	TRobotDO robot = new TRobotDO();
            robot.setArea(area);
            robot.setRobotNo(robotNo);
            robotList=tRobotService.selectByRobotID(robot.getRobotNo());
            if (robot.getRobotNo().equals(robotNo)) {
            	tRobotService.updateByrobot(robot);
                sendMessageNotifyRobot1(robotNo,area);
                return ResponseBean.success(robotList.get(0).getArea());
            }
            else {
                return ResponseBean.fail();
            }
        }catch (Exception e){
            logger.error("updateArea异常",e);
        }
        return ResponseBean.fail();
    }
    
    private void sendMessageNotifyRobot1(String robotNo,String area) {
        try {
            DeliveryModel deliveryModel = new DeliveryModel();
            deliveryModel.setArea(area);
            // 推送的数据
            String data = JSON.toJSONString(deliveryModel);
            // 数据签名
            String sign = RSA.sign(Constants.PRIVATE_KEY, data);
            // 推送出货消息
            JpushUtils.sendPush(PushTypeEnum.AREA, new MessageModel(data, sign).toString(), robotNo);
            System.out.println("sendMessageNotifyRobot1 is ok!");
        } catch (Exception e) {
            // 禁止抛出异常, 以保证业务的处理结果正常返回给第三方支付
            logger.error("消息推送异常", e);
        }
    }
}
