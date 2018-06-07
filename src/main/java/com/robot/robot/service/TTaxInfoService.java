package com.robot.robot.service;

import com.robot.robot.domain.TTaxInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 电力业务类型
 * 
 * @author yobi
 * @email ***
 * @date 2018-02-05 17:25:38
 */
public interface TTaxInfoService {
	
	TTaxInfoDO get(Integer id);
	
	List<TTaxInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TTaxInfoDO tTaxInfo);
	
	int update(TTaxInfoDO tTaxInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
