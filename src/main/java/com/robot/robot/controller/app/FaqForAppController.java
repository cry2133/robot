package com.robot.robot.controller.app;

import java.util.*;

import com.robot.common.utils.*;
import com.robot.robot.domain.TFaqLogDO;
import com.robot.robot.service.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Resource
    private TFaqLogService tFaqLogService;

    private FaqRequestBean faqBean = new FaqRequestBean();


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

        //查找答案（知识库==>讯飞==>图灵）
        faqBean.setQuestion(content);

        String answer = tNewFaqService.searchAnswer(content,robotNo);
        faqBean.setAnswer(answer);

        //场景问答只重复澄清一次
        String upAnswer = RedisUtil.getValue(robotNo + "robot_up_answer");
        if(answer.equals(upAnswer)){
            RedisUtil.del(robotNo + "robot_up_answer");
            RedisUtil.del(robotNo + "robot_entrance_entity_cache");
            RedisUtil.del(robotNo + "robot_up_entity_id");
            RedisUtil.del(robotNo + "robot_next_question");
        }
        RedisUtil.setExpire(robotNo + "robot_up_answer", 50, answer);

        TFaqLogDO tFaqLogDO = new TFaqLogDO();
        tFaqLogDO.setQuestion(content);
        tFaqLogDO.setAnswer(answer);
        tFaqLogDO.setRobotNo(robotNo);
        tFaqLogDO.setCreatetime(new Date());
        int save = tFaqLogService.save(tFaqLogDO);
        if(save > 0){
            log.info("------问答记录成功！------");
        }

        List<String> questionList = tNewFaqService.getQuestionList();
        faqBean.setQuestionList(questionList);

        return ResponseBean.success(faqBean);
    }



}
