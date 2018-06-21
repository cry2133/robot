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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 机器人异常日志 接口
 */
@Controller
@RequestMapping("/app/log")
public class RobotLogController {
	@Autowired
	private TRobotLogService tRobotLogService;

	/**
	 * 保存机器人异常日志 接口
	 */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResponseBean addSpeakLog(@RequestParam String robotNo, String level, String message, String tag) {
        Date now = new Date();
        now.getTime();
        
        Calendar c1=Calendar.getInstance();  
        Calendar c2=Calendar.getInstance();  
        /*
        Calendar c3=Calendar.getInstance();  
        c1.setTime(now);//要判断的日期  
        c2.setTime(new Date());//初始日期  
        c3.setTime(new Date());//也给初始日期 把分钟加五  
        c3.add(Calendar.MINUTE, 5);  //加五分钟
        c2.add(Calendar.MINUTE,-5);  //减去五分钟  
        System.out.println("c1"+c1.getTime());  
        System.out.println("c2"+c2.getTime());  
        System.out.println("c3"+c3.getTime());  
        if(c1.after(c2)&&c1.before(c3)){  
            System.out.println("五分钟之内");  
        }else {  
            System.out.println("五分钟之外");  
        }  
       */
        c1.setTime(new Date());//初始日期  
        c2.setTime(new Date());//初始日期 把分钟减十五  
        c2.add(Calendar.MINUTE, -15);  //减去十五分钟
        
        TRobotLogDO robotLog = new TRobotLogDO();
        robotLog.setRobotNo(robotNo);
        robotLog.setLevel(level);
        robotLog.setMessage(message);
        robotLog.setTag(tag);
        robotLog.setTime(now);
        
        //过滤机器人（底座数据异常日志）频繁输出，十五分钟保存一次
        Pattern p=Pattern.compile("底座数据");  
        Matcher m=p.matcher(message);    //正则表达式匹配
        if(m.lookingAt()){
	        Map<String,Object> map = new HashMap<String,Object>();
	        map.put("robotNo", robotNo);
	        map.put("level", level);
	        map.put("message", message);
	        map.put("tag", tag);
	        map.put("beginTime", c2.getTime());
	        map.put("endTime", c1.getTime());
	        int get = tRobotLogService.list(map).size();
	        if(get>0){
	        	System.out.println("---------------机器人底座数据异常，已存在！------------------");
	        	return ResponseBean.success();
	        }
        }
        
        try {
        	tRobotLogService.save(robotLog);
            return ResponseBean.success();
        }catch (Exception e) {
//            logger.error("robot："+robotNo+ " search error", e);
        }
        return ResponseBean.fail();
    }
}