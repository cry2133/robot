package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 场景入口表
 * 
 * @author laoGF
 * @date 2018-09-04 10:53:08
 */
public class TEntranceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long id;
	//场景入口
	private String entrance;
	///场景入口集
	private String entrances;
	//词槽实体
	private List<Long> entityIds;
	//角色ID
	private Long roleId;


	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：场景入口
	 */
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	/**
	 * 获取：场景入口
	 */
	public String getEntrance() {
		return entrance;
	}

	public String getEntrances() {
		return entrances;
	}

	public void setEntrances(String entrances) {
		this.entrances = entrances;
	}

	/**
	 * 获取：词槽实体
	 */
	public List<Long> getEntityIds() {
		return entityIds;
	}

	/**
	 * 设置：词槽实体
	 */
	public void setEntityIds(List<Long> entityIds) {
		this.entityIds = entityIds;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
