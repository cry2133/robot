package com.robot.robot.service;

import com.robot.robot.domain.TIdentityInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-20 18:01:32
 */
public interface TIdentityInfoService {
	
	TIdentityInfoDO get(Long id);
	
	List<TIdentityInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TIdentityInfoDO tIdentityInfo);
	
	int update(TIdentityInfoDO tIdentityInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	TIdentityInfoDO selectByIdentityID(String identityID);
}
