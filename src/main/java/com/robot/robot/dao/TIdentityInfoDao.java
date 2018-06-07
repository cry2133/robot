package com.robot.robot.dao;

import com.robot.robot.domain.TIdentityInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-20 18:01:32
 */
@Mapper
public interface TIdentityInfoDao {

	TIdentityInfoDO get(Long id);
	
	List<TIdentityInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TIdentityInfoDO tIdentityInfo);
	
	int update(TIdentityInfoDO tIdentityInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<TIdentityInfoDO> selectByIdentityID(String identityID);
	
}
