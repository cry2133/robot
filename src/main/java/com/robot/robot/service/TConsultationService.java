package com.robot.robot.service;

import com.robot.robot.domain.TConsultationDO;

import java.util.List;
import java.util.Map;

/**
 * 咨询建议表
 * 
 * @author laoGF
 * @email ***
 * @date 2018-09-07 18:14:00
 */
public interface TConsultationService {
	
	TConsultationDO get(Long id);
	
	List<TConsultationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TConsultationDO tConsultation);
	
	int update(TConsultationDO tConsultation);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
