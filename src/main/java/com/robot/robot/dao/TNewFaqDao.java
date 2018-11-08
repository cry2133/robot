package com.robot.robot.dao;

import com.robot.robot.domain.TNewFaqDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 新问答表
 * @author laoGF
 * @email ***
 * @date 2018-08-09 19:03:40
 */
@Mapper
@Repository
public interface TNewFaqDao {

	TNewFaqDO get(Long id);
	
	List<TNewFaqDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TNewFaqDO tNewFaq);
	
	int update(TNewFaqDO tNewFaq);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<TNewFaqDO> getLikeByQuestion(Map<String,Object> map);

}
