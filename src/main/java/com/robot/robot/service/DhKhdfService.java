package com.robot.robot.service;

import com.robot.robot.domain.DhKhdfDO;

import java.util.List;
import java.util.Map;

/**
 * 电费查询-来源回流库
 * 
 * @author yobi
 * @email ***
 * @date 2018-03-28 14:05:50
 */
public interface DhKhdfService {
	
	
	List<DhKhdfDO> list(Map<String, Object> map);
	
	List<DhKhdfDO> allList(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
}
