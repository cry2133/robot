package com.robot.robot.dao;

import com.robot.robot.domain.TTaxInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 电力业务类型
 * @author yobi
 * @email ***
 * @date 2018-02-05 17:25:38
 */
@Mapper
public interface TTaxInfoDao {

	TTaxInfoDO get(Integer id);
	
	List<TTaxInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TTaxInfoDO tTaxInfo);
	
	int update(TTaxInfoDO tTaxInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
