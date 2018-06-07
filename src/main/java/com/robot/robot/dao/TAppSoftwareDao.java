package com.robot.robot.dao;

import com.robot.robot.domain.TAppSoftwareDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机器人版本号表
 * @author yobi
 * @email ***
 * @date 2018-01-23 11:17:33
 */
@Mapper
public interface TAppSoftwareDao {

	TAppSoftwareDO get(Integer id);
	
	/** 获取最新的apk下载包 */
	TAppSoftwareDO getNewAPK(Map<String,Object> map);
	
	List<TAppSoftwareDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TAppSoftwareDO tAppSoftware);
	
	int update(TAppSoftwareDO tAppSoftware);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
