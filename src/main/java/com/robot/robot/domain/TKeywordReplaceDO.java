package com.robot.robot.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:48:51
 */
public class TKeywordReplaceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long replaceId;
	//关键字主键id
	private Long keywordId;
	//替换词名称
	private String charkey;
	
	
	//非持久化字段
	
	//替换词名称
	private String keyword;
	

	/**
	 * 设置：
	 */
	public void setReplaceId(Long replaceId) {
		this.replaceId = replaceId;
	}
	/**
	 * 获取：
	 */
	public Long getReplaceId() {
		return replaceId;
	}
	/**
	 * 设置：关键字主键id
	 */
	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}
	/**
	 * 获取：关键字主键id
	 */
	public Long getKeywordId() {
		return keywordId;
	}
	/**
	 * 设置：替换词名称
	 */
	public void setCharkey(String charkey) {
		this.charkey = charkey;
	}
	/**
	 * 获取：替换词名称
	 */
	public String getCharkey() {
		return charkey;
	}
	/**
	 * 设置：关键字名称
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * 获取：关键字名称
	 */
	public String getKeyword() {
		return keyword;
	}
}
