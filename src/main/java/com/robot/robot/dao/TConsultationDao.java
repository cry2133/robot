package com.robot.robot.dao;

import com.robot.robot.domain.TConsultationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 咨询建议表
 * @author laoGF
 * @email ***
 * @date 2018-09-07 18:14:00
 */
@Mapper
public interface TConsultationDao {

	TConsultationDO get(Long id);
	
	List<TConsultationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TConsultationDO tConsultation);
	
	int update(TConsultationDO tConsultation);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
