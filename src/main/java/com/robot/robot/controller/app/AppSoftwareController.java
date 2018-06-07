package com.robot.robot.controller.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TAppSoftwareDO;
import com.robot.robot.service.TAppSoftwareService;

import java.util.HashMap;
import java.util.Map;

/**
 * apk升级版本管理
 */
@RestController
@RequestMapping("/app/software")
public class AppSoftwareController {
	@Autowired
	private TAppSoftwareService tAppSoftwareService;
    
    private Logger logger = LoggerFactory.getLogger(AppSoftwareController.class);

    /**
     * 获取最新的apk下载包
     */
    @RequestMapping(value = "/upgrade",method= RequestMethod.GET)
    public ResponseBean upgrade(String robotNo) {
        TAppSoftwareDO appSoft = null;
        Map<String,Object> map =new HashMap<String,Object>();
        try {
        	appSoft = tAppSoftwareService.getNewAPK(map);
            if (appSoft.getId() == null && appSoft.getPath().isEmpty()) {
            	/**
            	 * 没有升级版本时返回空
            	 */
            	return ResponseBean.success();
            	//return ResponseBean.success("没有升级信息！");
            }
            /*
            for (int i = 0; i < appSoftwareList.size(); i++) {
                String path = UrlManagement.updatePath;
                path += appSoftwareList.get(i).getPath();
                appSoftwareList.get(i).setPath(path);
            }
            */
            return ResponseBean.success(appSoft.toString());
        }catch (Exception e) {
            logger.error("robot："+robotNo+ " upgrade error", e);
        }
        return ResponseBean.fail();
    }
}
