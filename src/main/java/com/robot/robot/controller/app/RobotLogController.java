package com.robot.robot.controller.app;

import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TRobotLogDO;
import com.robot.robot.service.TRobotLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/14.
 */
@Controller
@RequestMapping("/app/log")
public class RobotLogController {
	@Autowired
	private TRobotLogService tRobotLogService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResponseBean addSpeakLog(@RequestParam String robotNo, String level, String message,String tag) {
        Date now = new Date();
        now.getTime();

        TRobotLogDO robotLog = new TRobotLogDO();
        robotLog.setRobotNo(robotNo);
        robotLog.setLevel(level);
        robotLog.setMessage(message);
        robotLog.setTag(tag);
        robotLog.setTime(now);

        try {
        	tRobotLogService.save(robotLog);
            return ResponseBean.success();
        }catch (Exception e) {
//            logger.error("robot："+robotNo+ " search error", e);
        }
        return ResponseBean.fail();
    }
}