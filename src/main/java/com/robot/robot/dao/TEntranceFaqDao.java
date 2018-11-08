package com.robot.robot.dao;

import com.robot.robot.domain.TEntranceFaqDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 多轮问答表
 *
 * @author laoGF
 * @date 2018-09-28 16:36:54
 */
@Mapper
public interface TEntranceFaqDao {

	TEntranceFaqDO get(Long id);
	
	List<TEntranceFaqDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TEntranceFaqDO tEntranceFaq);
	
	int update(TEntranceFaqDO tEntranceFaq);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<TEntranceFaqDO> getLikeByQuestion(Map<String,Object> map);

}
