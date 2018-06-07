package com.robot.robot.dao;

import com.robot.robot.domain.TPrinterSettingDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 打印机设置表
 * @author yobi
 * @email ***
 * @date 2017-12-26 09:55:51
 */
@Mapper
public interface TPrinterSettingDao {

	TPrinterSettingDO get(Long id);
	
	List<TPrinterSettingDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TPrinterSettingDO tPrinterSetting);
	
	int update(TPrinterSettingDO tPrinterSetting);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
