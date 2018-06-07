package com.robot.robot.dldao;

import com.robot.robot.domain.DhKhxxDO;
import com.robot.robot.domain.DlKhYdkhDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 该表存放用电客户和考核户、违约窃电黑户的基本信息
1、用电客户定义：依法与供电企业建立供用电关系的组织或个人
 * @author yobi
 * @email ***
 * @date 2018-03-29 17:44:46
 */
@Mapper
public interface DlKhYdkhDao {

	DlKhYdkhDO get(String yhbh);
	
	List<DlKhYdkhDO> list(Map<String,Object> map);
	
	List<DhKhxxDO> getForZjhm(String zjhm);
	
	int count(Map<String,Object> map);
	
	int save(DlKhYdkhDO dlKhYdkh);
	
	int update(DlKhYdkhDO dlKhYdkh);
	
	int remove(String YHBH);
	
	int batchRemove(String[] yhbhs);
}
