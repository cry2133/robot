package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 场景入口与词槽实体关联表
 * 
 * @author laoGF
 * @date 2018-09-04 14:11:09
 */
public class TEntranceEntityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long id;
	//场景入口ID
	private Long entranceId;
	//词槽实体ID
	private Long entityId;
	//上层词槽ID
	private Long parentId;
	//连接词
	private String word;

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
	 * 设置：场景入口ID
	 */
	public void setEntranceId(Long entranceId) {
		this.entranceId = entranceId;
	}
	/**
	 * 获取：场景入口ID
	 */
	public Long getEntranceId() {
		return entranceId;
	}
	/**
	 * 设置：词槽实体ID
	 */
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	/**
	 * 获取：词槽实体ID
	 */
	public Long getEntityId() {
		return entityId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
