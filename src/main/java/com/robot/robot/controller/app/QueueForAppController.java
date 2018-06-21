package com.robot.robot.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TQueueInfoDO;
import com.robot.robot.service.TQueueInfoService;

/**
 * 
 * 智能排队类型接口
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年6月19日
 */
@RestController
@RequestMapping("/app/queue")
public class QueueForAppController {

	@Autowired
	private TQueueInfoService tQueueInfoService;
	
	    private Logger logger = LoggerFactory.getLogger(TaxForAppController.class);

	    @RequestMapping(value = "/searchQueueInfo",method= RequestMethod.GET)
	    public ResponseBean searchQueueInfo(String robotNo) {
	    	List<TQueueInfoDO> tTaxInfoList  = null;
	        try {
	        	//查询列表数据
	        	Map<String, Object> query = new HashMap<>(0);
	        	tTaxInfoList = tQueueInfoService.list(query);
	            return ResponseBean.success(tTaxInfoList.toArray());
	        }catch (Exception e) {
	            logger.error("robot："+robotNo+ " search error", e);
	            return ResponseBean.fail();
	        }
	    }
	    
}
