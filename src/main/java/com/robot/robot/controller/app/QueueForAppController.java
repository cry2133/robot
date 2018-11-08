package com.robot.robot.controller.app;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TQueueDO;
import com.robot.robot.service.TQueueService;

/**
 * 
 * 智能排队接口
 *
 * @Author 	laoGF
 * @Version 	1.0 
 * @Data 	2018年6月19日
 */
@RestController
@RequestMapping("/app/queue")
public class QueueForAppController {
	private Logger logger = LoggerFactory.getLogger(TaxForAppController.class);
	@Autowired
	private TQueueService tQueueService;
	
	/**
	 * @Functionlity  查询智能排队
	 * @Author  laoGF
	 * @Date  2018年7月16日
	 */
    @RequestMapping(value = "/searchQueue",method= RequestMethod.GET)
    @ResponseBody
    public ResponseBean searchQueue(String identityID) {
    	List<TQueueDO> tQueueList  = null;
        try {
        	//查询列表数据
        	Map<String, Object> query = new HashMap<>(0);
        	query.put("identityID",identityID);
        	tQueueList = tQueueService.list(query);
        	if(tQueueList.size() == 0){
        		return ResponseBean.success();
        	}
            return ResponseBean.success(tQueueList.toArray());
        }catch (Exception e) {
            logger.error("robot："+identityID+ " search error", e);
            return ResponseBean.fail();
        }
    }
	    
    /**
     * @Functionlity  智能排队
     * @Author  laoGF
     * @Date  2018年7月16日
     */
	@RequestMapping(value = "/queue",method= RequestMethod.GET)
	@ResponseBody
    public ResponseBean queue(String identityID,String queueTime, int taxID) {
        try {
        	TQueueDO data=new TQueueDO();
        	Map<String, Object> map = new HashMap<String, Object>();
        	Map<String, Object> map2 = new HashMap<String, Object>();
        	data.setIdentityID(identityID);
        	data.setTaxID(taxID);
        	//queueTime=2017-08-30 15:00-16:00
        	data.setQueueTime(queueTime);
        	map.put("identityID", identityID);
        	map.put("taxID", taxID);
        	List<TQueueDO> queue = tQueueService.list(map);
        	if(queue.size()>0){
        		return ResponseBean.fail("你已在排队！请勿重复操作！");
        	}
        	int count = tQueueService.count(map2);
        	tQueueService.save(data);
        	return ResponseBean.success("排队成功！你前面还有"+count+"个人。");
        }catch (Exception e) {
            logger.error("queue异常", e);
        }
        return ResponseBean.fail("对不起！操作失败！");
    }
		
		 
	/**
	 * @Functionlity  智能排队删除
	 * @Author  laoGF
	 * @Date  2018年7月16日
	 */
	@RequestMapping(value = "/queueDelete",method= RequestMethod.GET)
	@ResponseBody
    public ResponseBean queueDelete(String identityID,String queueTime, int taxID) {
        try{
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("identityID", identityID);
        	map.put("taxID", taxID);
        	List<TQueueDO> queue = tQueueService.list(map);
        	if(queue.size()>0){
        		for(TQueueDO q:queue){
	        		Long id = q.getId();
	        		tQueueService.remove(id);
	        	}
        	}
        	return ResponseBean.success();
	    }catch (Exception e){
	        logger.error("删除排队异常",e);
	    }
        return ResponseBean.fail();
    }
    
}
