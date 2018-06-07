package com.robot.robot.service;

import java.util.List;
import java.util.Map;

import com.robot.robot.domain.DlZwYsDfJlDO;

/**
 * 
 * 应收电费记录  - -来源回流库
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年6月6日
 */
public interface DlZwYsDfJlService {

	List<DlZwYsDfJlDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
}
