package com.robot.robot.controller.app;

import com.robot.common.utils.StringUtils;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TConsultationDO;
import com.robot.robot.domain.TRobotUserDO;
import com.robot.robot.service.TConsultationService;
import com.robot.robot.service.TRobotUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 咨询建议
 */
@Controller
@RequestMapping("/app/consultation")
public class ConsultationForAppController {

    @Resource
    private TConsultationService tConsultationService;
    @Resource
    private TRobotUserService tRobotUserService;

    @RequestMapping(value = "/add", method = POST)
    @ResponseBody
    public ResponseBean add(@RequestBody TConsultationDO tConsultationDO) {

        Map<String,Object> map =  new HashMap<>();

        String robotNo = tConsultationDO.getRobotNo();
        if(StringUtils.isNotEmpty(robotNo)){
            map.put("robotNo",robotNo);
            List<TRobotUserDO> list = tRobotUserService.list(map);
            for(TRobotUserDO tRobotUserDO: list){
                long userId = tRobotUserDO.getUserId();
                if(userId > 0){
                    tConsultationDO.setUserId(userId);
                    break;
                }
            }
        }

        tConsultationDO.setCreatetime(new Date());
        int save = tConsultationService.save(tConsultationDO);

        if(save > 0){
            return ResponseBean.success("提交成功");
        }
        return ResponseBean.fail("提交失败！");

    }

}
