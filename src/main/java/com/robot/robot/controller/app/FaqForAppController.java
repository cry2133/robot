package com.robot.robot.controller.app;

import java.util.*;

import com.robot.common.utils.*;
import com.robot.robot.controller.app.baidu.UnitService;
import com.robot.robot.domain.TNewFaqDO;
import com.robot.robot.service.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.robot.controller.app.bean.FaqRequestBean;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.controller.app.common.AppConstants;
import com.robot.robot.utils.SessionUtil;

import javax.annotation.Resource;

/**
 * 机器人智能问答接口
 *
 * @Author laoGF
 * @Version 1.0
 * @Data 2018年6月26日
 */
@Controller
@RequestMapping("/app/faq")
public class FaqForAppController {
    public static Logger log = Logger.getLogger(FaqForAppController.class);

    @Resource
    private TNewFaqService tNewFaqService;


    /**
     * 智能查询问答
     *
     */
    @RequestMapping("/searchAnswer")
    @ResponseBody
    public ResponseBean searchAnswer(HttpServletRequest request) {

        String content = RequestUtil.getString(request, "content");
        String robotNo = RequestUtil.getString(request, "robotNo");


        //退出即清空session缓存
        if (content.equals(AppConstants.OUT)) {
            SessionUtil.removeSessionAttribute(robotNo);
        }

        if (StringUtils.isEmpty(content)) {
            return ResponseBean.fail("参数格式不对");
        }

        FaqRequestBean faqBean = tNewFaqService.searchAnswer(content,robotNo);

        //场景问答只重复澄清一次
        String answer = faqBean.getAnswer();
        String upAnswer = RedisUtil.getValue(robotNo + "robot_up_answer");
        if(answer.equals(upAnswer)){
            RedisUtil.del(robotNo + "robot_up_answer");
            RedisUtil.del(robotNo + "robot_entrance_entity_cache");
            RedisUtil.del(robotNo + "robot_up_entity_id");
            RedisUtil.del(robotNo + "robot_next_question");
        }
        RedisUtil.setExpire(robotNo + "robot_up_answer", 50, answer);

        return ResponseBean.success(faqBean);
    }


    /**
     * 百度UNIT
     */
    @RequestMapping("/unit")
    @ResponseBody
    public String unit(){
        UnitService u = new UnitService();
        System.out.println(u.unit("居民用户掌上营业厅办理过户"));
        return "UNIT";
    }



}
