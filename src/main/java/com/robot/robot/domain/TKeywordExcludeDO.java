package com.robot.robot.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:48:51
 */
public class TKeywordExcludeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//关键字id
	private Long keywordId;
	//排除关键字id
	private Long excludeId;
	//排除的关键字
	private String excludeName;
	//关键字
	private String keyword;

	//排除关键字id
	private Long[] excludeIds;
	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：关键字id
	 */
	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}
	/**
	 * 获取：关键字id
	 */
	public Long getKeywordId() {
		return keywordId;
	}
	/**
	 * 设置：排除关键字id
	 */
	public void setExcludeId(Long excludeId) {
		this.excludeId = excludeId;
	}
	/**
	 * 获取：排除关键字id
	 */
	public Long getExcludeId() {
		return excludeId;
	}
	/**
	 * 获取：排除的关键字
	 */
	public String getExcludeName() {
		return excludeName;
	}
	/**
	 * 设置：排除的关键字
	 */
	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
	}
	/**
	 * 获取：关键字
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * 设置：关键字
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long[] getExcludeIds() {
		return excludeIds;
	}
	public void setExcludeIds(Long[] excludeIds) {
		this.excludeIds = excludeIds;
	}
	
}
