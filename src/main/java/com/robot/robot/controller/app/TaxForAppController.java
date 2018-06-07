package com.robot.robot.controller.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TTaxInfoDO;
import com.robot.robot.service.TTaxInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/tax")
public class TaxForAppController {

	@Autowired
	private TTaxInfoService tTaxInfoService;
	
	    private Logger logger = LoggerFactory.getLogger(TaxForAppController.class);

	    @RequestMapping(value = "/searchTaxInfo",method= RequestMethod.GET)
	    public ResponseBean searchTaxInfo(String robotNo) {
	    	List<TTaxInfoDO> tTaxInfoList  = null;
	        try {
	        	//查询列表数据
	        	Map<String, Object> query = new HashMap<>(0);
	        	tTaxInfoList = tTaxInfoService.list(query);
	            return ResponseBean.success(tTaxInfoList.toArray());
	        }catch (Exception e) {
	            logger.error("robot："+robotNo+ " search error", e);
	            return ResponseBean.fail();
	        }
	    }
}
